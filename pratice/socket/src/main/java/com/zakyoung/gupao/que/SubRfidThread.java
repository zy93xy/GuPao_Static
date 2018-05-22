package com.zakyoung.gupao.que;

import com.wonders.framework.util.DateUtil;
import com.wonders.train.backend.entity.po.DataLogPO;
import com.wonders.train.backend.queue.ProducerService;
import com.wonders.train.backend.service.DataLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.UncategorizedJmsException;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.Socket;
import java.util.Date;

/**
 * create by tf 2018/5/2 10:28
 */
public class SubRfidThread extends Thread {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private ProducerService producerService;//交给mq 后续处理
	private DataLogService dataLogService;
	private Socket socket;

	public SubRfidThread(Socket conSocket, ProducerService producerService, DataLogService dataLogService) {
		this.socket = conSocket;
		this.producerService = producerService;
		this.dataLogService = dataLogService;
	}

	@Override
	public void run(){
		PrintStream out = null;
		Reader reader = null;
		BufferedReader br = null;
		try {
			//System.out.println("prepareing... ");
			//RfidServer.list.remove(this);
			reader = new InputStreamReader(socket.getInputStream());
			br = new BufferedReader(reader);
			out = new PrintStream(socket.getOutputStream());
			String message = br.readLine();
			if(StringUtils.isEmpty(message)){
				System.out.println("null message");
				return;
			}
			message = message.trim();
			writeRFIDLog(message);
			System.out.println(message);
			// 先写入数据库
			if (StringUtils.isEmpty(message))return;
			String[] arr = message.split(":");
			DataLogPO datalog = new DataLogPO(arr[0], arr[1], arr[2], new Date(Long.valueOf(arr[3])),
					Integer.valueOf(arr[4]));
			dataLogService.save(datalog);
			// 交给mq后续分析
			producerService.sendMessage(message);
			// 返回给client
			out.println("ok");
			out.flush();
		} catch (UncategorizedJmsException e) {
			logger.error("mq中间件未启动，请联系管理员", e);
			out.println("mq异常");
			out.flush();
		} catch (Exception e) {

		} finally {
			if (out != null) {
				out.close();
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (br == null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (socket != null) {
				try {
					socket.close();
				} catch (Exception e) {
					System.out.println(e.toString());
					logger.error(e.toString(), e);
				}
			}
		}
	}

	private void writeRFIDLog(String message) throws IOException{
		File f = new File("E:/rfidLog.txt");
		if(!f.exists()) f.createNewFile();
		FileWriter writer = new FileWriter(f,true);
		String str = DateUtil.format(new Date()) +"   message:"+message;
		writer.write(str+"\r\n");
		writer.flush();
		writer.close();
	}
}
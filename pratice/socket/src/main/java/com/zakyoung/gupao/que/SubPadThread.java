package com.zakyoung.gupao.que;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.Socket;

/**
 * create by tf 2018/5/2 10:26
 */
public class SubPadThread extends Thread {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private Socket socket;
	public SubPadThread(Socket conSocket) {
		this.socket = conSocket;
	}

	@Override
	public void run(){
		try {
			Reader reader = new InputStreamReader(socket.getInputStream());
			BufferedReader br = new BufferedReader(reader);
			while (true) {
				try {
					String socketId = br.readLine();
					if(StringUtils.isEmpty(socketId))continue;
					socketId = socketId.trim();
					//itemId:groupId
					System.out.println("socketId:" + socketId);
					PadServer.handleMapping.put(socketId,socket);
					System.out.println("pad端数量"+PadServer.handleMapping.size());
				} catch (Exception e) {
					System.out.println("pad socket内部异常："+e.toString());
					logger.error(e.toString(), e);
				}
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			logger.error(e.toString(), e);
			System.out.println("断开了一个pad");
		}
	}
}

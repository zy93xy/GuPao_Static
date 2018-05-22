package com.zakyoung.gupao.que;


import com.wonders.train.backend.queue.ProducerService;
import com.wonders.train.backend.service.DataLogService;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RfidServer extends Thread {
	//public  static List<SubRfidThread> list = new CopyOnWriteArrayList<>();
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private ProducerService producerService;
	private DataLogService dataLogService;
	public RfidServer(ProducerService producerService, DataLogService dataLogService) {
		this.producerService = producerService;
		this.dataLogService = dataLogService;
	}
	@Override
	public void run() {
		ServerSocket serverSocket = null;
		ExecutorService rfidService = Executors.newCachedThreadPool();
		try {
			serverSocket = new ServerSocket(63333);
			while (true) {
				try {
					Socket socket = serverSocket.accept();
					SubRfidThread subRfid = new SubRfidThread(socket, producerService, dataLogService);
					//list.add(subRfid);
					//subRfid.start();
					rfidService.submit(subRfid);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
			//rfidService.shutdown();
		}
	}
}
package com.zakyoung.gupao.que;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wonders.train.backend.service.ItemGroupService;

public class PadServer extends Thread {
	public static Map<String, Socket> handleMapping = new HashMap<String, Socket>();
	private final Logger logger = LoggerFactory.getLogger(this.getClass());


	@Override
	public void run() {
		ExecutorService padService = Executors.newCachedThreadPool();
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(61111);
			while (true) {
				try {
					Socket socket = serverSocket.accept();
					SubPadThread subPad = new SubPadThread(socket);
					padService.submit(subPad);
					//subPad.start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
			System.out.println(e.toString());
			padService.shutdown();
		}
	}
}

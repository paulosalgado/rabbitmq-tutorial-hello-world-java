package com.paulosalgado.rabbitmq.tutorials.helloworld;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;

public class Send {
	
	private final static String QUEUE_NAME = "hello";
	
	public static void main(String[] argv) throws IOException, TimeoutException {
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		for (int i = 1; i <= 5; i++) {
			String message = "Hello World! " + i;
			channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
			System.out.println(" [x] Sent '" + message + "'");
		}
		
		channel.close();
		connection.close();
	}
	
}

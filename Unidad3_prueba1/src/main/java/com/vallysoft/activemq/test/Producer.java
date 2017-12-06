package com.vallysoft.activemq.test;

import java.util.ArrayList;
import java.util.List;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Producer {
	// URL of the JMS server. DEFAULT_BROKER_URL will just mean
	// that JMS server is on localhost
	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	// default broker URL is : tcp://localhost:61616"

	private static String subject = "LDS"; // Queue Name
	// You can create any/many queue names as per your requirement.

	public static void main(String[] args) throws JMSException {
		int contQueue=100;
		// Getting JMS connection from the server and starting it
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		Connection connection = connectionFactory.createConnection();
		connection.start();
		// JMS messages are sent and received using a Session. We will
		// create here a non-transactional session object. If you want
		// to use transactions you should set the first parameter to 'true'
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// Destination represents here our queue 'VALLYSOFTQ' on the
		// JMS server. You don't have to do anything special on the
		// server to create it, it will be created automatically.
		Destination destination = session.createQueue(subject);
		// MessageProducer is used for sending messages (as opposed
		// to MessageConsumer which is used for receiving them)
		MessageProducer producer = session.createProducer(destination);
		
		// We will send a small text message saying 'Hello' in Japanese
		//TextMessage message = session.createTextMessage("Hello welcome come to LDS ActiveMQ!");
		TextMessage[] arrayMessage = new TextMessage[contQueue];
		//ArrayList listaMensajes = new ArrayList();
		for(int i=0; i<contQueue; i++) {
			arrayMessage[i]=session.createTextMessage("Hello welcome come to LDS ActiveMQ!"+i);
			producer.send(arrayMessage[i]);
			System.out.println("Sentage '" + arrayMessage [i].getText()+ "'");
		}
		
//		for(int i=0; i<100; i++) {
//			TextMessage message = session.createTextMessage("Hello welcome come to LDS ActiveMQ!" +i);
//			listaMensajes.add(message);
//			//producer.send(arrayMessage[i]);
//			//System.out.println("Sentage '" + arrayMessage [i].getText()+ "'");
//		}
//		for(int i=0; i<listaMensajes.size(); i++) {
//			producer.send(arrayMessage[i]);
//			System.out.println("Sentage '" + arrayMessage [i].getText()+ "'");
//		}
		// Here we are sending the message!
		//producer.send(message);
		//System.out.println("Sentage '" + Message.getText() + "'");
		connection.close();
	}

}

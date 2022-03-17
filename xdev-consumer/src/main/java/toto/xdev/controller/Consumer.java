package toto.xdev.controller;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import toto.xdev.modet.Message;
import toto.xdev.service.MessageService;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Consumer {
	
	@Autowired
	private MessageService messageService;
	
	@RabbitListener(queues = "queue.A")
	private void receive(Message message) {
		
		messageService.addMessage(message);
//		
//		System.out.println("Name : " + message.getName());
//		System.out.println("Temperature : " + message.getTemperature());
//		System.out.println("Humidity : " + message.getHumidity());
//		System.out.println("Message received from QUEUE A -> " + message);
//		System.out.println("");
	}
	
//	@RabbitListener(queues = "queue.B")
//	private void receiveFromB(Message message) {
//		System.out.println("Name : " + message.getName());
//		System.out.println("Temperature : " + message.getTemperature());
//		System.out.println("Humidity : " + message.getHumidity());
//		
//		System.out.println("Message received from QUEUE B -> " + message);
//		System.out.println("");
//	}
//	
//	@RabbitListener(queues = "queue.C")
//	private void receiveFromC(Message message) {
//		System.out.println("Name : " + message.getName());
//		System.out.println("Temperature : " + message.getTemperature());
//		System.out.println("Humidity : " + message.getHumidity());
//		
//		System.out.println("Message received from QUEUE C -> " + message);
//		System.out.println("");
//	}
//	
//	@RabbitListener(queues = "queue.all")
//	private void receiveFromAll(Message message) {
//		System.out.println("Name : " + message.getName());
//		System.out.println("Temperature : " + message.getTemperature());
//		System.out.println("Humidity : " + message.getHumidity());
//		
//		System.out.println("Message received from QUEUE All -> " + message);
//		System.out.println("");
//	}
}

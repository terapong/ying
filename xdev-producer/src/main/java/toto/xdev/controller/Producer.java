package toto.xdev.controller;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import toto.xdev.modet.*;

@RestController
public class Producer {
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private TopicExchange exchange;
	
	//@PostMapping("/post")
	@GetMapping("/post")
	public String send(@RequestParam("name") String name, @RequestParam("temperature") String temperature, @RequestParam("humidity") String humidity) {
		System.out.println("Name : " + name);
		System.out.println("Temperature : " + temperature);
		System.out.println("Humidity : " + humidity);
		
		Message message = new Message();
		message.setName(name);
		message.setHumidity(Long.parseLong(humidity));
		message.setTemperature(Long.parseLong(temperature));
		
		rabbitTemplate.convertAndSend(exchange.getName(), "routing.A", message);
//		rabbitTemplate.convertAndSend(exchange.getName(), "routing.B", message);
//		rabbitTemplate.convertAndSend(exchange.getName(), "routing.C", message);
		
		return "Message sent su ccessfully";
	}
}
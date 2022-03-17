package toto.xdev.configuration;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {
	
	public static final String ROUTING_A = "routing.A";
	public static final String ROUTING_B = "routing.B";
	public static final String ROUTING_C = "routing.C";
	public static final String ROUTING_ALL = "routing.*";
	
	@Bean
	Queue queueA() {
		return new Queue("queue.A", false);
	}
	
	@Bean
	Queue queueB() {
		return new Queue("queue.B", false);
	}
	
	@Bean
	Queue queueC() {
		return new Queue("queue.C", false);
	}
	
	@Bean
	Queue allQueue() {
		return new Queue("queue.all", false);
	}
	
	@Bean
	Binding bindingA(Queue queueA, TopicExchange exchange) {
		return BindingBuilder
				.bind(queueA)
				.to(exchange)
				.with(ROUTING_A);
	}
	
	@Bean
	Binding bindinB(Queue queueB, TopicExchange exchange) {
		return BindingBuilder
				.bind(queueB)
				.to(exchange)
				.with(ROUTING_B);
	}
	
	@Bean
	Binding bindinC(Queue queueC, TopicExchange exchange) {
		return BindingBuilder
				.bind(queueC)
				.to(exchange)
				.with(ROUTING_C);
	}
	
	@Bean
	Binding bindinAll(Queue allQueue, TopicExchange exchange) {
		return BindingBuilder
				.bind(allQueue)
				.to(exchange)
				.with(ROUTING_ALL);
	}
	
	@Bean
	TopicExchange exchange() {
		return new TopicExchange("exchange.topic");
	}
	
	@Bean
	MessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return  template;
    }

}
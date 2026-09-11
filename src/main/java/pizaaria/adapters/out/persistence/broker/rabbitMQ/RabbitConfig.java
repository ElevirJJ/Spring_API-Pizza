package pizaaria.adapters.out.persistence.broker.rabbitMQ;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String FILA_ORDER = "fila.order";
    public static final String EXCHANGE_ORDER = "email.exchange";
    public static final String ROUTING_KEY = "order.key";

    @Bean
    public Queue filaOrder (){
        return new Queue(FILA_ORDER);
    }

    @Bean
    public DirectExchange exchange (){
        return new DirectExchange(EXCHANGE_ORDER);
    }

    @Bean
    public Binding binding (){
        return BindingBuilder
                .bind(filaOrder())
                .to(exchange())
                .with(ROUTING_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

}

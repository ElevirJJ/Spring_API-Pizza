package pizaaria.domain.message.produce;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import pizaaria.domain.message.event.OrderEvent;
import pizaaria.domain.message.rabbitMQ.RabbitConfig;

@Service
public class OrderProduce {

    private final RabbitTemplate rabbitTemplate;

    public OrderProduce(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarOrder (OrderEvent orderEvent){
        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE_ORDER,
                RabbitConfig.ROUTING_KEY,
                orderEvent);
    }
}

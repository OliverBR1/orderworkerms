package tech.oliver.orderworkerms.consumer;

import io.awspring.cloud.sqs.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import tech.oliver.orderworkerms.dto.OrderEventDto;
import tech.oliver.orderworkerms.service.OrderProcessingService;
import tools.jackson.databind.ObjectMapper;


@Component
public class OrderConsumer {

    private static final Logger logger = LoggerFactory.getLogger(OrderConsumer.class);
    public static final String ORDER_CONFIRMED_QUEUE = "order-confirmed-queue";

    private final OrderProcessingService orderProcessingService;
    private final ObjectMapper objectMapper;

    public OrderConsumer(OrderProcessingService orderProcessingService, ObjectMapper objectMapper) {
        this.orderProcessingService = orderProcessingService;
        this.objectMapper = objectMapper;
    }

    @SqsListener("ORDER_CONFIRMED_QUEUE")
    public void consume(String message) {
        logger.info("Cosuming {}", message);
        try {
            OrderEventDto event = objectMapper.readValue(message, OrderEventDto.class);
            orderProcessingService.processOrder(event.orderNumber());

        } catch (Exception e) {
            logger.error(String.format("Error while consuming %s", message), e);

            throw new RuntimeException("Failed to process message", e);
        }
    }
}

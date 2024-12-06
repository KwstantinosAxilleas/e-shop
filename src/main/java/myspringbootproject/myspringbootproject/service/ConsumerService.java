package myspringbootproject.myspringbootproject.service;

import myspringbootproject.myspringbootproject.entity.Cart;
import myspringbootproject.myspringbootproject.entity.Consumer;

public interface ConsumerService {
    Consumer getConsumer(Long consumerId);
    Consumer getConsumer(String username);
    Consumer saveConsumer(Consumer consumer);
    Cart addToCart(Long consumerId, Long productId, Integer quantity);
}
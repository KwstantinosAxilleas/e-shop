package myspringbootproject.myspringbootproject.service;

import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import myspringbootproject.myspringbootproject.entity.Cart;
import myspringbootproject.myspringbootproject.entity.Consumer;
import myspringbootproject.myspringbootproject.entity.Product;
import myspringbootproject.myspringbootproject.exception.DuplicateException;
import myspringbootproject.myspringbootproject.exception.EntityNotFoundException;
import myspringbootproject.myspringbootproject.repository.CartRepository;
import myspringbootproject.myspringbootproject.repository.ConsumerRepository;
import myspringbootproject.myspringbootproject.repository.ProductRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {

    private ConsumerRepository consumerRepository;
    private ProductRepository productRepository;
    private CartRepository cartRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Consumer getConsumer(Long consumerId) {
        Consumer consumer= unwrapConsumer(consumerRepository.findById(consumerId),consumerId);
        return consumer;
    }

    @Override
    public Consumer getConsumer(String username) {
        Consumer consumer= unwrapConsumer(consumerRepository.findByUsername(username),404L);
        return consumer;
    }

    @Override
    public Consumer saveConsumer(Consumer consumer) {
        if (!consumerExistsByUsername(consumer.getUsername())){
            consumer.setPassword(bCryptPasswordEncoder.encode(consumer.getPassword()));
            return consumerRepository.save(consumer);
        }
        else throw new DuplicateException(consumer.getUsername(), Consumer.class);
    }

    @Override
    public Cart addToCart(Long consumerId, Long productId, Integer quantity) {
        Product product = ProductServiceImpl.unwrapProduct(productRepository.findById(productId), productId);
        Consumer consumer = unwrapConsumer(consumerRepository.findById(consumerId), consumerId);
        Cart cart = new Cart();
        if(product.getQuantity()>=quantity){
            cart.setConsumer(consumer);
            cart.setProducts(product);
            cart.setQuantity(quantity);
            product.setQuantity(product.getQuantity()- quantity);
            return cartRepository.save(cart);
        }else throw new EntityNotFoundException(product.getId(), Product.class);
    }

    static Consumer unwrapConsumer(Optional<Consumer> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, Consumer.class);
    }

     public Boolean consumerExistsByUsername(String username){
        if(consumerRepository.findByUsername(username).isPresent()){
            return true;
        }else return false;
    }
}

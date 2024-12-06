package myspringbootproject.myspringbootproject.repository;

import org.springframework.data.repository.CrudRepository;
import myspringbootproject.myspringbootproject.entity.Cart;

public interface CartRepository extends CrudRepository<Cart, Long> {
}

package myspringbootproject.myspringbootproject.repository;

import org.springframework.data.repository.CrudRepository;
import myspringbootproject.myspringbootproject.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
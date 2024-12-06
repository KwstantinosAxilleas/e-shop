package myspringbootproject.myspringbootproject.service;

import java.util.List;
import myspringbootproject.myspringbootproject.entity.Product;

public interface ProductService {
    Product getProduct(Long id);
    Product saveProduct(Product product);
    Product updateProduct(Long productId, Product product);
    void deleteProduct(Long id);
    List<Product> getAllProducts();
    }
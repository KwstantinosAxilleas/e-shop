package myspringbootproject.myspringbootproject.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import myspringbootproject.myspringbootproject.entity.Product;
import myspringbootproject.myspringbootproject.exception.EntityNotFoundException;
import myspringbootproject.myspringbootproject.repository.ProductRepository;
import myspringbootproject.myspringbootproject.repository.ConsumerRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;
    ConsumerRepository consumerRepository;

    @Override
    public Product getProduct(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        return unwrapProduct(product, productId);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        Optional<Product> databaseProduct = productRepository.findById(productId);
        Product unwrappedDatabaseProduct = unwrapProduct(databaseProduct, productId);
        Product newProduct = new Product(unwrappedDatabaseProduct);
        return productRepository.save(newProduct);
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    static Product unwrapProduct(Optional<Product> entity, Long id) {
        if (entity.isPresent())
            return entity.get();
        else
            throw new EntityNotFoundException(id, Product.class);
    }

}
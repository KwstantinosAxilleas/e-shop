package myspringbootproject.myspringbootproject.web;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import myspringbootproject.myspringbootproject.entity.Product;
import myspringbootproject.myspringbootproject.service.ConsumerService;
import myspringbootproject.myspringbootproject.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@Tag(name = "Product's Controller", description = "Manages products")
@RequestMapping("/product")
public class ProductController {


    ProductService productService;
    ConsumerService consumerService;

    @Operation(summary = "Retrieves a product by it's id", description = "Provides information about a product.")
	@ApiResponses(value ={
		@ApiResponse(responseCode = "404", description = "Product doesn't exist", content = @Content(schema = @Schema(implementation = Product.class))),
        @ApiResponse(responseCode = "200", description = "Successful retrieval of product", content = @Content(schema = @Schema(implementation = Product.class))),
	})
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        return new ResponseEntity<>(productService.getProduct(id), HttpStatus.OK);
    }

    @Operation(summary = "Creates a product", description = "Saves a product to the database.")
	@ApiResponse(responseCode = "201", description = "Product created successfully", content = @Content(schema = @Schema(implementation = Product.class)))
    @PostMapping("/create")
    public ResponseEntity<Product> saveProduct(@Valid @RequestBody Product item) {
        return new ResponseEntity<>(productService.saveProduct(item), HttpStatus.CREATED);
    }

    @Operation(summary = "Deletes a product", description = "Deletes a product from the database.")
	@ApiResponse(responseCode = "204", description = "Product deleted successfully", content = @Content(schema = @Schema(implementation = Product.class)))
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Adds a product to a cart", description = "Saves a product to the cart database.")
	@ApiResponse(responseCode = "201", description = "Product added successfully", content = @Content(schema = @Schema(implementation = Product.class)))
    @PostMapping("/addProduct/{productId}/consumer/{consumerId}/{quantity}")
    public ResponseEntity<HttpStatus> addProductToCart(@Valid @PathVariable Long consumerId, @PathVariable Long productId, @PathVariable Integer quantity) {
        consumerService.addToCart(consumerId, productId, quantity);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Retrieves all products", description = "Provides information about all products in database.")
	@ApiResponses(value ={
		@ApiResponse(responseCode = "404", description = "Products doesn't exist", content = @Content(schema = @Schema(implementation = Product.class))),
        @ApiResponse(responseCode = "200", description = "Successful retrieval of products", content = @Content(schema = @Schema(implementation = Product.class))),
	})
    @GetMapping("/all")
    public ResponseEntity<List<Product>> getProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

}

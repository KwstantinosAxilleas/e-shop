package myspringbootproject.myspringbootproject.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.http.MediaType;
import myspringbootproject.myspringbootproject.entity.Consumer;
import myspringbootproject.myspringbootproject.service.ProductService;
import myspringbootproject.myspringbootproject.service.ConsumerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Tag(name = "Consumer's Controller", description = "Create and retrieve consumers")
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

	ConsumerService consumerService;
	ProductService productService;

	@Operation(summary = "Retrieves a consumer by his id", description = "Provides information about a consumer.")
	@ApiResponses(value ={
		@ApiResponse(responseCode = "404", description = "Consumer doesn't exist", content = @Content(schema = @Schema(implementation = Consumer.class))),
        @ApiResponse(responseCode = "200", description = "Successful retrieval of consumer", content = @Content(schema = @Schema(implementation = Consumer.class))),
	})
	@GetMapping(value = "/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> findById(@PathVariable Long id) {
		return new ResponseEntity<>(consumerService.getConsumer(id).getUsername(), HttpStatus.OK);
	}

	@Operation(summary = "Registers a consumer", description = "Saves a consumer to the database.")
	@ApiResponse(responseCode = "201", description = "Consumer created successfully", content = @Content(schema = @Schema(implementation = Consumer.class)))
	@PostMapping("/register")
	public ResponseEntity<HttpStatus> createConsumer(@Valid @RequestBody Consumer consumer) {
		consumerService.saveConsumer(consumer);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
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
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.http.MediaType;
import myspringbootproject.myspringbootproject.entity.Item;
import myspringbootproject.myspringbootproject.entity.User;
import myspringbootproject.myspringbootproject.service.ItemService;
import myspringbootproject.myspringbootproject.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

	UserService userService;
	ItemService itemService;
	@GetMapping(value = "/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Retrieves a user by his id", description = "Provides information about a user")
	@ApiResponse(responseCode = "200", description = "Successful retrieval of user", content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class))))
	public ResponseEntity<String> findById(@PathVariable Long id) {
		return new ResponseEntity<>(userService.getUser(id).getUsername(), HttpStatus.OK);
	}

	@PostMapping("/register")
	public ResponseEntity<HttpStatus> createUser(@Valid @RequestBody User user) {
		userService.saveUser(user);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping(value = "/items/{id}" , produces = MediaType.APPLICATION_JSON_VALUE) 
	public ResponseEntity<List<Item>> getUsersItem(@PathVariable Long id) {
		return new ResponseEntity<>(itemService.getUserItems(id), HttpStatus.OK);
	}

	@DeleteMapping("/item/{itemId}")
    public ResponseEntity<HttpStatus> deleteItemFromUser(@PathVariable Long itemId) {
        userService.deleteItemFromUser(itemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
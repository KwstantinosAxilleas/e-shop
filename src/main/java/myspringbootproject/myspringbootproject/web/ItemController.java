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
import myspringbootproject.myspringbootproject.entity.Item;
import myspringbootproject.myspringbootproject.service.ItemService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;



@AllArgsConstructor
@RestController
@RequestMapping("/item")
public class ItemController {

    
    ItemService itemService;

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItem(@PathVariable Long id) {
        return new ResponseEntity<>(itemService.getItem(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Item> saveItem(@Valid @RequestBody Item item) {
        return new ResponseEntity<>(itemService.saveItem(item), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Item>> getItems() {
        return new ResponseEntity<>(itemService.getAllItems(), HttpStatus.OK);
    }

    @PostMapping("/{itemId}/user/{userId}")
    public ResponseEntity<Item> addItemToUser(@Valid @PathVariable Long itemId, @PathVariable Long userId) {
        return new ResponseEntity<>(itemService.saveItemToUser(itemId, userId), HttpStatus.OK);
    }
    
}

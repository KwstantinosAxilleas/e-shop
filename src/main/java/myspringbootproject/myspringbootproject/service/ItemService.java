package myspringbootproject.myspringbootproject.service;

import java.util.List;
import myspringbootproject.myspringbootproject.entity.Item;


public interface ItemService {
    Item getItem(Long id);
    Item saveItem(Item item);
    void deleteItem(Long id);
    List<Item> getAllItems();
    List<Item> getUserItems(Long userId);
    Item saveItemToUser(Long itemId, Long userId);
    }
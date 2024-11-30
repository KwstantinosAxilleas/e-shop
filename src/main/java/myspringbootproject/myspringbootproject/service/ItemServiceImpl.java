package myspringbootproject.myspringbootproject.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import myspringbootproject.myspringbootproject.entity.Item;
import myspringbootproject.myspringbootproject.entity.User;
import myspringbootproject.myspringbootproject.exception.EntityNotFoundException;
import myspringbootproject.myspringbootproject.repository.ItemRepository;
import myspringbootproject.myspringbootproject.repository.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {

    ItemRepository itemRepository;
    UserRepository userRepository;

    @Override
    public Item getItem(Long id) {
        Optional<Item> item = itemRepository.findById(id);
        return unwrapItem(item, id);
    }

    @Override
    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    @Override
    public List<Item> getAllItems() {
        return (List<Item>) itemRepository.findAll();
    }

    @Override
    public Item saveItemToUser(Long itemId, Long userId) {
        User user = UserServiceImpl.unwrapUser(userRepository.findById(userId), userId);
        Item item =  unwrapItem(itemRepository.findById(itemId), itemId);
        item.setUser(user);
        return itemRepository.save(item);
    }

    @Override
    public List<Item> getUserItems(Long userId) {
        return itemRepository.findByUserId(userId);
    }

    static Item unwrapItem(Optional<Item> entity, Long id) {
        if (entity.isPresent())
            return entity.get();
        else
            throw new EntityNotFoundException(id, Item.class);
    }

}
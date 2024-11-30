package myspringbootproject.myspringbootproject.repository;


import java.util.List;
import org.springframework.data.repository.CrudRepository;
import jakarta.transaction.Transactional;
import myspringbootproject.myspringbootproject.entity.Item;


public interface ItemRepository extends CrudRepository<Item, Long> {
    List <Item> findByUserId(Long userId);
    @Transactional
    void deleteByUserId(Long userId);
}
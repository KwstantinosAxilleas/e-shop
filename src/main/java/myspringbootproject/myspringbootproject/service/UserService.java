package myspringbootproject.myspringbootproject.service;

import jakarta.transaction.Transactional;
import myspringbootproject.myspringbootproject.entity.User;

public interface UserService {
    User getUser(Long id);
    User getUser(String username);
    User saveUser(User user);
    @Transactional
    void deleteItemFromUser(Long itemId);
}
package myspringbootproject.myspringbootproject.repository;


import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import myspringbootproject.myspringbootproject.entity.User;



public interface UserRepository extends CrudRepository<User, Long> {
	Optional<User> findByUsername(String username);
}
package myspringbootproject.myspringbootproject.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import myspringbootproject.myspringbootproject.entity.Consumer;

public interface ConsumerRepository extends CrudRepository<Consumer, Long> {
	Optional<Consumer> findByUsername(String username);
}
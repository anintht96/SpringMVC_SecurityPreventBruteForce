package spring.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.mvc.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUsername(String username);
	
}

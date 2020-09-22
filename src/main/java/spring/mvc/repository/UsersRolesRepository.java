package spring.mvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.mvc.entities.User;
import spring.mvc.entities.UsersRoles;

public interface UsersRolesRepository extends JpaRepository<UsersRoles, Integer>{

	List<UsersRoles> findByUser(User user);
}

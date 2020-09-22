package spring.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.mvc.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

	Role findByName(String name);
	
}

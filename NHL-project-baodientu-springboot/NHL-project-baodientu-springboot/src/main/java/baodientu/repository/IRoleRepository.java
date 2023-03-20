package baodientu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import baodientu.entity.Role;

public interface IRoleRepository extends JpaRepository<Role, Integer>{
	Role findByName(String name);
}

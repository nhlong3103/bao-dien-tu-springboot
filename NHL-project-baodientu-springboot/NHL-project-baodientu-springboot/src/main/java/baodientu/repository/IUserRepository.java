package baodientu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import baodientu.entity.User;

public interface IUserRepository extends JpaRepository<User, Integer>{
	User findByUsername(String username);
}

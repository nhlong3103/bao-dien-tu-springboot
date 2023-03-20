package baodientu.service;

import java.util.List;

import baodientu.entity.Role;
import baodientu.entity.User;

public interface IUserService {
	User saveUser(User user);
	
	Role saveRole(Role role);
	
	void addRoleToUser(String username, String roleName);
	
	User getUser(String username);
	
	List<User> getUsers();
}

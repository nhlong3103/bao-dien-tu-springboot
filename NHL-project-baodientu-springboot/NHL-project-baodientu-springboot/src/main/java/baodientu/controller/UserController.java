package baodientu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import baodientu.entity.Role;
import baodientu.entity.User;
import baodientu.form.RoleToUserForm;
import baodientu.service.IUserService;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("api/baodientu-springboot")
@RequiredArgsConstructor
public class UserController {

	@Autowired
	private IUserService userService;
	
	@GetMapping("/users")
	public List<User> getUsers(){
		return userService.getUsers();
	}
	
	@PostMapping("/users/save")
	public ResponseEntity<User> saveUser(@RequestBody User user){
		return ResponseEntity.ok().body(userService.saveUser(user));
	}
	
	@PostMapping("/role/save")
	public ResponseEntity<Role> saveRole(@RequestBody Role role){
		return ResponseEntity.ok().body(userService.saveRole(role));
	}
	
	@PostMapping("/role/addtouser")
	public ResponseEntity<?> saveUser(@RequestBody RoleToUserForm form){
		userService.addRoleToUser(form.getUsername(), form.getRoleName());
		return ResponseEntity.ok().build();
	}
}

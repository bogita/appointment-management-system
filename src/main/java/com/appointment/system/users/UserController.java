package com.appointment.system.users;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

	private UserRepository repository;

	public UserController(UserRepository repository){
		this.repository = repository;
	}

	@GetMapping("/{id}")
	public User getUser(@PathVariable(name = "id") Long id){
		return repository.findOne(id);
	}

	@GetMapping("/all")
	public Iterable<User> getUsers(){
		return repository.findAll();
	}

	@PutMapping("/update")
	public void updateUser(@RequestBody User user){
		repository.save(user);
	}

	@PostMapping("/create")
	public User createUser(@RequestBody User user){
		repository.save(user);
		return user;
	}

	@DeleteMapping("/delete/{id}")
	public void deleteUser(@PathVariable(name = "id") Long id){
		repository.delete(id);
	}

}

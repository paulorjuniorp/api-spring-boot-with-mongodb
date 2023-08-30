package com.paulorjuniorp.apispringbootwithmongodb.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.paulorjuniorp.apispringbootwithmongodb.domain.Post;
import com.paulorjuniorp.apispringbootwithmongodb.domain.User;
import com.paulorjuniorp.apispringbootwithmongodb.dto.UserDTO;
import com.paulorjuniorp.apispringbootwithmongodb.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> userList = userService.findAll();
		List<UserDTO> userDTOList = userList.stream()
				.map(userDTO -> new UserDTO(userDTO)).collect(Collectors.toList());
		return ResponseEntity.ok().body(userDTOList);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id){
		User user = userService.findById(id);
		UserDTO userDTO = new UserDTO(user);
		
		return ResponseEntity.ok().body(userDTO);
	}
	
	@GetMapping(value = "/{id}/posts")
	public ResponseEntity<List<Post>> findPost(@PathVariable String id){
		User user = userService.findById(id);
		
		return ResponseEntity.ok().body(user.getPosts());
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDTO){
		User entity = userService.fromDTO(objDTO);
		entity = userService.insert(entity);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@RequestBody UserDTO objDTO, @PathVariable String id) {
		User entity = userService.fromDTO(objDTO);
		objDTO.setId(id);
		userService.update(entity);

		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id){
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}
}

package com.paulorjuniorp.apispringbootwithmongodb.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}

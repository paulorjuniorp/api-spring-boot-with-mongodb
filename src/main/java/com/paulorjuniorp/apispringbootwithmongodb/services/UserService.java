package com.paulorjuniorp.apispringbootwithmongodb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulorjuniorp.apispringbootwithmongodb.domain.User;
import com.paulorjuniorp.apispringbootwithmongodb.dto.UserDTO;
import com.paulorjuniorp.apispringbootwithmongodb.repository.UserRepository;
import com.paulorjuniorp.apispringbootwithmongodb.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findById(String id) {
		User user = userRepository.findById(id).orElse(null);
		if (user == null) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		}
		
		return user;
	}
	
	public User insert(User obj) {
		return userRepository.insert(obj);
	}
	
	public User fromDTO(UserDTO objDTO) {
		return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
	}
	
	public User update(User obj) {
		User entity = findById(obj.getId());
		updateData(entity, obj);
		
		return userRepository.save(entity);
	}
	
	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
	}

	public void delete(String id) {
		User user = findById(id);
		userRepository.delete(user);
	}
}

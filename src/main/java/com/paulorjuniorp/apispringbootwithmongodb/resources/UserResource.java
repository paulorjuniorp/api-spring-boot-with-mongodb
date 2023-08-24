package com.paulorjuniorp.apispringbootwithmongodb.resources;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paulorjuniorp.apispringbootwithmongodb.domain.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	public List<User> findAll() {
		
	}
}

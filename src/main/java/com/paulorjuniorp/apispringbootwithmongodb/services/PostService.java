package com.paulorjuniorp.apispringbootwithmongodb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulorjuniorp.apispringbootwithmongodb.domain.Post;
import com.paulorjuniorp.apispringbootwithmongodb.repository.PostRepository;
import com.paulorjuniorp.apispringbootwithmongodb.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	public Post findById(String id) {
		Post post = postRepository.findById(id).orElse(null);
		if (post == null) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		}
		
		return post;
	}
	
}

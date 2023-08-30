package com.paulorjuniorp.apispringbootwithmongodb.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.paulorjuniorp.apispringbootwithmongodb.domain.Post;
import com.paulorjuniorp.apispringbootwithmongodb.domain.User;
import com.paulorjuniorp.apispringbootwithmongodb.dto.AuthorDTO;
import com.paulorjuniorp.apispringbootwithmongodb.repository.PostRepository;
import com.paulorjuniorp.apispringbootwithmongodb.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		formatoData.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, formatoData.parse("21/12/2023"), "Partiu Viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
		Post post2 = new Post(null, formatoData.parse("26/09/2023"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
	}

}

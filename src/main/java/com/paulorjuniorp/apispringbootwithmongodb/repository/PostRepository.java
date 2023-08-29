package com.paulorjuniorp.apispringbootwithmongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.paulorjuniorp.apispringbootwithmongodb.domain.Post;

public interface PostRepository extends MongoRepository<Post, String> {

}

package com.practice.microservices.client;

import com.practice.microservices.dto.PostDto;
import com.practice.microservices.dto.UserDto;
import com.practice.microservices.exception.ErrorResponse;
import com.practice.microservices.model.Post;
import com.practice.microservices.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class PostClient {
    @Value("${users-server-url}")
    private String usersUrl;
    protected final RestTemplate rest = new RestTemplate();

    private final PostRepository postRepository;

    public ResponseEntity<Object> create(Post post) {
        try {
            UserDto user = rest.getForObject(usersUrl + "/" + post.getAuthorId(), UserDto.class);
            post.setCreated(LocalDateTime.now());
            postRepository.save(post);

            PostDto postDto = new PostDto();
            postDto.setId(post.getId());
            postDto.setDescription(post.getDescription());
            postDto.setCreated(post.getCreated());
            postDto.setAuthor(user);

            return new ResponseEntity<>(post, HttpStatus.CREATED);
        } catch (HttpClientErrorException e) {
            ErrorResponse errorResponse = e.getResponseBodyAs(ErrorResponse.class);
            return new ResponseEntity<>(errorResponse, e.getStatusCode());
        }
    }

    public Post findById(int postId) {
        return postRepository.findById(postId).orElseThrow();
    }
}

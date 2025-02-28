package com.practice.microservices.client;

import com.practice.microservices.dto.PostDto;
import com.practice.microservices.model.Post;
import com.practice.microservices.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class PostClient extends BaseClient {
    @Value("${users-server-url}")
    private String usersUrl;

    private final PostRepository postRepository;

    public ResponseEntity<Object> create(Post post) {
        ResponseEntity<Object> responseEntity = get(usersUrl + "/" + post.getAuthorId());
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            post.setCreated(LocalDateTime.now());
            postRepository.save(post);

            PostDto postDto = new PostDto();
            postDto.setId(post.getId());
            postDto.setDescription(post.getDescription());
            postDto.setCreated(post.getCreated());
            return new ResponseEntity<>(post, HttpStatus.CREATED);
        }
        return responseEntity;
    }

    public Post findById(int postId) {
        return postRepository.findById(postId).orElseThrow();
    }
}

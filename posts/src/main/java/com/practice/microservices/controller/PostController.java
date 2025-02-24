package com.practice.microservices.controller;

import com.practice.microservices.dto.UserDto;
import com.practice.microservices.model.Post;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private RestTemplate restTemplate = new RestTemplate();
    @Value("${users-server-url}")
    private String usersUrl;

    // HttpClient
    // Unirest
    // OkHttp
    // WebClient

    private final List<Post> posts = new ArrayList<>();
    private int nextId = 1;

    @PostMapping
    public Post create(@RequestBody Post post) {
        // TODO: существует пользователь по идентификатору
        //  post.getAuthorId()
        System.out.println(usersUrl);

        UserDto user = restTemplate.getForObject(usersUrl + "/" + post.getAuthorId(), UserDto.class);
        System.out.println(user);

        post.setId(nextId++);
        post.setCreated(LocalDateTime.now());
        posts.add(post);
        return post;
    }

    @GetMapping("/{id}")
    public Post findById(@PathVariable int id) {
        return posts.stream()
                .filter(post -> post.getId() == id)
                .findFirst()
                .orElseThrow();
    }

    // Стримы
    // Операции
    // - промежуточные (filter, map, peek, sorted)
    // - терминальные (toList, collect, count, forEach, findFirst)

    // Добавление
    // - ArrayList - O(1)/O(n)
    // - HashMap - O(1)

    // Асимптотическая сложность
    // O(log(n)) - логарифмическая сложность
    // O(n) - линейная сложность
    // O(1) - константная сложность
    // O(n2) - квадратичная сложность
}

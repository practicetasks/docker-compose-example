package com.practice.microservices.controller;

import com.practice.microservices.client.PostClient;
import com.practice.microservices.dto.PostDto;
import com.practice.microservices.dto.UserDto;
import com.practice.microservices.exception.ErrorResponse;
import com.practice.microservices.model.Post;
import com.practice.microservices.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;


@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostClient postClient;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Post post) {
        return postClient.create(post);
    }

    @GetMapping("/{id}")
    public Post findById(@PathVariable int id) {
        return postClient.findById(id);
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

package com.practice.microservices.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostDto {
    int id;
    String description;
    LocalDateTime created;
    UserDto author;
}

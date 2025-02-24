package com.practice.microservices.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Post {
    int id;
    String description;
    LocalDateTime created;
    int authorId;
}

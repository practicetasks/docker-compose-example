package com.practice.microservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.practice.microservices.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}

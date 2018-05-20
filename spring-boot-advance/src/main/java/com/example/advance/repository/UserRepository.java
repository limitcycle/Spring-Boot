package com.example.advance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.advance.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByUsernameAndPassword(String username, String password);
}

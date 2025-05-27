package com.example.finalMusicify.repository;

import com.example.finalMusicify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);


}

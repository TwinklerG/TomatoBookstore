package com.tomatobookstore.tomatobookstore.repository;

import com.tomatobookstore.tomatobookstore.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);
}

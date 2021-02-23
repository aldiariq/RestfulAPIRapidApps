package com.aldiariq.rapidapps.repository;

import com.aldiariq.rapidapps.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByemailuser(String emailuser);
}

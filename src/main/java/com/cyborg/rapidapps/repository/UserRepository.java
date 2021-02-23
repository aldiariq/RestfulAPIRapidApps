package com.cyborg.rapidapps.repository;

import com.cyborg.rapidapps.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByemailuser(String emailuser);
}

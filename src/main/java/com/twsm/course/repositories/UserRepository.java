package com.twsm.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twsm.course.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
}

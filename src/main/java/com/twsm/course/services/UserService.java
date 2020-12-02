package com.twsm.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twsm.course.entities.User;
import com.twsm.course.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    
    public List<User> findAll() {
        return repository.findAll();        
    }
    
    public User findById(Long id) {
        Optional<User> opt = repository.findById(id);
        return opt.get(); 
    }
}

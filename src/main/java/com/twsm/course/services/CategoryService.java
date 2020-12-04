package com.twsm.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twsm.course.entities.Category;
import com.twsm.course.repositories.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    
    public List<Category> findAll() {        
        return categoryRepository.findAll();
    }
    
    public Category findById(Long id) {
        Optional<Category> opt = categoryRepository.findById(id);
        return opt.get();
    }
}

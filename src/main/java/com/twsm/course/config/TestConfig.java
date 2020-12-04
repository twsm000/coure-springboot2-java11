package com.twsm.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.twsm.course.entities.Category;
import com.twsm.course.entities.Order;
import com.twsm.course.entities.User;
import com.twsm.course.entities.enums.OrderStatus;
import com.twsm.course.repositories.CategoryRepository;
import com.twsm.course.repositories.OrderRepository;
import com.twsm.course.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        
        Category c1 = new Category(null, "Eletronics");
        Category c2 = new Category(null, "Books");
        Category c3 = new Category(null, "Computers");
        
        categoryRepository.saveAll(Arrays.asList(c1, c2, c3));
        
        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "12345678", "4328765");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "43218765", "'1234567");

        Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), 
                OrderStatus.PAID, u1);
        Order o2 = new Order(null, Instant.parse("2020-11-29T00:00:00Z"), 
                OrderStatus.WAITING_PAYMENT, u2);
        Order o3 = new Order(null, Instant.parse("2021-03-29T01:00:00Z"), 
                OrderStatus.WAITING_PAYMENT, u2);

        userRepository.saveAll(Arrays.asList(u1, u2));
        orderRepository.saveAll(Arrays.asList(o1, o2, o3));
    }
}

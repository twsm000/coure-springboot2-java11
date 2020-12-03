package com.twsm.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.twsm.course.entities.Order;
import com.twsm.course.entities.User;
import com.twsm.course.repositories.OrderRepository;
import com.twsm.course.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void run(String... args) throws Exception {
        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "12345678", "4328765");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "43218765", "'1234567");

        Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), u1);
        Order o2 = new Order(null, Instant.parse("2020-11-29T00:00:00Z"), u2);
        Order o3 = new Order(null, Instant.parse("2021-03-29T01:00:00Z"), u2);

        userRepository.saveAll(Arrays.asList(u1, u2));
        orderRepository.saveAll(Arrays.asList(o1, o2, o3));
    }
}

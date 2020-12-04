package com.twsm.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.twsm.course.entities.Category;
import com.twsm.course.entities.Order;
import com.twsm.course.entities.OrderItem;
import com.twsm.course.entities.Payment;
import com.twsm.course.entities.Product;
import com.twsm.course.entities.User;
import com.twsm.course.entities.enums.OrderStatus;
import com.twsm.course.repositories.CategoryRepository;
import com.twsm.course.repositories.OrderItemRepository;
import com.twsm.course.repositories.OrderRepository;
import com.twsm.course.repositories.ProductRepository;
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

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public void run(String... args) throws Exception {

        Category c1 = new Category(null, "Eletronics");
        Category c2 = new Category(null, "Books");
        Category c3 = new Category(null, "Computers");

        Product p1 = new Product(null, "The Lord of the Rings", "Lore ipsum", 90.5, "");
        Product p2 = new Product(null, "Smart TV", "Lore ipsum", 2190.0, "");
        Product p3 = new Product(null, "Macbook Pro", "Lore ipsum", 15999.99, "");
        Product p4 = new Product(null, "PC Gamer", "Lore ipsum", 11000.99, "");
        Product p5 = new Product(null, "Rails for Dummies", "Lore ipsum", 100.99, "");

        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
        categoryRepository.saveAll(Arrays.asList(c1, c2, c3));
        
        p1.getCategories().add(c2);
        p2.getCategories().addAll(Arrays.asList(c1, c3));
        p3.getCategories().add(c3);
        p4.getCategories().add(c3);
        p5.getCategories().add(c2);
        
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
        
        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "12345678", "4328765");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "43218765", "'1234567");

        Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
        Order o2 = new Order(null, Instant.parse("2020-11-29T00:00:00Z"), OrderStatus.WAITING_PAYMENT, u2);
        Order o3 = new Order(null, Instant.parse("2021-03-29T01:00:00Z"), OrderStatus.WAITING_PAYMENT, u2);

        userRepository.saveAll(Arrays.asList(u1, u2));
        orderRepository.saveAll(Arrays.asList(o1, o2, o3));
        
        OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
        OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
        OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
        OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());
        
        orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));
        
        
        Payment pay1 = new Payment(null, Instant.parse("2019-06-20T21:53:07Z"), o1);
        o1.setPayment(pay1);
        orderRepository.save(o1);
    }
}

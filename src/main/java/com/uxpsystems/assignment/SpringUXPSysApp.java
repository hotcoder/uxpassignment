package com.uxpsystems.assignment;

import com.uxpsystems.assignment.model.User;
import com.uxpsystems.assignment.repository.UserRepository;
import com.uxpsystems.assignment.util.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringUXPSysApp implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringUXPSysApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        User user1 = new User();
        user1.setUserName("user1");
        user1.setPassword("user1password");
        user1.setStatus(StatusEnum.Activated.toString());

        userRepository.save(user1);

        User user2 = new User();
        user2.setUserName("user1");
        user2.setPassword("user1password");
        user2.setStatus(StatusEnum.Activated.toString());

        userRepository.save(user2);

    }
}

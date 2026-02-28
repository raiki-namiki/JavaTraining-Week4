package com.example.taskapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.taskapp.entity.UserAccount;          
import com.example.taskapp.repository.UserAccountRepository; 

@SpringBootApplication
public class TaskAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskAppApplication.class, args);
    }

    @Bean
    public CommandLineRunner dataInit(UserAccountRepository repository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (repository.findByUsername("testuser").isEmpty()) {
                repository.save(new UserAccount("testuser", passwordEncoder.encode("password"), "USER"));
                System.out.println("初期ユーザー(testuser)を登録しました！");
            }
        };
    }
}
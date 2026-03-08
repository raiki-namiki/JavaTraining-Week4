package com.example.taskapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            //アクセス権限の設定
            .authorizeHttpRequests(authz -> authz
                //誰でもアクセス可
                .requestMatchers("/css/**").permitAll()
                //それ以外のすべてのURLは、ログイン必要
                .anyRequest().authenticated()
            )
            //フォームログインの設定
            .formLogin(form -> form
            
                .permitAll()
                .defaultSuccessUrl("/tasks", true) // ログイン成功後は /tasksへ
            )
            //ログアウトの設定
            .logout(logout -> logout
                .permitAll()
            );

        return http.build();
    }
}
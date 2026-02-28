package com.example.taskapp.repository;

import com.example.taskapp.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    
    // ユーザー名でDBから検索
    Optional<UserAccount> findByUsername(String username);
}
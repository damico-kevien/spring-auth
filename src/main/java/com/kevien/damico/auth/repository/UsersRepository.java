package com.kevien.damico.auth.repository;

import com.kevien.damico.auth.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, Long>{
    
    Optional<User> findUserByUsername(String username);

}

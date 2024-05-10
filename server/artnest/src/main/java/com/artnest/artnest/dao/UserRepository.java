package com.artnest.artnest.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.artnest.artnest.entities.Role;
import com.artnest.artnest.entities.User;

@Repository
@Component
public interface UserRepository extends CrudRepository<User,Integer> {

    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findByEmail(@Param("email") String email);

    public User findByEmailAndPassword(String email, String password);
    User findByRole(Role role);


    
}

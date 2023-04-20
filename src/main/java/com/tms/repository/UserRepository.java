package com.tms.repository;

import com.tms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Modifying
    @Query (value = "from User where is_deleted = false AND id=:id")
    Optional<User> findByIs_deleted(int id);
    @Modifying
    @Query (value = "from User where is_deleted = false")
    Optional<User> getAllByIs_deleted();




    Optional<User> findByName(String username);
    Optional<User> findByLoginUser(String loginUser);
}

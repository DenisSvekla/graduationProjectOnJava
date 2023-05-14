package com.tms.repository;

import java.util.Optional;

import com.tms.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * @param id
     * @return
     */
    @Query(value = "from User where is_deleted = false AND id=:id")
    Optional<User> findByIs_deleted(int id);

    @Query(value = "from User where is_deleted = false")
    Optional<User> getAllByIs_deleted();

    @Query(value = "from User where loginUser=:loginUser")
    Optional<User> getUserByUserLogin(String loginUser);

    Optional<User> findByLoginUser(String loginUser);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE user_table SET is_deleted =true WHERE id = :id",
            countQuery = "SELECT * from user_table WHERE id = :id")
    Integer deleteUser(int id);


}

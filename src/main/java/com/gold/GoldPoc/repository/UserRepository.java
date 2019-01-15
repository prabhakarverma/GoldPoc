package com.gold.GoldPoc.repository;

import com.gold.GoldPoc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Prabhakar Verma
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.name = (:name) and u.password = (:password)")
    User findByUserNameAndPassword(@Param("name") String name, @Param("password") String password);

    @Query("SELECT u FROM User u WHERE u.email = (:email)")
    User findByEmail(@Param("email") String email);
}
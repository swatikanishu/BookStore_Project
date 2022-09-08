package com.example.bookstore_project.repo;

import com.example.bookstore_project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {


    @Query(value = "SELECT * FROM book_store WHERE user_id = user_id and email=:email", nativeQuery = true)

    List<User> findUserByemail(String email);
}

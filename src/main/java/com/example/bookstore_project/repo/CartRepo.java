package com.example.bookstore_project.repo;

import com.example.bookstore_project.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo  extends JpaRepository <Cart, Long> {


}

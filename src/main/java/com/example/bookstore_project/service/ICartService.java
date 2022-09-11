package com.example.bookstore_project.service;

import com.example.bookstore_project.dto.CartDto;
import com.example.bookstore_project.model.Cart;

import java.util.List;

public interface ICartService {
    Cart addCart(CartDto cartDto);

    List<Cart> findAll();

    Cart FindById(Long id);

    String deleteById(Long id);

    Cart updateCartData(Long id, CartDto cartDto);

    Cart changeCartQty(Long id, int quantity);
}

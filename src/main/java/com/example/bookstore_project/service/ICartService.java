package com.example.bookstore_project.service;

import com.example.bookstore_project.dto.CartDto;
import com.example.bookstore_project.model.Cart;

import java.util.List;

public interface ICartService {
    String addCart(CartDto cartDto);

    List<Cart> findAll();

    Cart FindById(Long cartid);

    String deleteById(Long cartid);

    Cart updateCartData(Long cartid, CartDto cartDto);

    Cart changeCartQty(Long cartid, int quantity);

    List<Cart> getCartDetailsByUserId(Long userid);

    List<Cart> getCartDetailsByToken(String token);
}

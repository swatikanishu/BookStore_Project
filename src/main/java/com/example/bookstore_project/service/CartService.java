package com.example.bookstore_project.service;

import com.example.bookstore_project.dto.CartDto;
import com.example.bookstore_project.exception.BookException;
import com.example.bookstore_project.exception.CartException;
import com.example.bookstore_project.model.Book;
import com.example.bookstore_project.model.Cart;
import com.example.bookstore_project.model.User;
import com.example.bookstore_project.repo.BookRepo;
import com.example.bookstore_project.repo.CartRepo;
import com.example.bookstore_project.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements  ICartService{
@Autowired
CartRepo cartRepo;
@Autowired
UserRepo userRepo;
@Autowired
    BookRepo bookRepo;
    @Override
    public Cart addCart(CartDto cartDto) {
        Optional<User> user =userRepo.findById(cartDto.getUserid());
        Optional<Book> book =bookRepo.findById(cartDto.getBookId());
        if(user.isPresent() && book.isPresent()){
            Cart cartDetails = new Cart(user.get(), book.get(), cartDto.getQuantity());
            cartRepo.save(cartDetails);
            return  cartDetails;

        }else
            throw new CartException(" userid and bookid is invalid");

    }

    @Override
    public List<Cart> findAll() {
        List<Cart> cartdetails  = cartRepo.findAll();
        return cartdetails;
    }

    @Override
    public Cart FindById(Long id) {
        Cart cart = cartRepo.findById(id).orElse(null);
        if (cart != null) {
            return cart;

        }else
            throw new CartException("card id is not found");
    }

    @Override
    public String deleteById(Long id) {
        Cart findById = cartRepo.findById(id).orElse(null);
        if (findById != null) {
            cartRepo.deleteById(id);
            return "data is deleted";

        } else throw new CartException("id is not invalid");

    }

    @Override
    public Cart updateCartData(Long id, CartDto cartDto) {
        Optional<Book> book =bookRepo.findById(cartDto.getBookId());
        Cart editcart= cartRepo.findById(id).orElse(null);
        if (editcart != null) {
            editcart.setBook(book.get());
            editcart.setQuantity(cartDto.getQuantity());
            return cartRepo.save(editcart);
        } else throw new CartException("Id and quantity is not present ");

    }

    @Override
    public Cart changeCartQty(Long id, int quantity) {
        Cart cart = cartRepo.findById(id).orElse(null);
        if(cart == null){
            throw new CartException("id is not found");
        }
        cart.setQuantity(quantity);
        return cartRepo.save(cart);
    }
    }









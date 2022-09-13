package com.example.bookstore_project.service;

import com.example.bookstore_project.dto.CartDto;
import com.example.bookstore_project.exception.CartException;
import com.example.bookstore_project.model.Book;
import com.example.bookstore_project.model.Cart;
import com.example.bookstore_project.model.User;
import com.example.bookstore_project.repo.BookRepo;
import com.example.bookstore_project.repo.CartRepo;
import com.example.bookstore_project.repo.UserRepo;
import com.example.bookstore_project.utility.TokenUtil;
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
@Autowired
    TokenUtil tokenUtil;
    @Override
    public String addCart(CartDto cartDto) {
        Optional<User> user =userRepo.findById(cartDto.getUserid());
        Optional<Book> book =bookRepo.findById(cartDto.getBookId());
        if(user.isPresent() && book.isPresent()){
            Cart cartDetails = new Cart(user.get(), book.get(), cartDto.getQuantity());
            cartRepo.save(cartDetails);
            String token = tokenUtil.createToken(cartDetails.getCartId());
            return  token;

        }else
            throw new CartException(" userid and bookid is invalid");

    }

    @Override
    public List<Cart> findAll() {
        List<Cart> cartdetails  = cartRepo.findAll();
        return cartdetails;
    }
// finding the car details using carti
    @Override
    public Cart FindById(Long cartid) {
        Cart cart = cartRepo.findById(cartid).orElse(null);
        if (cart != null) {
            return cart;

        }else
            throw new CartException("card id is not found");
    }

    @Override
    public String deleteById(Long cartid) {
        Cart findById = cartRepo.findById(cartid).orElse(null);
        if (findById != null) {
            cartRepo.deleteById(cartid);
            return "data is deleted";

        } else throw new CartException("id is not invalid");

    }

    @Override
    public Cart updateCartData(Long id, CartDto cartDto) {
        Optional<User>user= userRepo.findById(cartDto.getUserid());
        Optional<Book> book =bookRepo.findById(cartDto.getBookId());
        Cart editcart= cartRepo.findById(id).orElse(null);
        if (editcart != null) {
            editcart.setUser(user.get());
            editcart.setBook(book.get());
            editcart.setQuantity(cartDto.getQuantity());
            return cartRepo.save(editcart);
        } else throw new CartException("Id and quantity is not present ");

    }

    @Override
    public Cart changeCartQty(Long cartid, int quantity) {
        Cart cart = cartRepo.findById(cartid).orElse(null);
        if(cart == null){
            throw new CartException("id is not found");
        }
        cart.setQuantity(quantity);
        return cartRepo.save(cart);
    }

    @Override
    public List<Cart> getCartDetailsByUserId(Long userid) {
        List<Cart> userCartList = cartRepo.getCartListWithUserId(userid);
        if(userCartList.isEmpty()){
            throw new CartException("Cart is Empty!");
        }else
            return userCartList;
    }
    @Override
    public List<Cart> getCartDetailsByToken(String token) {
        Long userId = tokenUtil.decodeToken(token);
        List<Cart> userCartList = cartRepo.getCartListWithUserId(userId);
        if(userCartList.isEmpty()){
            throw new CartException("Cart is Empty!");
        }else
            return userCartList;
    }

}










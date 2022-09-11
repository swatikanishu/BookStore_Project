package com.example.bookstore_project.service;

import com.example.bookstore_project.dto.OrderDto;
import com.example.bookstore_project.exception.CartException;
import com.example.bookstore_project.exception.OrderException;
import com.example.bookstore_project.model.Book;
import com.example.bookstore_project.model.Order;
import com.example.bookstore_project.model.User;
import com.example.bookstore_project.repo.BookRepo;
import com.example.bookstore_project.repo.OrderRepo;
import com.example.bookstore_project.repo.UserRepo;
import com.example.bookstore_project.utility.EmailSenderService;
import com.example.bookstore_project.utility.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements  IOrderService{
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    BookRepo bookRepo;
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    EmailSenderService emailSenderService;
    @Override
    public String addOrder(OrderDto orderDto) {
        Optional<User> user =userRepo.findById(orderDto.getUserid());
        Optional<Book> book =bookRepo.findById(orderDto.getBookId());
        if(user.isPresent() && book.isPresent()){
            Order orderDetails = new Order(orderDto.getDate(), orderDto.getPrice(),
                    orderDto.getQuantity(),orderDto.getAddress(),user.get(), book.get(),orderDto.isCancel());
            orderRepo.save(orderDetails);
            String token = tokenUtil.createToken(orderDetails.getOrderID());
            emailSenderService.sendEmail(user.get().getEmail_address(), "Added Your Details", token);
            return  token;

        }else
            throw new CartException(" userid and bookid is invalid");

    }

    @Override
    public List<Order> findAll() {
        List<Order> orderdetails  = orderRepo.findAll();
        return orderdetails;
    }

    @Override
    public Order FindById(Long id) {
        Order order = orderRepo.findById(id).orElse(null);
        if (order != null) {
            return order;

        }else
            throw new OrderException("order id is not found");
    }

    @Override
    public String deleteById(Long id) {
        Order findById = orderRepo.findById(id).orElse(null);
        if (findById != null) {
            orderRepo.deleteById(id);
            return "data is deleted";

        } else throw new OrderException("Order id is  invalid");

    }

    @Override
    public String updateOrderData(Long id, OrderDto orderDto) {
        Optional<Book> book =bookRepo.findById(orderDto.getBookId());
        Optional<User> user =userRepo.findById(orderDto.getUserid());

        Order editorder= orderRepo.findById(id).orElse(null);
        if (editorder != null) {
            editorder.setBook(book.get());
            editorder.setUser(user.get());
            editorder.setPrice(orderDto.getPrice());
            editorder.setQuantity(orderDto.getQuantity());
            editorder.setAddress(orderDto.getAddress());
            editorder.setCancel(orderDto.isCancel());
            String token = tokenUtil.createToken(editorder.getOrderID());
            emailSenderService.sendEmail(user.get().getEmail_address(), "Edit Your Details",  token);
            return token;
        } else throw new CartException("Id and order is not present ");

    }

    }







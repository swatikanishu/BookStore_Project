package com.example.bookstore_project.controller;

import com.example.bookstore_project.dto.BookDto;
import com.example.bookstore_project.dto.CartDto;
import com.example.bookstore_project.dto.ResponseDto;

import com.example.bookstore_project.model.Cart;
import com.example.bookstore_project.model.Order;
import com.example.bookstore_project.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.bookstore_project.dto.OrderDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Order")
public class OrderController {
    @Autowired
    IOrderService orderService;

    @PostMapping("/add")
    public ResponseEntity<ResponseDto> addBook(@Valid @RequestBody OrderDto orderDto) {
        String order = orderService.addOrder(orderDto);
        ResponseDto responseDTO = new ResponseDto("Add order Success", order);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @GetMapping("/getAll")
    public ResponseEntity<ResponseDto> findAllDetail() {
        List<Order> orderList = orderService.findAll();
        ResponseDto responseDTO = new ResponseDto("** All order List ** ", orderList);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @GetMapping("/get/{Id}")
    public ResponseEntity<ResponseDto> FindById(@PathVariable Long Id) {
        Order response = orderService.FindById(Id);
        ResponseDto responseDto = new ResponseDto("***All Details cart list using Id***", response);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{Id}")
    public ResponseEntity<ResponseDto> deleteById(@PathVariable Long Id) {
        orderService.deleteById(Id);
        ResponseDto reponseDTO = new ResponseDto("**order Data deleted successfully ** ", "deleted id " + Id);
        return new ResponseEntity(reponseDTO, HttpStatus.ACCEPTED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDto> editData(@PathVariable Long id, @Valid @RequestBody OrderDto orderDto) {
        String order = orderService.updateOrderData(id, orderDto);
        ResponseDto responseDTO = new ResponseDto("Updated Order Details Successfully", order);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
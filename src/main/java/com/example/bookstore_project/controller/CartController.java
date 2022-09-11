package com.example.bookstore_project.controller;

import com.example.bookstore_project.dto.BookDto;
import com.example.bookstore_project.dto.CartDto;
import com.example.bookstore_project.dto.ResponseDto;
import com.example.bookstore_project.model.Book;
import com.example.bookstore_project.model.Cart;
import com.example.bookstore_project.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Cart")
public class CartController {
    @Autowired
    ICartService cartService;


    @PostMapping("/add")
    public ResponseEntity<ResponseDto> addBook(@Valid @RequestBody CartDto cartDto) {
        Cart cart = cartService.addCart(cartDto);
        ResponseDto responseDTO = new ResponseDto("cart details added", cart);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @GetMapping("/getAll")
    public ResponseEntity<ResponseDto> findAllDetail() {
        List<Cart> cartList = cartService.findAll();
        ResponseDto responseDTO = new ResponseDto("** All cart List ** ", cartList);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @GetMapping("/get/{Id}")
    public ResponseEntity<ResponseDto> FindById(@PathVariable Long Id) {
        Cart response = cartService.FindById(Id);
        ResponseDto responseDto = new ResponseDto("***All Details cart list using Id***", response);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{Id}")
    public ResponseEntity<ResponseDto> deleteById(@PathVariable Long Id) {
        cartService.deleteById(Id);
        ResponseDto reponseDTO = new ResponseDto("**cart Data deleted successfully ** ", "deleted id " + Id);
        return new ResponseEntity(reponseDTO, HttpStatus.ACCEPTED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDto> editData(@PathVariable Long id, @Valid @RequestBody CartDto cartDto) {
        Cart cartData = cartService.updateCartData(id, cartDto);
        ResponseDto responseDTO = new ResponseDto("Updated Cart Details Successfully", cartData);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @PostMapping("/update-qty")
    public ResponseEntity<ResponseDto> changeBookQuantity(@RequestParam Long id, @RequestParam int quantity) {
        Cart cart = cartService.changeCartQty(id, quantity);
        ResponseDto responseDTO = new ResponseDto("Cart quantity changed successfully", cart);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}

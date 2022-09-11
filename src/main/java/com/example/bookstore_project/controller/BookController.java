package com.example.bookstore_project.controller;

import com.example.bookstore_project.dto.BookDto;
import com.example.bookstore_project.dto.ResponseDto;
import com.example.bookstore_project.dto.UserDto;
import com.example.bookstore_project.model.Book;
import com.example.bookstore_project.model.User;
import com.example.bookstore_project.service.IBookService;
import com.example.bookstore_project.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
    @RequestMapping("/Book")
    public class BookController {
    @Autowired
    IBookService bookService;


    @PostMapping("/add")
    public ResponseEntity<ResponseDto> addBook(@Valid @RequestBody BookDto bookDTO) {
        Book book = bookService.addBook(bookDTO);
        ResponseDto responseDTO = new ResponseDto("Add Book  Success", book);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<ResponseDto> findAllDetail() {
        List<Book> userList = bookService.findAll();
        ResponseDto responseDTO = new ResponseDto("** All book List ** ", userList);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/get/{Id}")
    public ResponseEntity<ResponseDto> FindById(@PathVariable Long Id) {
        Book response = bookService.FindById(Id);
        ResponseDto responseDto = new ResponseDto("***All Details user list using Id***", response);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{Id}")
    public ResponseEntity<ResponseDto> deleteById(@PathVariable Long Id) {
        bookService.deleteById(Id);
        ResponseDto reponseDTO = new ResponseDto("**book Data deleted successfully ** ", "deleted id " + Id);
        return new ResponseEntity(reponseDTO, HttpStatus.ACCEPTED);
    }

    @GetMapping("/getBybookName/{bookName}")
    public ResponseEntity<ResponseDto> getBookByName(@PathVariable String bookName) {
        Book response = bookService.findBookByName(bookName);
        ResponseDto responseDTO = new ResponseDto("successfully record founded for given book name: ", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDto> editData(@PathVariable Long id, @Valid @RequestBody BookDto bookDTO) {
        Book bookData = bookService.updateBookData(id, bookDTO);
        ResponseDto responseDTO = new ResponseDto("Updated Book Details Successfully", bookData);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/sortByPriceAsc")
    public ResponseEntity<ResponseDto> getBookByPriceAsc() {
        List<Book> bookData = bookService.sortPriceLowToHigh();
        ResponseDto responseDTO = new ResponseDto("Sorted all books by price low to high ", bookData);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @GetMapping("/sortByPriceDsc")
    public ResponseEntity<ResponseDto> getBookByPriceDsc() {
        List<Book> bookData = bookService.sortPriceHighToLow();
        ResponseDto responseDTO = new ResponseDto("Sorted all books by price high to low ", bookData);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @PostMapping("/update-qty")
    public ResponseEntity<ResponseDto> changeBookQuantity(@RequestParam Long id, @RequestParam int bookQuantity) {
        Book book = bookService.changeBookQty(id, bookQuantity);
        ResponseDto responseDTO = new ResponseDto("Book quantity changed successfully", book);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
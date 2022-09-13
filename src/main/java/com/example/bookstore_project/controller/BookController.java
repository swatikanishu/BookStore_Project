package com.example.bookstore_project.controller;

import com.example.bookstore_project.dto.BookDto;
import com.example.bookstore_project.dto.ResponseDto;
import com.example.bookstore_project.model.Book;
import com.example.bookstore_project.service.IBookService;
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

// add book
    @PostMapping("/add")
    public ResponseEntity<ResponseDto> addBook(@Valid @RequestBody BookDto bookDTO) {
        Book book = bookService.addBook(bookDTO);
        ResponseDto responseDTO = new ResponseDto("Add Book  Success", book);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
  //  Ability to get all book data by findAll() method
    @GetMapping("/findAll")
    public ResponseEntity<ResponseDto> findAllDetail() {
        List<Book> userList = bookService.findAll();
        ResponseDto responseDTO = new ResponseDto("** All book List ** ", userList);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
 //Ability to get book data by id
    @GetMapping("/get/{bookId}")
    public ResponseEntity<ResponseDto> FindById(@PathVariable Long bookId) {
        Book response = bookService.FindById(bookId);
        ResponseDto responseDto = new ResponseDto("***All Details book list using Id***", response);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
   // Ability to delete book data for particular id
    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<ResponseDto> deleteById(@PathVariable Long bookId) {
        bookService.deleteById(bookId);
        ResponseDto reponseDTO = new ResponseDto("**book Data deleted successfully ** ", "deleted id " + bookId);
        return new ResponseEntity(reponseDTO, HttpStatus.ACCEPTED);
    }
   /* create a method name as getBookByName
    Ability to get book by book name*/
    @GetMapping("/getBybookName/{bookName}")
    public ResponseEntity<ResponseDto> getBookByName(@PathVariable String bookName) {
        Book response = bookService.findBookByName(bookName);
        ResponseDto responseDTO = new ResponseDto("successfully record founded for given book name: ", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    //Ability to update book data for particular id
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDto> editData(@PathVariable Long bookid, @Valid @RequestBody BookDto bookDTO) {
        Book bookData = bookService.updateBookData(bookid, bookDTO);
        ResponseDto responseDTO = new ResponseDto("Updated Book Details Successfully", bookData);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
   /*  get book data in ascending order
     return  data in ascending order
     */
    @GetMapping("/sortByPriceAsc")
    public ResponseEntity<ResponseDto> getBookByPriceAsc() {
        List<Book> bookData = bookService.sortPriceLowToHigh();
        ResponseDto responseDTO = new ResponseDto("Sorted all books by price low to high ", bookData);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    /* get book data in descending order
      return - data in descending order
     */
    @GetMapping("/sortByPriceDsc")
    public ResponseEntity<ResponseDto> getBookByPriceDsc() {
        List<Book> bookData = bookService.sortPriceHighToLow();
        ResponseDto responseDTO = new ResponseDto("Sorted all books by price high to low ", bookData);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // update  book quantity

    @PostMapping("/update-qty")
    public ResponseEntity<ResponseDto> changeBookQuantity(@RequestParam Long bookid, @RequestParam int bookQuantity) {
        Book book = bookService.changeBookQty(bookid, bookQuantity);
        ResponseDto responseDTO = new ResponseDto("Book quantity changed successfully", book);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
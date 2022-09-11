package com.example.bookstore_project.model;

import com.example.bookstore_project.dto.BookDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor

@Table(name="Book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bookId", nullable = false)
    private Long bookId;

    @Column(name="book_name")
    public String bookName;
    public String authorName;
    public int bookQuantity;
    public int price;


    public String Bookimage;
    public String bookDescription;


    public Book(BookDto bookDto) {
        this.bookName = bookDto.getBookName();
        this.authorName = bookDto.getAuthorName();
        this.bookQuantity = bookDto.getBookQuantity();
        this.price = bookDto.getPrice();
        this.Bookimage=bookDto.getBookimage();
        this.bookDescription =bookDto.getBookDescription();
    }



}

package com.example.bookstore_project.dto;

import com.example.bookstore_project.model.Book;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
@Data
@NoArgsConstructor
public class BookDto {
    public String bookName;
    public int price;
    public String authorName;
    public int bookQuantity;
    public String Bookimage;
    public String bookDescription;
}

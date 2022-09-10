package com.example.bookstore_project.dto;

import com.example.bookstore_project.model.Book;
import com.example.bookstore_project.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
public class ResponseDto {
    private String message;
    private Object object;

    public ResponseDto(String string, String response) {
        this.message = string;
        this.object = response;
    }
    public ResponseDto(String string, Optional<User> response) {
        this.message = string;
        this.object = response;
    }
    public ResponseDto(String string, List<User> response) {
        this.message = string;
        this.object = response;
    }

    public ResponseDto(String s, User details) {
        this.message = s;
        this.object = details;
    }


    public ResponseDto(String s, Book book) {
        this.message=s;
        this.object=book;
    }

    public ResponseDto(String s, Object response) {
        this.message=s;
        this.object=response;
    }
}


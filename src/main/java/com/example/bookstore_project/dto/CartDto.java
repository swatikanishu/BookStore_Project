package com.example.bookstore_project.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartDto {
    Long userid;
    Long bookId;
    int quantity;

}

package com.example.bookstore_project.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginDto {
   private String email_address;
  private   String  password;
}

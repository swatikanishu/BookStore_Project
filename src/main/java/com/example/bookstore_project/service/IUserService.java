package com.example.bookstore_project.service;

import com.example.bookstore_project.dto.UserDto;
import com.example.bookstore_project.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {



    String addRecord(UserDto addressDto);

    List<User> findAll();

    Optional<User> FindById(Long id);

    List<User> getUserByemail(String email);

    User editByEmail(UserDto userDTO, String email_address);
}

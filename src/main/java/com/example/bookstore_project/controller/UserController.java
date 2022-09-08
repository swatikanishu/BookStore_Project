package com.example.bookstore_project.controller;


import com.example.bookstore_project.dto.ResponseDto;
import com.example.bookstore_project.dto.UserDto;
import com.example.bookstore_project.model.User;
import com.example.bookstore_project.service.IUserService;
import com.example.bookstore_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookstore")
public class UserController {
    @Autowired
    IUserService userService;


    @PostMapping("/insert")
    public ResponseEntity<String>AddAddressDetails(@Valid @RequestBody UserDto userDto) {
        String token = userService.addRecord(userDto);
        ResponseDto respDTO = new ResponseDto("Data Added Successfully", token);
        return new ResponseEntity(respDTO, HttpStatus.CREATED);
    }
    @GetMapping("/findAll")
    public ResponseEntity<ResponseDto> findAllDetail() {
        List<User> userList = userService.findAll();
        ResponseDto responseDTO = new ResponseDto("** All User List ** ", userList);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @GetMapping("/get/{Id}")
    public ResponseEntity<ResponseDto> FindById(@PathVariable Long Id) {
        Optional<User> response = userService.FindById(Id);
        ResponseDto responseDto = new ResponseDto("***All Details user list using Id***", response);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<ResponseDto> getDataByemail(@PathVariable String email) {
        List<User> personDetailsList = userService.getUserByemail(email);
        ResponseDto respDTO = new ResponseDto("*** Data by using email ***", personDetailsList);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }
    @PutMapping("/edit/{email_address}")
    public ResponseEntity<ResponseDto> updateById(@PathVariable String email_address, @Valid @RequestBody UserDto userDTO) {
        User Details =userService.editByEmail(userDTO, email_address);
        ResponseDto respDTO = new ResponseDto(" **** Person details is updated *****", Details);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }
    }

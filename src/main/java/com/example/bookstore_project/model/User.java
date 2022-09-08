package com.example.bookstore_project.model;


import com.example.bookstore_project.dto.UserDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
    @NoArgsConstructor
    @Table(name = "book_store")
    public @Data class User{
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "user_id")
        Long user_id;
        String first_name;
        String last_name;
        String address;
        @Column(name = "email")
        String email_address;
        LocalDate DOB;
        String password;

        public User(UserDto userdto){
            this.first_name = userdto.getFirst_name();
            this.last_name = userdto.getLast_name();
            this.address = userdto.getAddress();
            this.email_address = userdto.getEmail_address();
            this.DOB = userdto.getDOB();
            this.password = userdto.getPassword();
        }
    }



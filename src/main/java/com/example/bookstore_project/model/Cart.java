package com.example.bookstore_project.model;

import com.example.bookstore_project.dto.CartDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cartId", nullable = false)
     Long cartId;
    @OneToOne
    @JoinColumn(name = "userid")
   User user;

   @ManyToOne
   @JoinColumn(name = "bookId")
   Book book;
    int quantity;

    public Cart(User user, Book book, int quantity) {
        this.user=user;
        this.book=book;
        this.quantity=quantity;
    }


}

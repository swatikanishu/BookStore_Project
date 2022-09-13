package com.example.bookstore_project.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name="book_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orderID", nullable = false)
    Long orderID;
     LocalDate date = LocalDate.now();
    int price;
    int quantity;
    String address;
    @OneToOne
    @JoinColumn(name="userid")
    User user;
    @ManyToOne
    @JoinColumn(name="bookId")
    Book book;
    boolean cancel;

    public Order( LocalDate date, Integer price, Integer quantity, String address, User user, Book book, boolean cancel) {
        this.date = date;
        this.price = price;
        this.quantity = quantity;
        this.address = address;
        this.user = user;
        this.book = book;
        this.cancel = cancel;
    }




}

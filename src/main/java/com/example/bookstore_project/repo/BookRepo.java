package com.example.bookstore_project.repo;

import com.example.bookstore_project.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {

    @Query(value = "SELECT * FROM book WHERE book_name=:bookName", nativeQuery = true)

    Book findBookByName(String bookName);
    @Query(value = "select * from book order by price", nativeQuery = true)
    List<Book> getSortedListOfBooksInAsc();
    @Query(value = "select * from book order by price desc", nativeQuery = true)

    List<Book> getSortedListOfBooksInDesc();
}

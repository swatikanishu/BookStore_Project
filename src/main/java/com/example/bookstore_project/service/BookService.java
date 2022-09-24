package com.example.bookstore_project.service;
import com.example.bookstore_project.dto.BookDto;
import com.example.bookstore_project.exception.BookException;
import com.example.bookstore_project.model.Book;
import com.example.bookstore_project.repo.BookRepo;
import com.example.bookstore_project.utility.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;



@Service
public class BookService implements IBookService {

    @Autowired
    BookRepo bookRepo;
    @Autowired
    EmailSenderService emailSenderService;
   // create a method name as add book
    public Book addBook(BookDto bookDTO) {
        Book addData = new Book(bookDTO);
        return bookRepo.save(addData);
    }
  //  create a method name as findall()
    @Override
    public List<Book> findAll() {
        List<Book> bookList = bookRepo.findAll();
        return bookList;
    }
   // create a method name as findbyId
    @Override
    public Book FindById(Long id) {
        Book book = bookRepo.findById(id).orElse(null);
        if (book != null) {
            return book;

        }else
            throw new BookException("book id is not found");
    }
    //create a method name as gdeleteByid
    @Override
    public String deleteById(Long id) {
        Book findById = bookRepo.findById(id).orElse(null);

        if (findById != null) {
            bookRepo.deleteById(id);
            return "data is deleted";

        } else throw new BookException("id is not invalid");

    }
  //  create a method name as findbookByname

    @Override
    public Book findBookByName(String bookName) {
        Book bookList =  bookRepo.findBookByName(bookName);
        if (bookList!=null) {
            bookRepo.findBookByName("bookname");
            return bookList;

        }else   throw new BookException(" Book with naame  is  found!");

    }
   // create a method name as updateBookData
    @Override
    public Book updateBookData(Long id, BookDto bookdto) {
        Book editbook = bookRepo.findById(id).orElse(null);
        if (editbook != null) {
            editbook.setBookName(bookdto.getBookName());
            editbook.setAuthorName(bookdto.getAuthorName());
            editbook.setBookDescription(bookdto.getBookDescription());
            editbook.setBookimage(bookdto.getBookimage());
            editbook.setPrice(bookdto.getPrice());
            editbook.setBookQuantity(bookdto.getBookQuantity());

            return bookRepo.save(editbook);
        } else throw new BookException("Id:"+id+" is not present ");

    }

    @Override
    public List<Book> sortPriceLowToHigh() {
        List<Book> getSortedList = bookRepo.getSortedListOfBooksInAsc();
        return getSortedList;
    }

    @Override
    public List<Book> sortPriceHighToLow() {
        List<Book> getSortedListInDesc =bookRepo.getSortedListOfBooksInDesc();
        return getSortedListInDesc;

    }

    @Override
    public Book changeBookQty(Long id, int bookQuantity) {
        Book book = bookRepo.findById(id).orElse(null);
        if(book == null){
            throw new BookException("id is not found");
        }
        book.setBookQuantity(bookQuantity);
        return bookRepo.save(book);
    }
    }







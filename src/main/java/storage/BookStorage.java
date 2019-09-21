package storage;

import type.Book;

import java.util.List;

public interface BookStorage {
    Book getBook(long id);

    List<Book> getAllBooks();

    long addBook(Book book); //shoud return bookId

    void clearTableBooks();

    //TODO add Delete book feature
}

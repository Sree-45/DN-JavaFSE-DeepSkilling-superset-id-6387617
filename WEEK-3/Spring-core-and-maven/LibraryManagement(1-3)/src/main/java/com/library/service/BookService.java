package com.library.service;

import com.library.repository.BookRepository;

public class BookService {
    private BookRepository bookRepository;

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void printService() {
        System.out.println("BookService: Ready!");
        if (bookRepository != null) {
            bookRepository.printRepository();
        }
    }
}

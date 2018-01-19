package ru.unn.agile.BookSale.Model.legacy;

import java.util.UUID;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;

public class BooksTable {

    public BooksTable() {
        this.books = new HashMap<UUID, Book>();
    }

    public BooksTable(final Set<Book> books) {
        this.books = new HashMap<UUID, Book>();
        for (Book book : books) {
            this.books.put(book.getId(), book);
        }
    }

    public Book[] getBooks() {
        return this.books.values().toArray(new Book[0]);
    }

    public Book getBookById(final UUID id) {
        return this.books.get(id);
    }

    private Map<UUID, Book> books;
}

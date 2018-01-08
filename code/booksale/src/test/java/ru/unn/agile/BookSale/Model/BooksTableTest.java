package ru.unn.agile.BookSale.Model;

import org.junit.Before;
import org.junit.Test;

import java.util.Set;
import java.util.HashSet;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BooksTableTest {
    @Before
    public void setUp() {
        book1 = new Book("Harry Potter and the Philosopher's Stone", 8.0, UUID.randomUUID());
        book2 = new Book("Harry Potter And The Chamber of secrets", 8.0, UUID.randomUUID());
        book3 = new Book("Harry Potter and the prisoner of Azkaban", 8.0, UUID.randomUUID());
        book4 = new Book("Harry Potter and the Goblet of Fire", 8.0, UUID.randomUUID());
        book5 = new Book("Harry Potter and the Order of the Phoenix", 8.0, UUID.randomUUID());

        books = new HashSet<Book>();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);
    }

    @Test
    public void canCreateEmptyBooksTable() {
        BooksTable booksTable = new BooksTable();

        assertNotNull(booksTable);
    }

    @Test
    public void canCreateNotEmptyBooksTable() {
        BooksTable booksTable = new BooksTable(books);

        assertNotNull(booksTable);
    }

    @Test
    public void canGetBooks() {
        BooksTable booksTable = new BooksTable(books);

        assertNotNull(booksTable.getBooks());
    }

    @Test
    public void canGetBookById() {
        BooksTable booksTable = new BooksTable(books);

        assertEquals(booksTable.getBookById(book1.getId()), book1);
    }

    private Book book1;
    private Book book2;
    private Book book3;
    private Book book4;
    private Book book5;
    private Set<Book> books;
}

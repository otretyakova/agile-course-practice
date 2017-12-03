package ru.unn.agile.BookSale.Model;

import org.junit.Test;

import static org.junit.Assert.*;

public class BookTest {

    @Test
    public void canCreateBook() {
        Book book = new Book(HarryPotterBooks.philosophersStone, 1);

        assertNotNull(book);
    }

    @Test
    public void canGetBookPart() {
        Book book = new Book(HarryPotterBooks.philosophersStone, 2);

        assertEquals(HarryPotterBooks.philosophersStone, book.part());
    }

    @Test
    public void canGetBookCount() {
        Book book = new Book(HarryPotterBooks.philosophersStone, 2);

        assertEquals(2, book.count());
    }

    @Test
    public void canSetBookCount() {
        Book book = new Book(HarryPotterBooks.philosophersStone, 2);

        book.setCount(3);

        assertEquals(3, book.count());
    }

    @Test
    public void isEqualBooksEqual() {
        Book book1 = new Book(HarryPotterBooks.philosophersStone, 1);
        Book book2 = new Book(HarryPotterBooks.philosophersStone, 2);

        assertTrue(book1.isEqual(book2));
    }

    @Test
    public void isNotEqualBooksNotEqual() {
        Book book1 = new Book(HarryPotterBooks.philosophersStone, 1);
        Book book2 = new Book(HarryPotterBooks.chamberOfSecret, 1);

        assertFalse(book1.isEqual(book2));
    }
}

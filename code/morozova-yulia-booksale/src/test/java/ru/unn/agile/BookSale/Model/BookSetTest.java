package ru.unn.agile.BookSale.Model;

import java.util.ArrayList;

import org.junit.Test;

import static org.junit.Assert.*;

public class BookSetTest {
    private final double delta = 0.001;

    @Test
    public void canCreateBookSet() {
        ArrayList<Book> bookList = new ArrayList<Book>();
        bookList.add(new Book(HarryPotterBooks.philosophersStone, 1));
        bookList.add(new Book(HarryPotterBooks.chamberOfSecret, 1));
        bookList.add(new Book(HarryPotterBooks.prisonerOfAzkaban, 1));

        BookSet bookSet = new BookSet(bookList);

        assertNotNull(bookSet);
    }

    @Test
    public void canCreateEmptyBookSet() {
        BookSet bookSet = new BookSet();

        assertEquals(0, bookSet.count());
    }

    @Test
    public void canCombineBookSet() {
        ArrayList<Book> bookList = new ArrayList<Book>();
        bookList.add(new Book(HarryPotterBooks.philosophersStone, 1));
        bookList.add(new Book(HarryPotterBooks.chamberOfSecret, 1));
        bookList.add(new Book(HarryPotterBooks.prisonerOfAzkaban, 1));
        BookSet bookSet = new BookSet(bookList);

        bookSet.combineEqualBooks();

        assertNotNull(bookSet);
    }

    @Test
    public void isCombineBookSetEqualBookSetWithoutEqualBooks() {
        ArrayList<Book> bookList = new ArrayList<Book>();
        bookList.add(new Book(HarryPotterBooks.philosophersStone, 1));
        bookList.add(new Book(HarryPotterBooks.chamberOfSecret, 1));
        bookList.add(new Book(HarryPotterBooks.prisonerOfAzkaban, 1));

        BookSet bookSet = new BookSet(bookList);

        assertNotNull(bookSet);
    }

    @Test
    public void isSizeOfCombineBookSetCorrect() {
        ArrayList<Book> bookList = new ArrayList<Book>();
        bookList.add(new Book(HarryPotterBooks.philosophersStone, 1));
        bookList.add(new Book(HarryPotterBooks.chamberOfSecret, 1));
        bookList.add(new Book(HarryPotterBooks.philosophersStone, 1));
        BookSet bookSet = new BookSet(bookList);

        bookSet.combineEqualBooks();

        assertEquals(2, bookSet.set().size());
    }

    @Test
    public void canGetSetOfNotEmptyBookSet() {
        ArrayList<Book> bookList = new ArrayList<Book>();
        bookList.add(new Book(HarryPotterBooks.philosophersStone, 1));
        bookList.add(new Book(HarryPotterBooks.chamberOfSecret, 1));
        bookList.add(new Book(HarryPotterBooks.prisonerOfAzkaban, 1));
        BookSet bookSet = new BookSet(bookList);

        assertEquals(bookList, bookSet.set());
    }

    @Test
    public void canGetSetOfEmptyBookSet() {
        BookSet bookSet = new BookSet();

        assertEquals(new ArrayList<Book>(), bookSet.set());
    }

    @Test
    public void canCalculateCountOfEmptyBookSet() {
        BookSet bookSet = new BookSet();

        assertEquals(0, bookSet.count());
    }

    @Test
    public void canCalculateCountOfNotEmptyBookSet() {
        ArrayList<Book> bookList = new ArrayList<Book>();
        bookList.add(new Book(HarryPotterBooks.philosophersStone, 1));
        bookList.add(new Book(HarryPotterBooks.chamberOfSecret, 2));
        bookList.add(new Book(HarryPotterBooks.prisonerOfAzkaban, 3));
        BookSet bookSet = new BookSet(bookList);

        assertEquals(6, bookSet.count());
    }

    @Test
    public void canCalculateCountDifferentOfEmptyBookSet() {
        BookSet bookSet = new BookSet();

        assertEquals(0, bookSet.countDifferent());
    }

    @Test
    public void canCalculateCountDifferentOfNotEmptyBookSet() {
        ArrayList<Book> bookList = new ArrayList<Book>();
        bookList.add(new Book(HarryPotterBooks.philosophersStone, 1));
        bookList.add(new Book(HarryPotterBooks.chamberOfSecret, 2));
        bookList.add(new Book(HarryPotterBooks.prisonerOfAzkaban, 3));
        bookList.add(new Book(HarryPotterBooks.chamberOfSecret, 1));
        bookList.add(new Book(HarryPotterBooks.orderOfThePhoenix, 3));
        bookList.add(new Book(HarryPotterBooks.chamberOfSecret, 1));
        BookSet bookSet = new BookSet(bookList);

        assertEquals(4, bookSet.countDifferent());
    }

    @Test
    public void canSearchBookInNotEmptyBookSet() {
        ArrayList<Book> bookList = new ArrayList<Book>();
        bookList.add(new Book(HarryPotterBooks.philosophersStone, 1));
        bookList.add(new Book(HarryPotterBooks.chamberOfSecret, 2));
        bookList.add(new Book(HarryPotterBooks.prisonerOfAzkaban, 3));
        bookList.add(new Book(HarryPotterBooks.chamberOfSecret, 1));
        bookList.add(new Book(HarryPotterBooks.orderOfThePhoenix, 3));
        bookList.add(new Book(HarryPotterBooks.chamberOfSecret, 1));
        BookSet bookSet = new BookSet(bookList);

        assertNotNull(bookSet.searchBook(new Book(HarryPotterBooks.chamberOfSecret, 1)));
    }

    @Test
    public void canNotSearchBookInNotEmptyBookSet() {
        ArrayList<Book> bookList = new ArrayList<Book>();
        bookList.add(new Book(HarryPotterBooks.philosophersStone, 1));
        bookList.add(new Book(HarryPotterBooks.prisonerOfAzkaban, 3));
        BookSet bookSet = new BookSet(bookList);

        assertNull(bookSet.searchBook(new Book(HarryPotterBooks.chamberOfSecret, 1)));
    }

    @Test
    public void canNotSearchBookInEmptyBookSet() {
        BookSet bookSet = new BookSet();

        assertNull(bookSet.searchBook(new Book(HarryPotterBooks.chamberOfSecret, 1)));
    }

    @Test
    public void canChangeBookCountInNotEmptyBookSet() {
        ArrayList<Book> bookList = new ArrayList<Book>();
        Book book = new Book(HarryPotterBooks.philosophersStone, 1);
        bookList.add(book);
        BookSet bookSet = new BookSet(bookList);

        bookSet.changeBookCount(book, 2);

        assertEquals(2, bookSet.count());
    }

    @Test
    public void canAddBookToEmptyBookSet() {
        BookSet bookSet = new BookSet();
        Book book = new Book(HarryPotterBooks.gobletOfFire, 1);

        bookSet.addBook(book);

        assertNotNull(bookSet);
    }

    @Test
    public void isBookSetCorrectAfterAddBookToEmptyBookSet() {
        BookSet bookSet = new BookSet();
        Book book = new Book(HarryPotterBooks.gobletOfFire, 2);
        ArrayList<Book> bookList = new ArrayList<Book>();
        bookList.add(book);

        bookSet.addBook(book);

        assertEquals(bookList, bookSet.set());
    }

    @Test
    public void isBookSetCountCorrectAfterAddBookToEmptyBookSet() {
        BookSet bookSet = new BookSet();
        Book book = new Book(HarryPotterBooks.gobletOfFire, 2);

        bookSet.addBook(book);

        assertEquals(2, bookSet.count());
    }

    @Test
    public void isBookSetCountDifferentCorrectAfterAddBookToEmptyBookSet() {
        BookSet bookSet = new BookSet();
        Book book = new Book(HarryPotterBooks.gobletOfFire, 2);

        bookSet.addBook(book);

        assertEquals(1, bookSet.countDifferent());
    }

    @Test
    public void canAddBookToNotEmptyBookSet() {
        ArrayList<Book> bookList = new ArrayList<Book>();
        bookList.add(new Book(HarryPotterBooks.philosophersStone, 1));
        bookList.add(new Book(HarryPotterBooks.chamberOfSecret, 1));
        BookSet bookSet = new BookSet(bookList);

        bookSet.addBook(new Book(HarryPotterBooks.prisonerOfAzkaban, 1));

        assertNotNull(bookSet);
    }

    @Test
    public void isBookSetCorrectAfterAddBookToNotEmptyBookSet() {
        ArrayList<Book> bookList = new ArrayList<Book>();
        bookList.add(new Book(HarryPotterBooks.philosophersStone, 2));
        bookList.add(new Book(HarryPotterBooks.chamberOfSecret, 1));
        BookSet bookSet = new BookSet(bookList);
        bookList.add(new Book(HarryPotterBooks.prisonerOfAzkaban, 2));

        bookSet.addBook(new Book(HarryPotterBooks.prisonerOfAzkaban, 2));

        assertEquals(bookSet.set(), bookList);
    }

    @Test
    public void isBookSetCountCorrectAfterAddBookToNotEmptyBookSet() {
        ArrayList<Book> bookList = new ArrayList<Book>();
        bookList.add(new Book(HarryPotterBooks.philosophersStone, 2));
        bookList.add(new Book(HarryPotterBooks.chamberOfSecret, 1));
        BookSet bookSet = new BookSet(bookList);

        bookSet.addBook(new Book(HarryPotterBooks.prisonerOfAzkaban, 2));

        assertEquals(5, bookSet.count());
    }

    @Test
    public void isBookSetCountDifferentCorrectAfterAddBookToNotEmptyBookSet() {
        ArrayList<Book> bookList = new ArrayList<Book>();
        bookList.add(new Book(HarryPotterBooks.philosophersStone, 2));
        bookList.add(new Book(HarryPotterBooks.chamberOfSecret, 1));
        BookSet bookSet = new BookSet(bookList);

        bookSet.addBook(new Book(HarryPotterBooks.prisonerOfAzkaban, 2));

        assertEquals(3, bookSet.countDifferent());
    }

    @Test
    public void canDeleteBookFromNotEmptyBookSet() {
        ArrayList<Book> bookList = new ArrayList<Book>();
        bookList.add(new Book(HarryPotterBooks.philosophersStone, 2));
        bookList.add(new Book(HarryPotterBooks.chamberOfSecret, 1));
        bookList.add(new Book(HarryPotterBooks.prisonerOfAzkaban, 2));
        BookSet bookSet = new BookSet(bookList);

        assertTrue(bookSet.deleteBook(new Book(HarryPotterBooks.philosophersStone, 2)));
    }

    @Test
    public void canNotDeleteBookFromNotEmptyBookSet() {
        ArrayList<Book> bookList = new ArrayList<Book>();
        bookList.add(new Book(HarryPotterBooks.philosophersStone, 2));
        bookList.add(new Book(HarryPotterBooks.chamberOfSecret, 1));
        bookList.add(new Book(HarryPotterBooks.prisonerOfAzkaban, 2));
        BookSet bookSet = new BookSet(bookList);

        assertFalse(bookSet.deleteBook(new Book(HarryPotterBooks.gobletOfFire, 2)));
    }

    @Test
    public void canNotDeleteBookFromEmptyBookSet() {
        BookSet bookSet = new BookSet();

        assertFalse(bookSet.deleteBook(new Book(HarryPotterBooks.philosophersStone, 2)));
    }

    @Test
    public void canGetMaxDiscountOfBookSetWithoutDifferentBooks() {
        ArrayList<Book> bookList = new ArrayList<Book>();
        bookList.add(new Book(HarryPotterBooks.philosophersStone, 1));
        bookList.add(new Book(HarryPotterBooks.philosophersStone, 2));
        BookSet bookSet = new BookSet(bookList);

        assertEquals(0.0, bookSet.getMaxDiscount(), delta);
    }

    @Test
    public void canGetMaxDiscountOfBookSetWithTwoDifferentBooks() {
        ArrayList<Book> bookList = new ArrayList<Book>();
        bookList.add(new Book(HarryPotterBooks.philosophersStone, 1));
        bookList.add(new Book(HarryPotterBooks.chamberOfSecret, 2));
        bookList.add(new Book(HarryPotterBooks.philosophersStone, 3));
        BookSet bookSet = new BookSet(bookList);

        assertEquals(0.05, bookSet.getMaxDiscount(), delta);
    }

    @Test
    public void canGetMaxDiscountOfBookSetWithThreeDifferentBooks() {
        ArrayList<Book> bookList = new ArrayList<Book>();
        bookList.add(new Book(HarryPotterBooks.philosophersStone, 1));
        bookList.add(new Book(HarryPotterBooks.chamberOfSecret, 2));
        bookList.add(new Book(HarryPotterBooks.prisonerOfAzkaban, 3));
        BookSet bookSet = new BookSet(bookList);

        assertEquals(0.1, bookSet.getMaxDiscount(), delta);
    }

    @Test
    public void canGetMaxDiscountOfBookSetWithFourDifferentBooks() {
        ArrayList<Book> bookList = new ArrayList<Book>();
        bookList.add(new Book(HarryPotterBooks.philosophersStone, 1));
        bookList.add(new Book(HarryPotterBooks.chamberOfSecret, 2));
        bookList.add(new Book(HarryPotterBooks.prisonerOfAzkaban, 3));
        bookList.add(new Book(HarryPotterBooks.chamberOfSecret, 1));
        bookList.add(new Book(HarryPotterBooks.orderOfThePhoenix, 3));
        bookList.add(new Book(HarryPotterBooks.chamberOfSecret, 1));
        BookSet bookSet = new BookSet(bookList);

        assertEquals(0.2, bookSet.getMaxDiscount(), delta);
    }

    @Test
    public void canGetMaxDiscountOfBookSetWithFiveDifferentBooks() {
        ArrayList<Book> bookList = new ArrayList<Book>();
        bookList.add(new Book(HarryPotterBooks.philosophersStone, 1));
        bookList.add(new Book(HarryPotterBooks.chamberOfSecret, 2));
        bookList.add(new Book(HarryPotterBooks.prisonerOfAzkaban, 3));
        bookList.add(new Book(HarryPotterBooks.gobletOfFire, 1));
        bookList.add(new Book(HarryPotterBooks.orderOfThePhoenix, 3));
        BookSet bookSet = new BookSet(bookList);

        assertEquals(0.25, bookSet.getMaxDiscount(), delta);
    }

    @Test
    public void canCalculateCostOfBookSetWithDiscount5Persent() {
        ArrayList<Book> bookList = new ArrayList<Book>();
        bookList.add(new Book(HarryPotterBooks.philosophersStone, 1));
        bookList.add(new Book(HarryPotterBooks.chamberOfSecret, 1));
        bookList.add(new Book(HarryPotterBooks.philosophersStone, 1));
        BookSet bookSet = new BookSet(bookList);

        assertEquals(Price.BOOK_PRICE * 2 * 0.95 + Price.BOOK_PRICE, bookSet.cost(), delta);
    }

    @Test
    public void canCalculateCostOfBookSetWithDiscount10Persent() {
        ArrayList<Book> bookList = new ArrayList<Book>();
        bookList.add(new Book(HarryPotterBooks.philosophersStone, 1));
        bookList.add(new Book(HarryPotterBooks.chamberOfSecret, 1));
        bookList.add(new Book(HarryPotterBooks.prisonerOfAzkaban, 1));
        BookSet bookSet = new BookSet(bookList);

        assertEquals(Price.BOOK_PRICE * 3 * 0.9, bookSet.cost(), delta);
    }

    @Test
    public void canCalculateCostOfBookSetWithDiscount20Persent() {
        ArrayList<Book> bookList = new ArrayList<Book>();
        bookList.add(new Book(HarryPotterBooks.philosophersStone, 1));
        bookList.add(new Book(HarryPotterBooks.chamberOfSecret, 2));
        bookList.add(new Book(HarryPotterBooks.prisonerOfAzkaban, 1));
        bookList.add(new Book(HarryPotterBooks.gobletOfFire, 3));
        BookSet bookSet = new BookSet(bookList);

        assertEquals(Price.BOOK_PRICE * 4 * 0.8 + Price.BOOK_PRICE * 3, bookSet.cost(), delta);
    }

    @Test
    public void canCalculateCostOfBookSetWithDiscount25Persent() {
        ArrayList<Book> bookList = new ArrayList<Book>();
        bookList.add(new Book(HarryPotterBooks.philosophersStone, 1));
        bookList.add(new Book(HarryPotterBooks.chamberOfSecret, 2));
        bookList.add(new Book(HarryPotterBooks.prisonerOfAzkaban, 1));
        bookList.add(new Book(HarryPotterBooks.gobletOfFire, 3));
        bookList.add(new Book(HarryPotterBooks.orderOfThePhoenix, 2));
        bookList.add(new Book(HarryPotterBooks.gobletOfFire, 1));
        BookSet bookSet = new BookSet(bookList);

        assertEquals(Price.BOOK_PRICE * 5 * 0.75 + Price.BOOK_PRICE * 5, bookSet.cost(), delta);
    }
}
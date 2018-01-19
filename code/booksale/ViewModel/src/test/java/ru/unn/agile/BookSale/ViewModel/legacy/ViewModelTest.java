package ru.unn.agile.BookSale.ViewModel.legacy;

import ru.unn.agile.BookSale.Model.legacy.Book;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.AfterClass;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ViewModelTest {
    @BeforeClass
    public static void setUpClass() {
        namesOfDefaultBooks = new ArrayList<String>();
        namesOfDefaultBooks.add("Harry Potter and the Philosopher's Stone");
        namesOfDefaultBooks.add("Harry Potter And the Chamber of secrets");
        namesOfDefaultBooks.add("Harry Potter and the prisoner of Azkaban");
        namesOfDefaultBooks.add("Harry Potter and the Goblet of Fire");
        namesOfDefaultBooks.add("Harry Potter and the Order of the Phoenix");
    }

    @AfterClass
    public static void cleanAll() {
        namesOfDefaultBooks = null;
    }

    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void viewModelCanBeCreated() {
        assertNotNull(viewModel);
    }

    @Test
    public void defaultAvailableBooksListIsInitializedCorrectly() {
        Book[] books = viewModel.getAvailableBooks();

        assertTrue(namesOfDefaultBooks.contains(books[0].getName()));
        assertTrue(namesOfDefaultBooks.contains(books[1].getName()));
        assertTrue(namesOfDefaultBooks.contains(books[2].getName()));
        assertTrue(namesOfDefaultBooks.contains(books[3].getName()));
        assertTrue(namesOfDefaultBooks.contains(books[4].getName()));
        assertEquals(books[0].getPrice(), DEFAULT_PRICE, ERROR);
        assertEquals(books[1].getPrice(), DEFAULT_PRICE, ERROR);
        assertEquals(books[2].getPrice(), DEFAULT_PRICE, ERROR);
        assertEquals(books[3].getPrice(), DEFAULT_PRICE, ERROR);
        assertEquals(books[4].getPrice(), DEFAULT_PRICE, ERROR);
    }

    @Test
    public void whenBookFromAvailableListIsNotSelectedAddButtonIsDisabled() {
        assertFalse(viewModel.isAddBookToOrderButtonEnabled());
    }

    @Test
    public void whenBookFromAvailableListIsNotSelectedDeleteButtonIsDisabled() {
        assertFalse(viewModel.isDeleteBookFromOrderButtonEnabled());
    }

    @Test
    public void whenBookFromAvailableListIsSelectedAddButtonIsEnabled() {
        Book[] books = viewModel.getAvailableBooks();
        viewModel.onSelectBookFromAvailableList(books[1]);

        assertTrue(viewModel.isAddBookToOrderButtonEnabled());
    }

    @Test
    public void canSelectBookFromAvailableBooksList() {
        Book[] books = viewModel.getAvailableBooks();
        viewModel.onSelectBookFromAvailableList(books[2]);
        Book expectedBook = viewModel.getSelectedBook();

        assertEquals(expectedBook, books[2]);
    }

    @Test
    public void whenClickOnAddButtonSelectedBookShouldBeAddedToOrderList() {
        Book[] books = viewModel.getAvailableBooks();
        viewModel.onSelectBookFromAvailableList(books[0]);
        viewModel.addSelectedBookToOrder();
        ViewModel.OrderedBook[] orderedBooks = viewModel.getOrderedBooks();

        assertEquals(orderedBooks[0].getCount(), (Integer) 1);
        assertEquals(orderedBooks[0].getBook(), books[0]);
    }

    @Test
    public void whenSelectedBookWasAddedDeleteButtonIsEnabled() {
        Book[] books = viewModel.getAvailableBooks();
        viewModel.onSelectBookFromAvailableList(books[3]);
        viewModel.addSelectedBookToOrder();

        assertTrue(viewModel.isDeleteBookFromOrderButtonEnabled());
    }

    @Test
    public void whenOrderListContainsSelectedBookDeleteButtonIsEnabled() {
        Book[] books = viewModel.getAvailableBooks();
        viewModel.onSelectBookFromAvailableList(books[3]);
        viewModel.addSelectedBookToOrder();
        viewModel.onSelectBookFromAvailableList(books[4]);
        viewModel.addSelectedBookToOrder();
        viewModel.onSelectBookFromAvailableList(books[3]);

        assertTrue(viewModel.isDeleteBookFromOrderButtonEnabled());
    }

    @Test
    public void whenOrderListDoesNotContainSelectedBookDeleteButtonIsDisabled() {
        Book[] books = viewModel.getAvailableBooks();
        viewModel.onSelectBookFromAvailableList(books[4]);
        viewModel.addSelectedBookToOrder();
        viewModel.onSelectBookFromAvailableList(books[0]);

        assertFalse(viewModel.isDeleteBookFromOrderButtonEnabled());
    }

    @Test
    public void whenClickOnDeleteButtonSelectedBookShouldBeDeletedFromOrderList() {
        Book[] books = viewModel.getAvailableBooks();
        viewModel.onSelectBookFromAvailableList(books[0]);
        viewModel.addSelectedBookToOrder();
        viewModel.deleteSelectedBookFromOrder();

        assertEquals(viewModel.getOrderedBooks().length, 0);
    }

    @Test
    public void whenSelectedBookWasDeletedDeleteButtonIsDisabled() {
        Book[] books = viewModel.getAvailableBooks();
        viewModel.onSelectBookFromAvailableList(books[3]);
        viewModel.addSelectedBookToOrder();
        viewModel.deleteSelectedBookFromOrder();

        assertFalse(viewModel.isDeleteBookFromOrderButtonEnabled());
    }

    @Test
    public void orderedBookCanBeCreated() {
        Book book = new Book("Name", 10.0, UUID.randomUUID());
        ViewModel.OrderedBook orderedBook = new ViewModel.OrderedBook(book, 1);

        assertNotNull(orderedBook);
    }

    @Test
    public void orderedBookCanBeConvertedToString() {
        Book book = new Book("Name", 1.0, UUID.randomUUID());
        ViewModel.OrderedBook orderedBook = new ViewModel.OrderedBook(book, 21);

        assertEquals(orderedBook.toString(), "Name (1.0$) - 21 PCs.");
    }

    @Test
    public void whenAddBookToOrderCostOfOrderIsCalculated() {
        Book[] books = viewModel.getAvailableBooks();
        viewModel.onSelectBookFromAvailableList(books[4]);
        viewModel.addSelectedBookToOrder();

        assertEquals(viewModel.getCost(), books[4].getPrice(), ERROR);
    }

    @Test
    public void whenDeleteBookFromOrderCostOfOrderIsCalculated() {
        Book[] books = viewModel.getAvailableBooks();
        viewModel.onSelectBookFromAvailableList(books[4]);
        viewModel.addSelectedBookToOrder();
        viewModel.deleteSelectedBookFromOrder();

        assertEquals(viewModel.getCost(), 0.0, ERROR);
    }

    private ViewModel viewModel;
    private static ArrayList<String> namesOfDefaultBooks;
    private static final double DEFAULT_PRICE = 8.0;
    private static final double ERROR = 0.001;
}

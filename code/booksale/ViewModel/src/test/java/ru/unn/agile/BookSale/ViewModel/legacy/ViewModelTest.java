package ru.unn.agile.BookSale.ViewModel.legacy;

import ru.unn.agile.BookSale.Model.legacy.Book;

import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ViewModelTest {

    public void setViewModel(final ViewModel viewModels) {
        this.viewModel = viewModels;
    }

    @Before
    public void setUp() {
        FakeLogger logger = new FakeLogger();
        viewModel = new ViewModel(logger);
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

    @Test(expected = IllegalArgumentException.class)
    public void canThrowExceptionWhenLoggerIsNull() {
        new ViewModel(null);
    }

    @Test
    public void isLogEmptyAfterStart() {
        List<String> log = viewModel.getLog();

        assertEquals(1, log.size());
    }

    @Test
    public void canAddLogWhenSelectBook() {
        Book[] books = viewModel.getAvailableBooks();
        viewModel.onSelectBookFromAvailableList(books[4]);
        List<String> log = viewModel.getLog();

        assertEquals(2, log.size());
    }

    @Test
    public void canAddLogWhenSelectChange() {
        Book[] books = viewModel.getAvailableBooks();
        viewModel.onSelectBookFromAvailableList(books[4]);
        viewModel.onSelectBookFromAvailableList(books[3]);
        List<String> log = viewModel.getLog();

        assertEquals(3, log.size());
    }

    @Test
    public void doesNotLogWhenSelectIWasNotChanged() {
        Book[] books = viewModel.getAvailableBooks();
        viewModel.onSelectBookFromAvailableList(books[4]);
        viewModel.onSelectBookFromAvailableList(books[4]);
        List<String> log = viewModel.getLog();

        assertEquals(2, log.size());
    }

    @Test
    public void canAddLogWhenAddBookToOrder() {
        Book[] books = viewModel.getAvailableBooks();
        viewModel.onSelectBookFromAvailableList(books[4]);
        viewModel.addSelectedBookToOrder();
        List<String> log = viewModel.getLog();

        assertEquals(4, log.size());
    }

    @Test
    public void doesLogContainLogMessageWhenSelectBook() {
        Book[] books = viewModel.getAvailableBooks();
        viewModel.onSelectBookFromAvailableList(books[3]);
        List<String> log = viewModel.getLog();

        assertTrue(log.get(1).matches(".*" + ViewModel.LogMessages.SELECT_BOOK));
    }

    @Test
    public void doesLogsContainLogMessageWhenAddBook() {
        Book[] books = viewModel.getAvailableBooks();
        viewModel.onSelectBookFromAvailableList(books[2]);
        viewModel.addSelectedBookToOrder();
        List<String> log = viewModel.getLog();

        assertTrue(log.get(1).matches(".*" + ViewModel.LogMessages.SELECT_BOOK));
        assertTrue(log.get(2).matches(".*" + ViewModel.LogMessages.ADD_BOOK_WAS_PRESSED));
        assertTrue(log.get(3).matches(".*" + ViewModel.LogMessages.THE_CALCULATE_WAS_MADE + ".*"));
    }

    @Test
    public void doesLogsContainLogMessageWhenDeleteBook() {
        Book[] books = viewModel.getAvailableBooks();
        viewModel.onSelectBookFromAvailableList(books[1]);
        viewModel.addSelectedBookToOrder();
        viewModel.deleteSelectedBookFromOrder();
        List<String> log = viewModel.getLog();

        assertTrue(log.get(4).matches(".*" + ViewModel.LogMessages.DELETE_BOOK_WAS_PRESSED));
        assertTrue(log.get(5).matches(".*" + ViewModel.LogMessages.THE_CALCULATE_WAS_MADE + ".*"));
    }

    @Test
    public void doesLogsContainLogMessageWhenAddAndDeleteBookFull() {
        Book[] books = viewModel.getAvailableBooks();
        viewModel.onSelectBookFromAvailableList(books[1]);
        String selectedBookName = viewModel.getSelectedBook().getName();
        viewModel.addSelectedBookToOrder();
        double costAfterAdd = viewModel.getCost();
        viewModel.deleteSelectedBookFromOrder();
        double costAfterDelete = viewModel.getCost();
        List<String> log = viewModel.getLog();
        assertTrue(log.get(2).matches(".*" + selectedBookName
                + ViewModel.LogMessages.ADD_BOOK_WAS_PRESSED));
        assertTrue(log.get(3).matches(".*" + ViewModel.LogMessages.THE_CALCULATE_WAS_MADE
                + costAfterAdd));
        assertTrue(log.get(4).matches(".*" + selectedBookName
                + ViewModel.LogMessages.DELETE_BOOK_WAS_PRESSED));
        assertTrue(log.get(5).matches(".*" + ViewModel.LogMessages.THE_CALCULATE_WAS_MADE
                + costAfterDelete));
    }

    private ViewModel viewModel;
    private static final double ERROR = 0.001;
}

package ru.unn.agile.BookSale.ViewModel.legacy;

import ru.unn.agile.BookSale.Model.legacy.Book;
import ru.unn.agile.BookSale.Model.legacy.BooksTable;
import ru.unn.agile.BookSale.Model.legacy.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ViewModel {

    public ViewModel(final ILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger parameter can't be null");
        }
        this.logger = logger;

        booksTable = new BooksTable();
        booksTable.defaultInitialization();
        order = new Order(booksTable);
        updateOrderedBooksList();
        updateButtonActivityState();
        calculateCost();
    }

    public final class LogMessages {
        public static final String THE_CALCULATE_WAS_MADE = "The calculate was made. Cost = ";
        public static final String ADD_BOOK_WAS_PRESSED = " was added to the order.";
        public static final String DELETE_BOOK_WAS_PRESSED = " was deleted from the order.";
        public static final String SELECT_BOOK = " was selected.";

        private LogMessages() {
        }
    }

    public void onSelectBookFromAvailableList(final Book book) {
        if (book != currentSelectedBook) {
            logger.log(selectBookMessage(book.getName()));
        }
        selectedBook = book;
        currentSelectedBook = selectedBook;
        updateButtonActivityState();
    }

    public void addSelectedBookToOrder() {
        logger.log(addBookMessage(selectedBook.getName()));
        order.addBook(selectedBook.getId());
        updateOrderedBooksList();
        updateButtonActivityState();
        calculateCost();
    }

    public void deleteSelectedBookFromOrder() {
        logger.log(deleteBookMessage(selectedBook.getName()));
        order.deleteBook(selectedBook.getId());
        updateOrderedBooksList();
        updateButtonActivityState();
        calculateCost();
    }

    public boolean isAddBookToOrderButtonEnabled() {
        return isAddBookToOrderButtonEnabled;
    }

    public boolean isDeleteBookFromOrderButtonEnabled() {
        return isDeleteBookFromOrderButtonEnabled;
    }

    public Book[] getAvailableBooks() {
        return booksTable.getBooks();
    }

    public Book getSelectedBook() {
        return selectedBook;
    }

    public OrderedBook[] getOrderedBooks() {
        return orderedBooks;
    }

    public double getCost() {
        return cost;
    }

    public List<String> getLog() {
        return logger.getLog();
    }

    public static class OrderedBook {
        public OrderedBook(final Book book, final Integer count) {
            this.book = book;
            this.count = count;
        }

        @Override
        public String toString() {
            return book.toString() + " - " + count.toString() + " PCs.";
        }

        public Book getBook() {
            return book;
        }

        public Integer getCount() {
            return count;
        }

        private Book book;
        private Integer count;
    }

    private void updateOrderedBooksList() {
        ArrayList<OrderedBook> tmpList = new ArrayList<>();
        order.getBooksList().forEach((UUID key, Integer value) ->
                tmpList.add(new OrderedBook(booksTable.getBookById(key), value))
        );
        orderedBooks = tmpList.toArray(new OrderedBook[tmpList.size()]);
    }

    private void updateButtonActivityState() {
        if (selectedBook != null) {
            this.isAddBookToOrderButtonEnabled = true;
            if (order.getCountBookById(selectedBook.getId()) != null) {
                isDeleteBookFromOrderButtonEnabled = true;
            } else {
                isDeleteBookFromOrderButtonEnabled = false;
            }
        }
    }

    private void calculateCost() {
        cost = order.getCost();
        logger.log(calculateCostMessage(cost));
    }

    private String selectBookMessage(final String bookName) {
        return bookName + LogMessages.SELECT_BOOK;
    }

    private String addBookMessage(final String bookName) {
        return bookName + LogMessages.ADD_BOOK_WAS_PRESSED;
    }

    private String deleteBookMessage(final String bookName) {
        return bookName + LogMessages.DELETE_BOOK_WAS_PRESSED;
    }

    private String calculateCostMessage(final double cost) {
        return LogMessages.THE_CALCULATE_WAS_MADE + cost;
    }

    private BooksTable booksTable;
    private Order order;
    private boolean isAddBookToOrderButtonEnabled = false;
    private boolean isDeleteBookFromOrderButtonEnabled = false;
    private Book selectedBook;
    private Book currentSelectedBook;
    private OrderedBook[] orderedBooks = new OrderedBook[0];
    private double cost = 0.0;
    private ILogger logger;
}

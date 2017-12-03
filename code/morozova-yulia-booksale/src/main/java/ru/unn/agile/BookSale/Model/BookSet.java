package ru.unn.agile.BookSale.Model;

import java.util.ArrayList;

interface Price {
    double BOOK_PRICE = 8.0;
}

public class BookSet {
    private ArrayList<Book> set;
    private int count;
    private int countDifferent;

    public BookSet() {
        this.set = new ArrayList<Book>();
        this.count = 0;
        this.countDifferent = 0;
    }

    public BookSet(final ArrayList<Book> bookSet) {
        this.set = bookSet;
        this.combineEqualBooks();
        this.count = this.count();
        this.countDifferent = this.countDifferent();
    }

    public void combineEqualBooks() {
        for (int i = 0; i < this.set.size(); i++) {
            for (int j = i + 1; j < this.set.size(); j++) {
                if (this.set.get(j).isEqual(this.set.get(i))) {
                    int newCount = this.set.get(j).count() + this.set.get(i).count();
                    this.set.get(i).setCount(newCount);
                    this.set.remove(this.set.get(j));
                }
            }
        }
    }

    public ArrayList<Book> set() {
        return this.set;
    }

    public int count() {
        int result = 0;
        for (int i = 0; i < this.set.size(); i++) {
            result = result + this.set.get(i).count();
        }
        return result;
    }

    public int countDifferent() {
        int result = this.set.size();
        for (int i = 0; i < this.set.size(); i++) {
            for (int j = i + 1; j < this.set.size(); j++) {
                if (this.set.get(j).isEqual(this.set.get(i))) {
                    result--;
                    break;
                }
            }
        }
        return result;
    }

    public Book searchBook(Book book) {
        for (int i = 0; i < this.set.size(); i++) {
            Book bookInSet = this.set.get(i);
            if (bookInSet.isEqual(book)) {
                return bookInSet;
            }
        }
        return null;
    }

    public void changeBookCount(Book book, int newCount) {
        int bookIndex = this.set.indexOf(book);
        this.set.get(bookIndex).setCount(newCount);
    }

    public void addBook(Book book) {
        Book bookInSet = this.searchBook((book));
        if (bookInSet == null) {
            this.set.add(book);
        } else {
            int newCount = bookInSet.count() + book.count();
            this.changeBookCount(bookInSet, newCount);
        }
        this.count = this.count();
        this.countDifferent = this.countDifferent();
    }

    public boolean deleteBook(Book book) {
        boolean isDeleted = false;
        Book bookInSet = this.searchBook(book);
        boolean canDelete = bookInSet != null && bookInSet.count() >= book.count();
        if (canDelete) {
            if (bookInSet.count() == book.count()) {
                this.set.remove(book);
            } else {
                int newCount = bookInSet.count() - book.count();
                this.changeBookCount(bookInSet, newCount);
            }
            this.count = this.count();
            this.countDifferent = this.countDifferent();
            isDeleted = true;
        }
        return isDeleted;
    }

    public double getMaxDiscount() {
        switch (this.countDifferent) {
            case 2:
                return 0.05;
            case 3:
                return 0.1;
            case 4:
                return 0.2;
            case 5:
                return 0.25;
            default:
                return 0.0;
        }
    }

    public double cost() {
        return Price.BOOK_PRICE * this.countDifferent * (1 - this.getMaxDiscount()) + Price.BOOK_PRICE * (this.count - this.countDifferent);
    }
}
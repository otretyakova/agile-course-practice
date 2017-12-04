package ru.unn.agile.BookSale.Model;

import java.util.ArrayList;
import java.util.List;

public final class BookSet {
    public static final double BOOK_PRICE = 8.0;
    public static final double[] AMOUNT_OF_DISCOUNT = {0.0, 0.0, 0.05, 0.1, 0.2, 0.25};
    private List<Book> set;
    private int count;
    private int countDifferent;

    public BookSet() {
        this.set = new ArrayList<Book>();
        this.count = 0;
        this.countDifferent = 0;
    }

    public BookSet(final List<Book> bookSet) {
        this.set = bookSet;
        this.combineEqualBooks();
        this.count = this.getCount();
        this.countDifferent = this.getCountDifferent();
    }

    public void combineEqualBooks() {
        for (int i = 0; i < this.set.size(); i++) {
            for (int j = i + 1; j < this.set.size(); j++) {
                if (this.set.get(i).isEqual(this.set.get(j))) {
                    final int newCount = this.set.get(j).count() + this.set.get(i).count();
                    this.set.get(i).setCount(newCount);
                    this.set.remove(this.set.get(j));
                }
            }
        }
    }

    public List<Book> set() {
        return this.set;
    }

    public int count() {
        return this.count;
    }

    public int countDifferent() {
        return this.countDifferent;
    }

    public int getCount() {
        int result = 0;
        for (int i = 0; i < this.set.size(); i++) {
            result = result + this.set.get(i).count();
        }
        return result;
    }

    public int getCountDifferent() {
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

    public Book searchBook(final Book book) {
        for (int i = 0; i < this.set.size(); i++) {
            Book bookInSet = this.set.get(i);
            if (bookInSet.isEqual(book)) {
                return bookInSet;
            }
        }
        return null;
    }

    public void changeBookCount(final Book book, final int newCount) {
        int bookIndex = this.set.indexOf(book);
        this.set.get(bookIndex).setCount(newCount);
        this.count = this.getCount();
        this.countDifferent = this.getCountDifferent();
    }

    public void addBook(final Book book) {
        Book bookInSet = this.searchBook((book));
        if (bookInSet == null) {
            this.set.add(book);
        } else {
            final int newCount = bookInSet.count() + book.count();
            this.changeBookCount(bookInSet, newCount);
        }
        this.count = this.getCount();
        this.countDifferent = this.getCountDifferent();
    }

    public boolean deleteBook(final Book book) {
        boolean isDeleted = false;
        Book bookInSet = this.searchBook(book);
        boolean canDelete = bookInSet != null && bookInSet.count() >= book.count();
        if (canDelete) {
            if (bookInSet.count() == book.count()) {
                this.set.remove(book);
            } else {
                final int newCount = bookInSet.count() - book.count();
                this.changeBookCount(bookInSet, newCount);
            }
            this.count = this.getCount();
            this.countDifferent = this.getCountDifferent();
            isDeleted = true;
        }
        return isDeleted;
    }

    public double getMaxDiscount() {
        return AMOUNT_OF_DISCOUNT[this.countDifferent];
    }

    public double cost() {
        final double discount = this.getMaxDiscount();
        final double priceWithDiscount = BOOK_PRICE * this.countDifferent * (1 - discount);
        final double priceWithoutDiscount = BOOK_PRICE * (this.count - this.countDifferent);
        return priceWithDiscount + priceWithoutDiscount;
    }
}

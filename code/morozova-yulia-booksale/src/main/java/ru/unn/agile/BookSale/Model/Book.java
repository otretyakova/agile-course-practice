package ru.unn.agile.BookSale.Model;

enum HarryPotterBooks {
    philosophersStone,
    chamberOfSecret,
    prisonerOfAzkaban,
    gobletOfFire,
    orderOfThePhoenix
}

public class Book {
    private HarryPotterBooks part;
    private int count;

    public Book(final HarryPotterBooks part, final int count) {
        this.part = part;
        this.count = count;
    }

    public HarryPotterBooks part() {
        return this.part;
    }

    public int count() {
        return this.count;
    }

    public void setCount(int newCount) {
        this.count = newCount;
    }

    public boolean isEqual(Book book) {
        return this.part == book.part;
    }
}

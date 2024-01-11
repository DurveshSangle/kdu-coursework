package kdu.backend.hw4.q4;

import kdu.backend.hw4.Logging;

import java.util.*;

public class SetDemo {

    public static final Logging log = new Logging();

    /**
     * Return sorted book set according to comparator
     *
     * @param comparator ascending, descending or null comparator.
     *
     * @return sorted set of books
     * */
    public static Set<Book> treeSetDemo(Comparator<Book> comparator) {
        Book book1 = new Book("Harry Potter", "J.K.Rowling", 1997);
        Book book2 = new Book("Harry Potter", "J.K.Rowling", 1997);
        Book book3 = new Book("Walden", "Henry David Thoreau", 1854);
        Book book4 = new Book("Effective Java", "Joshua Bloch", 2008);
        Book book5 = new Book("The Last Lecture", "Randy Pausch", 2008);

        Set<Book> books = new TreeSet<>();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);

        if(comparator==null) {
            log.logInfo("Books sorted with comparator as null");
            return books;
        }

        log.logInfo("Books sorted with comparator as "+comparator.getClass());

        Set<Book> sortedBooks = new TreeSet<>(comparator);
        sortedBooks.addAll(books);
        return sortedBooks;
    }

    public static void main(String[] args) {
        Set<Book> books1 = treeSetDemo(null);
        log.logInfo(Arrays.toString(books1.toArray()));
        Set<Book> books2 = treeSetDemo(new PubDateAscComparator());
        log.logInfo(Arrays.toString(books2.toArray()));
        Set<Book> books3 = treeSetDemo(new PubDateDescComparator());
        log.logInfo(Arrays.toString(books3.toArray()));
    }

}
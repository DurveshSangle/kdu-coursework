package kdu.backend.hw4.q4;

import java.util.Comparator;

public class PubDateAscComparator implements Comparator<Book>{
    public int compare(Book book1, Book book2){
        if(book1.getYear()>book2.getYear()) return 1;
        else if(book1.getYear()<book2.getYear()) return -1;
        else return book1.compareTo(book2);
    }
}

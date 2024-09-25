package kdu.backend.hw4.q4;

public class Book implements Comparable<Object> {
    private String title;
    private String author;
    private int year;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Book(String title, String author, int year) {
        super();
        this.title = title;
        this.author = author;
        this.year = year;
    }

    @Override
    public String toString() {
        return "\nBook [title=" + title + ", author=" + author + ", year=" + year + "]";
    }

    public int compareTo(Object book) {
        return getTitle().compareTo(((Book)book).getTitle()); // utilizing String’s compareTo
    }

}
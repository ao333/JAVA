package com;

import org.junit.Before;
import org.junit.Test;
import java.util.Set;
import static org.junit.Assert.*;

public class InMemoryBookDatabaseTest {
    private BookDatabase bookDatabase;

    @Before
    public void setUp() {
        bookDatabase = new InMemoryBookDatabase();
    }

    @Test // Test addTitle & getAuthorsByTitle
    public void testAddAndGetTitle() { 
        bookDatabase.addTitle("Book0", "Author0", "Author1", "");
        Set<String> authors = bookDatabase.getAuthorsByTitle("Book0");
        assertTrue(authors.contains("Author0"));
        assertTrue(authors.contains("Author1")); // Test many-authors-to-one-title relationship
        assertTrue(authors.contains("")); // Edge case
    }

    @Test // Test removeTitle
    public void testRemoveTitle() {
        bookDatabase.addTitle("Book1", "Author2");
        bookDatabase.removeTitle("Book1");
        Set<String> authors = bookDatabase.getAuthorsByTitle("Book1");
        assertTrue(authors.isEmpty());
    }

    @Test // test Get 'ALL' Titles from 'Single' Author
    public void testGetTitlesByAuthor() {
        bookDatabase.addTitle("Book2", "Author3");
        bookDatabase.addTitle("Book3", "Author3");
        Set<String> titles = bookDatabase.getTitlesByAuthor("Author3");
        assertTrue(titles.contains("Book2"));
        assertTrue(titles.contains("Book3"));
    }

    @Test // Test Remove 'ALL' Titles from 'Single' Author
    public void testRemoveTitlesByAuthor() {
        bookDatabase.addTitle("Book4", "Author4");
        bookDatabase.addTitle("Book5", "Author4");
        bookDatabase.removeTitlesByAuthor("Author4");
        assertTrue(bookDatabase.getTitlesByAuthor("Author4").isEmpty());
    }
}
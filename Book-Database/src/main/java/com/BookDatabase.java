package com;

import java.util.*;

public interface BookDatabase {
    void addTitle(String title, String... authors);
    void removeTitle(String title);
    void removeTitlesByAuthor(String author);
    Set<String> getTitlesByAuthor(String author);
    Set<String> getAuthorsByTitle(String title);
}
package com;

import java.util.*;

public class InMemoryBookDatabase implements BookDatabase {
    private final Map<String, Set<String>> titleToAuthors = new HashMap<>();
    private final Map<String, Set<String>> authorToTitles = new HashMap<>();

    @Override
    public void addTitle(String title, String... authors) {
        Set<String> authorSet = new HashSet<>(Arrays.asList(authors));
        titleToAuthors.put(title, authorSet);
        for (String author : authors) {
            authorToTitles.computeIfAbsent(author, k -> new HashSet<>()).add(title);
        }
    }

    @Override
    public void removeTitle(String title) {
        Set<String> authors = titleToAuthors.remove(title);
        if (authors != null) {
            for (String author : authors) {
                Set<String> titles = authorToTitles.get(author);
                if (titles != null) {
                    titles.remove(title);
                    if (titles.isEmpty()) {
                        authorToTitles.remove(author);
                    }
                }
            }
        }
    }

    @Override
    public void removeTitlesByAuthor(String author) {
        Set<String> titles = authorToTitles.remove(author);
        if (titles != null) {
            for (String title : titles) {
                Set<String> authors = titleToAuthors.get(title);
                if (authors != null) {
                    authors.remove(author);
                    if (authors.isEmpty()) {
                        titleToAuthors.remove(title);
                    }
                }
            }
        }
    }

    @Override // Uses HashMap for fast lookup performance
    public Set<String> getTitlesByAuthor(String author) {
        return authorToTitles.getOrDefault(author, Collections.emptySet());
    }

    @Override // Uses HashMap for fast lookup performance
    public Set<String> getAuthorsByTitle(String title) {
        return titleToAuthors.getOrDefault(title, Collections.emptySet());
    }
}
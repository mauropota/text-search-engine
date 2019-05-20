package com.company.searcheng.text.cl.search;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class SearchByWordStrategy implements SearchStrategy {

    private static final String WORD_SEPARATOR = "\\s";

    public SearchByWordStrategy() {}

    public int search(String searchedWords, String knowledge) {

        List<Integer> matches = new ArrayList<>();

        getStream(searchedWords).forEach(word -> {
            if (getStream(knowledge).anyMatch(word::equals)) {
                matches.add(1);
            }
        });

        return computeRelevance(searchedWords, matches);
    }

    private Stream<String> getStream(String content) {
        return Stream.of(content.toLowerCase().trim().split(WORD_SEPARATOR));
    }

    private int computeRelevance(String searchedWords, List<Integer> matches) {
        int relevance = (int) ((matches.size() * 100) / getStream(searchedWords).count());
        return relevance;
    }
}

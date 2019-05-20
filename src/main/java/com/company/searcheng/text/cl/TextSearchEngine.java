package com.company.searcheng.text.cl;

import com.company.searcheng.text.cl.repository.resource.Resource;
import com.company.searcheng.text.cl.search.SearchStrategy;

import java.util.*;

public class TextSearchEngine {

    private static final int MAX_RESULTS = 10;

    private final List<Resource> resources;
    private final SearchStrategy searchStrategy;

    public TextSearchEngine(List<Resource> resources, SearchStrategy searchStrategy) {
        this.resources = resources;
        this.searchStrategy = searchStrategy;
    }

    public Map<String, Integer> getResults(String textSearch) {

        Map<String, Integer> results = new HashMap<>();
        resources.forEach(resource -> {
                results.put(
                        resource.getFileName(),
                        searchStrategy.search(textSearch, resource.getContent())
                );
        });

        return filterResults(results);
    }

    private Map<String, Integer> filterResults(Map<String, Integer> results) {

        Map<String, Integer> orderedResults = new LinkedHashMap<>();

        results.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(MAX_RESULTS)
                .forEachOrdered(entry ->
                        orderedResults.put(entry.getKey(), entry.getValue())
                );

        return orderedResults;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextSearchEngine that = (TextSearchEngine) o;

        if (!resources.equals(that.resources)) return false;
        return searchStrategy.equals(that.searchStrategy);
    }

    @Override
    public int hashCode() {
        int result = resources.hashCode();
        result = 31 * result + searchStrategy.hashCode();
        return result;
    }
}

package com.company.searcheng.text.cl;

import com.company.searcheng.text.cl.repository.resource.Resource;
import com.company.searcheng.text.cl.search.SearchStrategy;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasValue;

public class SearchEngineTest {

    public static final String INPUT = "ANY_INPUT";
    public static final String CONTENT = "ANY_CONTENT";
    public static final String FILE_NAME = "ANY_FILE";
    public static final int RESULT_RELEVANCE = 50;

    Mockery context = new Mockery();

    private final SearchStrategy searchStrategy = context.mock(SearchStrategy.class);
    private final Resource resource = context.mock(Resource.class);

    @Test
    public void returnsNoResults() throws Exception {

        context.checking(new Expectations()
                         {{
                             never(resource).getFileName();
                             never(resource).getContent();will(returnValue(CONTENT));
                             never(searchStrategy).search(with(INPUT), with(CONTENT));
                         }}
        );
        Map<String, Integer> results = new TextSearchEngine(Collections.EMPTY_LIST, searchStrategy)
                .getResults(INPUT);

        assertThat(results.isEmpty(), is(true));
    }

    @Test
    public void returnsOneResult() throws Exception {

        context.checking(new Expectations()
                         {{
                             oneOf(resource).getFileName();will(returnValue(FILE_NAME));
                             oneOf(resource).getContent();will(returnValue(CONTENT));
                             oneOf(searchStrategy).search(with(INPUT), with(CONTENT));will(returnValue(RESULT_RELEVANCE));
                         }}
        );
        Map<String, Integer> results = new TextSearchEngine(Arrays.asList(resource), searchStrategy)
                .getResults(INPUT);

        assertThat(results.size(), is(1));
        assertThat(results, hasKey(FILE_NAME));
        assertThat(results, hasValue(RESULT_RELEVANCE));
    }

    @Test
    public void returnsMaxTenResults() throws Exception {

        List<Resource> resources = Arrays.asList(
                resource, resource, resource, resource, resource, resource, resource, resource, resource, resource, resource);


        context.checking(new Expectations()
                         {{
                             exactly(11).of(resource).getFileName();
                             will(onConsecutiveCalls(
                                     returnValue(FILE_NAME+0),
                                     returnValue(FILE_NAME+1),
                                     returnValue(FILE_NAME+2),
                                     returnValue(FILE_NAME+3),
                                     returnValue(FILE_NAME+4),
                                     returnValue(FILE_NAME+5),
                                     returnValue(FILE_NAME+6),
                                     returnValue(FILE_NAME+7),
                                     returnValue(FILE_NAME+8),
                                     returnValue(FILE_NAME+9),
                                     returnValue(FILE_NAME+10)
                             ));
                             exactly(11).of(resource).getContent();will(returnValue(CONTENT));
                             exactly(11).of(searchStrategy).search(with(INPUT), with(CONTENT));
                         }}
        );

        Map<String, Integer> results = new TextSearchEngine(resources, searchStrategy)
                .getResults(INPUT);

        assertThat(results.size(), is(10));
    }

    @Test
    public void resultsAreOrderedByRelevance() throws Exception {

        List<Resource> resources = Arrays.asList(
                resource, resource, resource, resource);


        context.checking(new Expectations()
                         {{
                             exactly(4).of(resource).getFileName();
                             will(onConsecutiveCalls(
                                     returnValue(FILE_NAME+0),
                                     returnValue(FILE_NAME+1),
                                     returnValue(FILE_NAME+2),
                                     returnValue(FILE_NAME+3)
                             ));
                             exactly(4).of(resource).getContent();will(returnValue(CONTENT));
                             exactly(4).of(searchStrategy).search(with(INPUT), with(CONTENT));
                             will(onConsecutiveCalls(
                                     returnValue(RESULT_RELEVANCE),
                                     returnValue(RESULT_RELEVANCE+10),
                                     returnValue(RESULT_RELEVANCE+30),
                                     returnValue(RESULT_RELEVANCE+50)
                             ));
                         }}
        );

        Map<String, Integer> results = new TextSearchEngine(resources, searchStrategy)
                .getResults(INPUT);

        assertThat(results.values(), contains(100, 80, 60, 50));
        assertThat(results.keySet(), contains(FILE_NAME+3, FILE_NAME+2, FILE_NAME+1, FILE_NAME+0));
    }
}
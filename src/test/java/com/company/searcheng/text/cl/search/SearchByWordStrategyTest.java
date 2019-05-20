package com.company.searcheng.text.cl.search;

import com.company.searcheng.text.cl.search.SearchByWordStrategy;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SearchByWordStrategyTest {

    public static final String INPUT_1 = "To be, or not to be? That is the question-";
    public static final String INPUT_2 = "People are strange";
    public static final String INPUT_3 = "it is not good";
    private static String CONTENT = "To be, or not to be? That is the question-";

    @Test
    public void fullMatch() throws Exception {

        int result = new SearchByWordStrategy().search(INPUT_1, CONTENT);

        assertThat(result, is(100));
    }

    @Test
    public void zeroMatch() throws Exception {

        int result = new SearchByWordStrategy().search(INPUT_2, CONTENT);

        assertThat(result, is(0));
    }

    @Test
    public void HalfMatch() throws Exception {

        int result = new SearchByWordStrategy().search(INPUT_3, CONTENT);

        assertThat(result, is(50));
    }
}

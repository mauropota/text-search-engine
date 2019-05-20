package com.company.searcheng.text.cl.repository.resource;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FileResourceTest {

    private static final String TXT_FILE = "src/test/resources/file1.txt";
    private static final String FILE_NAME = "file1.txt";
    private static final String CONTENT = "To be or not to be.";

    @Test
    public void getRourceContent() throws Exception {

        assertThat(new FileResource(TXT_FILE).getContent(), is(CONTENT));
    }

    @Test
    public void getFileName() throws Exception {
        assertThat(new FileResource(TXT_FILE).getFileName(), is(FILE_NAME));
    }
}
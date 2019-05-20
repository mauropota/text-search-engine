package com.company.searcheng.text.cl.repository;

import com.company.searcheng.text.cl.repository.FileRepository;
import com.company.searcheng.text.cl.repository.resource.FileResource;
import com.company.searcheng.text.cl.repository.resource.Resource;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;

public class FileRepositoryTest {

    private static final String AVAILABLE_REPOSITORY = "src/test/resources";
    private static final String NOT_AVAILABLE_REPOSITORY = "NOT_A_DIR";

    private static final String TXT_FILE = "src/test/resources/file1.txt";
    private static final String NOT_A_TEXT_FILE = "src/test/resources/file5.jpg";

    @Test(expected = IllegalArgumentException.class)
    public void repositoryNotAvailable() throws Exception {
        new FileRepository(NOT_AVAILABLE_REPOSITORY);
    }

    @Test
    public void getTextResources() throws Exception {
        List<Resource> resources = new FileRepository(AVAILABLE_REPOSITORY).getResource(".txt");

        assertThat(resources, hasItem(new FileResource(TXT_FILE)));
        assertThat(resources, not(hasItem(new FileResource(NOT_A_TEXT_FILE))));
        assertThat(resources, hasSize(8));
    }

    @Test
    public void repositoryNotContainsCsvResources() throws Exception {
        List<Resource> resources = new FileRepository(AVAILABLE_REPOSITORY).getResource(".csv");

        assertThat(resources, empty());
    }
}
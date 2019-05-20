package com.company.searcheng.text.cl.repository;

import com.company.searcheng.text.cl.repository.resource.FileResource;
import com.company.searcheng.text.cl.repository.resource.Resource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileRepository implements Repository {

    private final Path repositoryPath;

    public FileRepository(String resource) {

        if (!Files.isDirectory(Paths.get(resource))) {
            throw new IllegalArgumentException("Repository not available");
        }

        this.repositoryPath = Paths.get(resource);
    }

    public List<Resource> getResource(String type) {
        try {
            return Files.list(repositoryPath)
                    .filter(path -> path.getFileName().toString().endsWith(type))
                    .map(path -> new FileResource(path.toString()))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FileRepository that = (FileRepository) o;

        return repositoryPath.equals(that.repositoryPath);
    }

    @Override
    public int hashCode() {
        return repositoryPath.hashCode();
    }
}

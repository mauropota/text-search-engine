package com.company.searcheng.text.cl.repository.resource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileResource implements Resource {

    private final String pathName;

    public FileResource(String resource) {
        this.pathName = resource;
    }

    public String getFileName() {
        return Paths.get(pathName).getFileName().toString();
    }

    public String getContent() {
        try {
            return new String(Files.readAllBytes(Paths.get(pathName)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FileResource that = (FileResource) o;

        return pathName.equals(that.pathName);
    }

    @Override
    public int hashCode() {
        return pathName.hashCode();
    }
}

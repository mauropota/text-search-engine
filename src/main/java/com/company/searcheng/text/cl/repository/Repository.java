package com.company.searcheng.text.cl.repository;

import com.company.searcheng.text.cl.repository.resource.Resource;

import java.util.List;

public interface Repository {
    List<Resource> getResource(String type);
}

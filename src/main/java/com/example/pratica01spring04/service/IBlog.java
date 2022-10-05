package com.example.pratica01spring04.service;

import com.example.pratica01spring04.dto.BlogDTO;
import com.example.pratica01spring04.exception.BlogAlreadyExistsException;
import com.example.pratica01spring04.exception.NotFoundException;
import com.example.pratica01spring04.model.Blog;

import java.util.ArrayList;
import java.util.List;

public interface IBlog {
    Blog getById(int id) throws NotFoundException;
    void create(Blog blog) throws BlogAlreadyExistsException;

    void delete(int id) throws NotFoundException;

    Blog update(Blog blog, int id);

    List<BlogDTO> getAll();
}

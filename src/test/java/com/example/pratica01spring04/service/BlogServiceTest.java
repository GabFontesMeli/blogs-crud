package com.example.pratica01spring04.service;

import com.example.pratica01spring04.model.Blog;
import com.example.pratica01spring04.repository.BlogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BlogServiceTest {

    @InjectMocks
    private BlogService blogService;

    @Mock
    private BlogRepository blogRepository;

    @Test
    void create() {
        final Blog blog = new Blog(null,"titulo", "nomeDoAutor", "dataPublicacao");

        given(blogRepository.getById(blog.getId())).willReturn(Optional.of(blog));

        verify(blogRepository, times(1)).create(blog);

    }

    @Test
    void getById() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }

    @Test
    void getAll() {
    }
}
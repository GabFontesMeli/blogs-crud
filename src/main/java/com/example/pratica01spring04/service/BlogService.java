package com.example.pratica01spring04.service;

import com.example.pratica01spring04.dto.BlogDTO;
import com.example.pratica01spring04.exception.BlogAlreadyExistsException;
import com.example.pratica01spring04.exception.NotFoundException;
import com.example.pratica01spring04.model.Blog;
import com.example.pratica01spring04.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BlogService implements IBlog{
    @Autowired
    private BlogRepository blogRepository;

    @Override
    public void create(Blog blog) {
        Optional<Blog> blogIfExists = blogRepository.getById(blog.getId());

        if (blogIfExists.isEmpty()) {
            blogRepository.create(blog);
            return;
        }

        throw new BlogAlreadyExistsException("Já existe um blog com o id " + blog.getId() + ".");
    }

    @Override
    public Blog getById(int id) {
        Optional<Blog> blog = blogRepository.getById(id);

        if (blog.isEmpty()) {
            throw new NotFoundException("Não foi possível encontrar um blog com o id " + id + ".");
        }
        return blog.get();
    }

    @Override
    public void delete(int id) {
        Optional<Blog> blog = blogRepository.getById(id);

        if (blog.isEmpty()) {
            throw new NotFoundException("Não foi possível encontrar um blog com o id " + id + ".");
        }
        blogRepository.delete(id);
    }

    @Override
    public Blog update(Blog blog, int id) {
        Optional<Blog> updatedBlog = blogRepository.update(blog, id);

        if (updatedBlog.isEmpty()) {
            throw new NotFoundException("Não foi possível encontrar um blog com o id " + id + ".");
        }
        return updatedBlog.get();
    }

    @Override
    public List<BlogDTO> getAll() {
        return blogRepository.getAll().stream()
                //.map(blog -> new BlogDTO(blog)
                .map(BlogDTO::new)
                .collect(Collectors.toList());
    }
}

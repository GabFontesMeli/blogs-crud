package com.example.pratica01spring04.repository;

import com.example.pratica01spring04.model.Blog;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class BlogRepository {
    private final String path = "src/main/resources/blogs.json";
    ObjectMapper mapper = new ObjectMapper();

    public Optional<Blog> getById(int id) {
        List<Blog> blogs = null;
        try {
            blogs = Arrays.asList(mapper.readValue(new File(path), Blog[].class));
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        for(Blog b: blogs) {
            if(b.getId().equals(id)) {
                return Optional.of(b);
            }
        }
        return Optional.empty();
    }
    public List<Blog> getAll() {
        List<Blog> blogs = null;
        try {
            blogs = Arrays.asList(mapper.readValue(new File(path), Blog[].class));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return blogs;
    }

    public void delete(int id) {
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        List<Blog> blogs = getAll();

        blogs = new ArrayList<>(blogs);

        blogs.removeIf(blog -> blog.getId().equals(id));

        try {
            writer.writeValue(new File(path), blogs);
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void create(Blog newBlog) {
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        List<Blog> blogs = getAll();

        blogs = new ArrayList<>(blogs);

        blogs.add(newBlog);

        try {
            writer.writeValue(new File(path), blogs);
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Optional<Blog> update(Blog updatedBlog, int id) {
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

        List<Blog> blogs = null;

        try {
            blogs = Arrays.asList(mapper.readValue(new File(path), Blog[].class));
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        for(Blog b: blogs) {
            if(b.getId().equals(id)) {
                b.setTitulo(updatedBlog.getTitulo());
                b.setNomeDoAutor(updatedBlog.getNomeDoAutor());
                b.setDataPublicacao(updatedBlog.getDataPublicacao());

                blogs = new ArrayList<>(blogs);

                try {
                    writer.writeValue(new File(path), blogs);
                }catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                return Optional.of(b);
            }
        }

        return Optional.empty();
    }
}

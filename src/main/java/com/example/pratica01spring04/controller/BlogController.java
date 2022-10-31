package com.example.pratica01spring04.controller;

import com.example.pratica01spring04.dto.BlogDTO;
import com.example.pratica01spring04.model.Blog;
import com.example.pratica01spring04.service.IBlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private IBlog blogService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Blog blog) {
        blogService.create(blog);

        return new ResponseEntity<>(blog.getId(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BlogDTO>> getAll() {
        return new ResponseEntity<>(blogService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> getById(@PathVariable int id) {
        return new ResponseEntity<>(blogService.getById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        blogService.delete(id);

        return new ResponseEntity<>("O blog de id " + id + " foi deletado com sucesso.", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Blog> update(@RequestBody Blog updatedBlog, @PathVariable int id) {
        return new ResponseEntity<>(blogService.update(updatedBlog, id), HttpStatus.OK);
    }
}

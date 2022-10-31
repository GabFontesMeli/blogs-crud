package com.example.pratica01spring04.controller;

import com.example.pratica01spring04.dto.BlogDTO;
import com.example.pratica01spring04.model.Blog;
import com.example.pratica01spring04.service.BlogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Log4j2
@WebMvcTest(BlogController.class)
class BlogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BlogService blogService;

    private Blog blog;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    void setup() {
        blog = new Blog(1, "Blog 1", "Author 1", "01/01/2021");
    }

    @Test
    @DisplayName("Cria um blog")
        void create() throws Exception {

            ResultActions response = mockMvc.perform(
                    post("/blog")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(blog))
            );

        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$").value(blog.getId()));
    }

    @Test
    @DisplayName("Retorna todos os blogs")
    void getAll_returnsAllBlogs() throws Exception {

        final List<BlogDTO> BLOG_DTO_LIST = new ArrayList<>();

        final BlogDTO BLOG_DTO_1 = new BlogDTO(1, "Blog 1", "Author 1", "01/01/2021");
        final BlogDTO BLOG_DTO_2 = new BlogDTO(2, "Blog 2", "Author 2", "02/02/2022");

        BLOG_DTO_LIST.add(BLOG_DTO_1);
        BLOG_DTO_LIST.add(BLOG_DTO_2);

        BDDMockito.when(blogService.getAll())
                .thenReturn(BLOG_DTO_LIST);

        ResultActions response = mockMvc.perform(
                get("/blog")
                        .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].titulo").value("Blog 1"))
                .andExpect(jsonPath("$[0].nomeDoAutor").value("Author 1"))
                .andExpect(jsonPath("$[0].dataPublicacao").value("01/01/2021"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].titulo").value("Blog 2"))
                .andExpect(jsonPath("$[1].nomeDoAutor").value("Author 2"))
                .andExpect(jsonPath("$[1].dataPublicacao").value("02/02/2022"));
    }

    @Test
    @DisplayName("Retorna um blog pelo id")
    void getById_returnsAnBlog_whenIdExists() throws Exception {

        BDDMockito.when(blogService.getById(blog.getId()))
                .thenReturn(blog);

        ResultActions response = mockMvc.perform(
                get("/blog/1")
                        .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.titulo").value("Blog 1"))
                .andExpect(jsonPath("$.nomeDoAutor").value("Author 1"))
                .andExpect(jsonPath("$.dataPublicacao").value("01/01/2021"));
    }

    @Test
    @DisplayName("Remove um blog pelo id")
    void delete_removeABlogFromTheList_whenIdExists() throws Exception {

        log.info("testeeeeeee");

        ResultActions response = mockMvc.perform(
                delete("/blog/1")
                        .contentType(MediaType.APPLICATION_JSON));


        response.andExpect(status().isOk())
                .andExpect(jsonPath("$").value("O blog de id 1 foi deletado com sucesso."));
    }

    @Test
    void update() throws Exception {

        Blog blogWithoutId = new Blog("Blog 5", "Author 5", "05/05/2025");

        BDDMockito.when(blogService.update(blogWithoutId, blog.getId()))
                .thenReturn(blog);

        ResultActions response = mockMvc.perform(
                put("/blog/{id}", 1)
                        .content(asJsonString(new Blog("Blog 5", "Author 5", "05/05/2025")))
                        .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.titulo").value("Blog 5"))
                .andExpect(jsonPath("$.nomeDoAutor").value("Author 5"))
                .andExpect(jsonPath("$.dataPublicacao").value("05/05/2025"));

    }
}
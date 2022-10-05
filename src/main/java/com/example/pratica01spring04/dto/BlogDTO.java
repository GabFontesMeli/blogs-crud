package com.example.pratica01spring04.dto;

import com.example.pratica01spring04.model.Blog;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogDTO implements Serializable {
    private int id;
    private String titulo, nomeDoAutor, dataPublicacao;

    public BlogDTO(Blog blog) {
        this.id = blog.getId();
        this.titulo = blog.getTitulo();
        this.nomeDoAutor = blog.getNomeDoAutor();
        this.dataPublicacao = blog.getDataPublicacao();
    }

}

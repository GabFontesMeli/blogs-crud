package com.example.pratica01spring04.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Blog {
    private Integer id;
    private String titulo, nomeDoAutor, dataPublicacao;

    public Blog(String titulo, String nomeDoAutor, String dataPublicacao) {
        this.titulo = titulo;
        this.nomeDoAutor = nomeDoAutor;
        this.dataPublicacao = dataPublicacao;
    }
}

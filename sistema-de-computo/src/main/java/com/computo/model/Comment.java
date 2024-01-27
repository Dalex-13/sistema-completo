package com.computo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "comentarios")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String body;


    @ManyToOne(fetch = FetchType.LAZY) //lazy es solicitado cuando lo necesitemos
    @JoinColumn(name = "producto_id", nullable = false)
    private Product product;

}

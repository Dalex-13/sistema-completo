package com.computo.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "producto", uniqueConstraints = {@UniqueConstraint(columnNames = {"name","description"})})
public class Product {//clase padre

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    private int amount;

    private double price;

                                            ///con esto le indicamos que cuando eliminemos un producto se eliminen todos los comentarios
    @JsonBackReference
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();//este es la clase hijo

}

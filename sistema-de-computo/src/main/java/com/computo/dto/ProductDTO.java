package com.computo.dto;

import com.computo.model.Comment;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
public class ProductDTO {


    private Long id;

    @NotEmpty
    @Size(min = 4, max = 50,message = "El nombre del producto debe tener al menos 4 caracteres")
    private String name;

    @NotEmpty
    @Size(min = 4, max = 100,message = "La descripción del producto debe tener al menos 4 caracteres")
    private String description;

    private int amount;

    private double price;

    private Set<Comment> comments;



}

package com.computo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CommentDTO {

    private Long id;


    private String name;

    @NotEmpty(message = "El email no debe estar vacío o nulo")
    @Email
    private String email;

    @NotEmpty
    @Size(min = 4, max = 100,message = "El comentario debe tener al menos 4 caracteres")
    private String body;

}

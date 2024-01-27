package com.computo.service;

import com.computo.dto.CommentDTO;

import java.util.List;

public interface CommentService {

    public CommentDTO createComment (Long productId,CommentDTO commentDTO);

    public List<CommentDTO> obtenerComentarioPorId(Long comentarioId);

    public CommentDTO obtenerComentarioYProductoId(Long productoId, Long comentarioId);

    public CommentDTO actualizarComentario(Long productoId, Long comentarioId, CommentDTO commentDTO);

    public void eliminarComentario(Long productoId, Long comentarioId);


}

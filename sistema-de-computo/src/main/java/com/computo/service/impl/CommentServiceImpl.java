package com.computo.service.impl;

import com.computo.dao.CommentDao;
import com.computo.dao.ProductDao;
import com.computo.dto.CommentDTO;
import com.computo.dto.ProductDTO;
import com.computo.exceptions.BlogAppExceptions;
import com.computo.model.Comment;
import com.computo.model.Product;
import com.computo.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private ProductDao productDao;


    @Override
    public CommentDTO createComment(Long productId, CommentDTO commentDTO) {
        Comment comment = maperEntity(commentDTO);

        Product product = productDao.findById(productId).orElseThrow(() -> new ResolutionException("no se encuentra el comentario con el id:"+productId));

        comment.setProduct(product);
        Comment newComment = commentDao.save(comment);
        return maperDTO(newComment);
    }

    @Override
    public List<CommentDTO> obtenerComentarioPorId(Long comentarioId) {
        List<Comment> comments = commentDao.findByProductId(comentarioId);
        return comments.stream().map(c -> maperDTO(c)).collect(Collectors.toList());
    }

    @Override
    public CommentDTO obtenerComentarioYProductoId(Long productoId, Long comentarioId) {
        Product product = productDao.findById(productoId).orElseThrow(() -> new ResolutionException("no se encuentra el producto con el id:"+productoId));
        Comment comment = commentDao.findById(comentarioId).orElseThrow(() -> new ResolutionException("no se encuentra el comentario con el id:"+comentarioId));

        if (!comment.getProduct().getId().equals(product.getId())){
            throw new BlogAppExceptions(HttpStatus.BAD_REQUEST, "El comentario no pertenece al producto");
        }
        return maperDTO(comment);
    }





    @Override
    public CommentDTO actualizarComentario(Long productoId, Long comentarioId, CommentDTO commentDTO) {
        Product product = productDao.findById(productoId).orElseThrow(() -> new ResolutionException("no se encuentra el producto con el id:"+productoId));
        Comment comment = commentDao.findById(comentarioId).orElseThrow(() -> new ResolutionException("no se encuentra el comentario con el id:"+comentarioId));

        if (!comment.getProduct().getId().equals(product.getId())){
            throw new BlogAppExceptions(HttpStatus.BAD_REQUEST, "El comentario no pertenece al producto");
        }

        comment.setName(commentDTO.getName());
        comment.setEmail(commentDTO.getEmail());
        comment.setBody(commentDTO.getBody());

        Comment comentarioActualizado = commentDao.save(comment);

        return maperDTO(comentarioActualizado);
    }

    @Override
    public void eliminarComentario(Long productoId, Long comentarioId) {
        Product product = productDao.findById(productoId).orElseThrow(() -> new ResolutionException("no se encuentra el producto con el id:"+productoId));
        Comment comment = commentDao.findById(comentarioId).orElseThrow(() -> new ResolutionException("no se encuentra el comentario con el id:"+comentarioId));

        if (!comment.getProduct().getId().equals(product.getId())){
            throw new BlogAppExceptions(HttpStatus.BAD_REQUEST, "El comentario no pertenece al producto");
        }

        commentDao.delete(comment);
    }





















    private CommentDTO maperDTO(Comment comment){

        //utilizamos la libreria del modelMapper
        CommentDTO commentDTO = modelMapper.map(comment, CommentDTO.class);

/*
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setAmount(product.getAmount());
        productDTO.setPrice(product.getPrice());
*/
        return commentDTO;
    }

    private Comment maperEntity(CommentDTO commentDTO){
        Comment comment = modelMapper.map(commentDTO, Comment.class);
/*
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setAmount(productDTO.getAmount());
        product.setPrice(productDTO.getPrice());
*/
        return comment;
    }
}

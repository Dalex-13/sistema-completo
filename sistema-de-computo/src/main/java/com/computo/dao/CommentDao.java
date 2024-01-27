package com.computo.dao;

import com.computo.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao extends JpaRepository<Comment, Long> {

    public List<Comment> findByProductId(Long productId);

}

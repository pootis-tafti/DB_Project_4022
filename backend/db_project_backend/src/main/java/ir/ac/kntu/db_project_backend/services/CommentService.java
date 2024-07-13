package ir.ac.kntu.db_project_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import ir.ac.kntu.db_project_backend.models.Comment;
import ir.ac.kntu.db_project_backend.repositories.CommentRepository;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @CacheEvict(value = "commentsCache", key = "#comment.ADDID")
    public void addComment(Comment comment, int ADDID) {
        commentRepository.addComment(comment, ADDID);
    }

    @Cacheable(value = "commentsCache", key = "#ADDID")
    public List<Comment> findByAd(int ADDID) {
        return commentRepository.findByAd(ADDID);
    }
}


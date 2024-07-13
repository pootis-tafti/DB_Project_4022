package ir.ac.kntu.db_project_backend.controllers;

import ir.ac.kntu.db_project_backend.models.Comment;
import ir.ac.kntu.db_project_backend.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/ad/{addId}")
    public ResponseEntity<Void> addComment(@PathVariable int addId, @RequestBody Comment comment) {
        commentService.addComment(comment, addId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/ad/{addId}")
    public ResponseEntity<List<Comment>> findByAd(@PathVariable int addId) {
        List<Comment> comments = commentService.findByAd(addId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}


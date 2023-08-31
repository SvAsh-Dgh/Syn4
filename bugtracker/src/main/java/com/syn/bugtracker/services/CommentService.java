package com.syn.bugtracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.syn.bugtracker.entities.Comment;
import com.syn.bugtracker.repositories.CommentRepository;
import java.util.Optional;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comment addComment(Comment comment) throws Exception {
        if (comment.getCommentText() == null || comment.getCommentText().isEmpty()) {
            throw new Exception("Comment cannot be empty.");
        }
        return commentRepository.save(comment);
    }

    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findById(id);
    }

    public List<Comment> getCommentsByBugId(Long bugId) {
        return commentRepository.findByBugId(bugId);
    }
}

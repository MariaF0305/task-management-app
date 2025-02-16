package com.nttdata.task_management_app.controllers;

import com.nttdata.task_management_app.domain.Comment;
import com.nttdata.task_management_app.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{taskId}")
    public List<Comment> getCommentsForTask(@PathVariable Long taskId) {
        return commentService.getCommentsForTask(taskId);
    }

    @PostMapping("/{taskId}")
    public Comment addComment(@PathVariable Long taskId, @RequestBody String text) {
        return commentService.addComment(taskId, text);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }

    @PutMapping("/{commentId}")
    public Comment updateComment(@PathVariable Long commentId, @RequestBody String newText) {
        return commentService.updateComment(commentId, newText);
    }
}

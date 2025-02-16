package com.nttdata.task_management_app.service.implementation;

import com.nttdata.task_management_app.domain.Comment;
import com.nttdata.task_management_app.repositories.CommentRepository;
import com.nttdata.task_management_app.repositories.TaskRepository;
import com.nttdata.task_management_app.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImplementation implements CommentService {
    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;

    public CommentServiceImplementation(CommentRepository commentRepository, TaskRepository taskRepository) {
        this.commentRepository = commentRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Comment> getCommentsForTask(Long taskId) {
        return commentRepository.findByTaskId(taskId);
    }

    @Override
    public Comment addComment(Long taskId, String text) {
        return taskRepository.findById(taskId).map(task -> {
            Comment comment = new Comment(text, task);
            return commentRepository.save(comment);
        }).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public Comment updateComment(Long commentId, String newText) {
        return commentRepository.findById(commentId)
                .map(comment -> {
                    comment.setText(newText);
                    return commentRepository.save(comment);
                }).orElseThrow(() -> new RuntimeException("Comment not found"));
    }
}

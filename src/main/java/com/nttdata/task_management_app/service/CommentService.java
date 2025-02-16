package com.nttdata.task_management_app.service;

import com.nttdata.task_management_app.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<Comment> getCommentsForTask(Long taskId);
    Comment addComment(Long taskId, String text);
    void deleteComment(Long commentId);
    Comment updateComment(Long commentId, String text);
}

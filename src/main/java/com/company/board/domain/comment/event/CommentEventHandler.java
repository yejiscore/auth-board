package com.company.board.domain.comment.event;

import com.company.board.domain.comment.entity.CommentEntity;
import com.company.board.domain.comment.repository.CommentRepository;
import com.company.board.domain.post.event.PostDeletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CommentEventHandler {

    private final CommentRepository commentRepository;

    @EventListener
    @Transactional
    public void handle(PostDeletedEvent event) {
        List<CommentEntity> comments = commentRepository.findAllByPostId(event.postId());
        comments.forEach(comment -> comment.delete(event.deletedBy()));
    }
}
package com.company.board.domain.post.event;

import java.util.UUID;

public record PostDeletedEvent(UUID postId, Long deletedBy) {
}
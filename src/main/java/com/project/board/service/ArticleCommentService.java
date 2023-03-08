package com.project.board.service;


import com.project.board.dto.ArticleCommentDto;
import com.project.board.repository.ArticleCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleCommentService {
    private final ArticleCommentRepository articleCommentRepository;

    public List<ArticleCommentDto> searchArticleComment(Long id) {
        return List.of();
    }
}

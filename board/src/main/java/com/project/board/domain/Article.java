package com.project.board.domain;

import com.project.board.domain.base.BaseTimeEntity;

public class Article extends BaseTimeEntity {
    private Long id;
    private String title;
    private String content;
    private String hashtag;
}

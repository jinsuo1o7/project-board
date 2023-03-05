package com.project.board.domain;

import com.project.board.domain.base.BaseTimeEntity;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
//@Table(indexes = )
public class Article extends BaseTimeEntity {
    @Id @GeneratedValue
    private Long id;
    private String title;
    private String content;
    private String hashtag;
}

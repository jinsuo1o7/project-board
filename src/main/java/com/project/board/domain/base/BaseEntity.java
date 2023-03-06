package com.project.board.domain.base;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = {
        @Index(columnList = "createdBy")
})
public class BaseEntity extends BaseTimeEntity {
    @CreatedBy
    private String createdBy;
    @LastModifiedBy
    @Column(length = 100)
    private String modifiedBy;
}

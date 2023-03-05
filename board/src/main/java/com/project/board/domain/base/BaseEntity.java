package com.project.board.domain.base;

import org.slf4j.spi.LocationAwareLogger;

import java.time.LocalDateTime;

public class BaseEntity {
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}

package com.project.board.dto;

import com.project.board.domain.Member;

import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.project.board.domain.Member} entity
 */
public record MemberDto(
        String accountId,
        String accountPw,
        String email,
        String nickname,
        String memo,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String createdBy,
        String modifiedBy
) {
    public static MemberDto of(String accountId, String accountPw, String email, String nickname, String memo) {
        return new MemberDto(accountId, accountPw, email, nickname, memo, null, null, null, null);
    }
    public static MemberDto of(String accountId, String accountPw, String email, String nickname, String memo, LocalDateTime createdAt, LocalDateTime modifiedAt, String createdBy, String modifiedBy) {
        return new MemberDto(accountId, accountPw, email, nickname, memo, createdAt, modifiedAt, createdBy, modifiedBy);
    }

    public static MemberDto toDto(Member entity) {
        return new MemberDto(
                entity.getAccountId(),
                entity.getAccountPw(),
                entity.getEmail(),
                entity.getNickname(),
                entity.getMemo(),
                entity.getCreatedAt(),
                entity.getModifiedAt(),
                entity.getCreatedBy(),
                entity.getModifiedBy()
        );
    }

    public Member toEntity() {
        return Member.of(accountId, accountPw, email, nickname, memo);
    }
}
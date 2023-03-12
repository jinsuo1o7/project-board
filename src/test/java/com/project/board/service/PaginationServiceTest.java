package com.project.board.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;


@DisplayName("biz logic - pagination")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = PaginationService.class)
class PaginationServiceTest {
    private final PaginationService sut;

    public PaginationServiceTest(@Autowired PaginationService paginationService) {
        this.sut = paginationService;
    }

    @DisplayName("현재 페이지 번호와 총 페이지 수를 주면, 페이징 바 리스트를 만듦")
    @MethodSource
    @ParameterizedTest(name = "[{index}] {0}, {1} => {2}")
    void givenCurrentPageNumber_whenCalculating_thenReturnPaginationBarNumber(int currentPageNumber, int totalPages, List<Integer> expected) {
        // Given

        // When
        List<Integer> actual = sut.getPaginationBarNumbers(currentPageNumber, totalPages);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> givenCurrentPageNumber_whenCalculating_thenReturnPaginationBarNumber() {
        return Stream.of(
                arguments(0, 20, List.of(0, 1, 2, 3, 4)),
                arguments(1, 20, List.of(0, 1, 2, 3, 4)),
                arguments(2, 20, List.of(0, 1, 2, 3, 4)),
                arguments(3, 20, List.of(1, 2, 3, 4, 5)),
                arguments(4, 20, List.of(2, 3, 4, 5, 6)),
                arguments(5, 20, List.of(3, 4, 5, 6, 7))
        );
    }

    @DisplayName("현재 설정되어 있는 페이지네이션 바의 길이를 알려줌")
    @Test
    void givenNothing_whenCalling_thenReturnsCurrentBarLength() {
        // Given

        // When
        int barLength = sut.currentBarLength();

        // Then
        assertThat(barLength).isEqualTo(5);
    }
}
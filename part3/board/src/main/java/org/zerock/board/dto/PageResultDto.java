package org.zerock.board.dto;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResultDto<Dto, EN> {

    // Dto 리스트
    private List<Dto> DtoList;
    // 총 페이지 번호
    private int totalPage;
    // 현재 페이지 번호
    private int page;
    // 목록 사이즈
    private int size;
    // 시작 페이지, 끝 페이지 번호
    private int start, end;
    // 이전, 다음 페이지가 있는가?
    private boolean prev, next;
    // 페이지 번호 목록
    private List<Integer> pageList;

    public PageResultDto(Page<EN> result, Function<EN, Dto> fn) {
        DtoList = result.stream().map(fn).collect(Collectors.toList());

        totalPage = result.getTotalPages();

        makePageList(result.getPageable());
    }

    private void makePageList(Pageable pageable) {
        this.page = pageable.getPageNumber() + 1; // 0부터 시작하므로 1 추가
        this.size = pageable.getPageSize();

        // temp and page
        int tempEnd = (int)(Math.ceil(page / 10.0)) * 10;

        start = tempEnd - 9;
        end = Math.min(totalPage, tempEnd);

        prev = start > 1;
        next = totalPage > tempEnd;

        pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
    }

}

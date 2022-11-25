package org.zerock.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.board.dto.BoardDto;
import org.zerock.board.dto.PageRequestDto;
import org.zerock.board.dto.PageResultDto;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Member;
import org.zerock.board.repository.BoardRepository;

import java.util.function.Function;

@Log4j2
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public Long register(BoardDto boardDto) {
        log.info(boardDto);

        Board board = dtoToEntity(boardDto);

        boardRepository.save(board);

        return board.getBno();
    }

    @Override
    public PageResultDto<BoardDto, Object[]> getList(PageRequestDto pageRequestDto) {
        log.info(pageRequestDto);

        Function<Object[], BoardDto> fn = (en -> entityToDto((Board) en[0], (Member) en[1], (Long) en[2]));

        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageRequestDto.getPageable(Sort.by("bno")
                                                                                                      .descending()));

        return new PageResultDto<>(result, fn);
    }

    @Override
    public BoardDto get(Long bno) {
        Object result = boardRepository.getBoardByBno(bno);

        Object[] arr = (Object[]) result;

        return entityToDto((Board) arr[0], (Member) arr[1], (Long) arr[2]);
    }

}

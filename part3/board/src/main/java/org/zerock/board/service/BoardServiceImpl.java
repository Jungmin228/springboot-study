package org.zerock.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.board.dto.BoardDto;
import org.zerock.board.entity.Board;
import org.zerock.board.repository.BoardRepository;

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

}

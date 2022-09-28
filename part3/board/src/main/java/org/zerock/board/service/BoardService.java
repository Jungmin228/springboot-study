package org.zerock.board.service;

import org.zerock.board.dto.BoardDto;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Member;

public interface BoardService {

    Long register(BoardDto boardDto);

    default Board dtoToEntity(BoardDto boardDto) {
        Member member = Member.builder()
                .email(boardDto.getWriterEmail())
                .build();

        Board board = Board.builder()
                .bno(boardDto.getBno())
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .writer(member)
                .build();

        return board;
    }

}

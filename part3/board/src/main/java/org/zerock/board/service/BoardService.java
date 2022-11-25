package org.zerock.board.service;

import org.zerock.board.dto.BoardDto;
import org.zerock.board.dto.PageRequestDto;
import org.zerock.board.dto.PageResultDto;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Member;

public interface BoardService {

    Long register(BoardDto boardDto);

    PageResultDto<BoardDto, Object[]> getList(PageRequestDto pageRequestDto);

    BoardDto get(Long bno);

    void removeWithReplies(Long bno);

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

    default BoardDto entityToDto(Board board, Member member, Long replyCount) {
        BoardDto boardDto = BoardDto.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .writerEmail(member.getEmail())
                .writerName(member.getName())
                .replyCount(replyCount.intValue())
                .build();

        return boardDto;
    }

}

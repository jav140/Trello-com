package uz.jl.springbootfeatures.mappers;

import org.mapstruct.Mapper;
import uz.jl.springbootfeatures.domains.Board;
import uz.jl.springbootfeatures.dtos.board.BoardDto;
import uz.jl.springbootfeatures.dtos.board.BoardGetAllDto;
import uz.jl.springbootfeatures.dtos.board.BoardUpdateDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BoardMapper {

    Board fromBoardDto(BoardDto dto);


    BoardGetAllDto toDto(Board board);

    List<BoardGetAllDto> toListBoardUpdates(List<Board> boards);
}

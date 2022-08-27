package uz.jl.springbootfeatures.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.jl.springbootfeatures.domains.Board;
import uz.jl.springbootfeatures.domains.BoardColumn;
import uz.jl.springbootfeatures.domains.auth.AuthUser;
import uz.jl.springbootfeatures.dtos.column.ColumnCreateDto;
import uz.jl.springbootfeatures.dtos.column.ColumnGetDto;
import uz.jl.springbootfeatures.dtos.column.ColumnUpdateDto;
import uz.jl.springbootfeatures.exceptions.GenericNotFoundException;
import uz.jl.springbootfeatures.mappers.BoardMapper;
import uz.jl.springbootfeatures.mappers.ColumnMapper;
import uz.jl.springbootfeatures.repository.BoardRepository;
import uz.jl.springbootfeatures.repository.ColumnRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class BoardColumnService {

    private final ColumnRepository columnRepository;
    private final BoardRepository boardRepository;
    private final ColumnMapper columnMapper;

    public void createColumn(ColumnCreateDto dto, AuthUser authUser) {
        Board board = boardRepository.findById(dto.getBoard_id()).orElseThrow(() -> new GenericNotFoundException("Board not found", 404));
        BoardColumn boardColumn = columnMapper.fromCreateDto(dto);
        boardColumn.setCreatedBy(authUser.getId());
        boardColumn.setBoard(board);
        boardColumn.setCreatedAt(LocalDateTime.now());
        boardColumn.setDeleted(false);
        columnRepository.save(boardColumn);
    }

    public ColumnGetDto updateColumn(ColumnUpdateDto dto) {

        BoardColumn boardColumn = columnRepository.findById(dto.getId()).orElseThrow(() -> new GenericNotFoundException("Column not found", 404));
        boardColumn.setOrderColumn(dto.getOrder());
        boardColumn.setName(dto.getName());
        return columnMapper.toGetDto(boardColumn);

    }

    public void deleteById(Long id) {
        BoardColumn boardColumn = columnRepository.findById(id).orElseThrow(() -> new GenericNotFoundException("column not found", 404));
        boardColumn.setDeleted(true);
        columnRepository.save(boardColumn);
    }

    public ColumnGetDto getById(Long id) {
        BoardColumn boardColumn = columnRepository.findById(id).orElseThrow(() -> new GenericNotFoundException("column not found", 404));
        return columnMapper.toGetDto(boardColumn);
    }

    public List<ColumnGetDto> getAll(Long board_id) {
       List<BoardColumn> boardColumns = columnRepository.findByBoardId(board_id);
        return columnMapper.toListGetDto(boardColumns);
    }
}

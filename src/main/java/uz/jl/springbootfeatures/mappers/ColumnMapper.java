package uz.jl.springbootfeatures.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.jl.springbootfeatures.domains.BoardColumn;
import uz.jl.springbootfeatures.dtos.column.ColumnCreateDto;
import uz.jl.springbootfeatures.dtos.column.ColumnGetDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ColumnMapper {


    @Mapping(target = "board", ignore = true)
    BoardColumn fromCreateDto(ColumnCreateDto dto);

    ColumnGetDto toGetDto(BoardColumn boardColumn);

    List<ColumnGetDto> toListGetDto(List<BoardColumn> boardColumns);
}

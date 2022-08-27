package uz.jl.springbootfeatures.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import uz.jl.springbootfeatures.config.security.UserDetails;
import uz.jl.springbootfeatures.domains.auth.AuthUser;
import uz.jl.springbootfeatures.dtos.board.BoardGetAllDto;
import uz.jl.springbootfeatures.dtos.board.BoardUpdateDto;
import uz.jl.springbootfeatures.dtos.column.ColumnCreateDto;
import uz.jl.springbootfeatures.dtos.column.ColumnGetDto;
import uz.jl.springbootfeatures.dtos.column.ColumnUpdateDto;
import uz.jl.springbootfeatures.response.ApiResponse;
import uz.jl.springbootfeatures.services.BoardColumnService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BoardColumnController extends ApiController<BoardColumnService> {

    protected BoardColumnController(BoardColumnService service) {
        super(service);
    }

    @PostMapping(PATH + "/column/createColumn")
    public ApiResponse<Void> createColumn(@Valid @RequestBody ColumnCreateDto dto, @AuthenticationPrincipal UserDetails userDetails) {
        AuthUser authUser = userDetails.authUser();
        service.createColumn(dto,authUser);
        return new ApiResponse<>(200);
    }

    @PutMapping(PATH + "/column/updateColumn")
    public ApiResponse<ColumnGetDto> updateColumn(@RequestBody ColumnUpdateDto dto) {
        ColumnGetDto columnGetDto = service.updateColumn(dto);
        return new ApiResponse<>(columnGetDto,200);
    }

    @GetMapping(PATH+"/column/delete/{column_id}")
    public ApiResponse<Void> deleteById(@PathVariable("column_id") Long id) {
        service.deleteById(id);
        return  new ApiResponse<>(200);
    }

    @GetMapping(PATH+"/column/{id}")
    public ApiResponse<ColumnGetDto> get(@PathVariable("id") Long id){
        ColumnGetDto columnGetDto = service.getById(id);
        return  new ApiResponse<>(columnGetDto);
    }

    @GetMapping(PATH+"/column/getAll/{board_id}")
    public ApiResponse<List<ColumnGetDto>> getAll(@PathVariable("board_id") Long board_id) {
        List<ColumnGetDto> columnGetDtos = service.getAll(board_id);
        return  new ApiResponse<>(columnGetDtos);
    }


}

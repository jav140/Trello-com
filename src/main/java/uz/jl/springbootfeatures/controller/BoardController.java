package uz.jl.springbootfeatures.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import uz.jl.springbootfeatures.config.security.UserDetails;
import uz.jl.springbootfeatures.domains.Board;
import uz.jl.springbootfeatures.domains.Workspace;
import uz.jl.springbootfeatures.domains.auth.AuthUser;
import uz.jl.springbootfeatures.dtos.board.BoardDto;
import uz.jl.springbootfeatures.dtos.board.BoardGetAllDto;
import uz.jl.springbootfeatures.dtos.board.BoardUpdateDto;
import uz.jl.springbootfeatures.response.ApiResponse;
import uz.jl.springbootfeatures.services.BoardService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BoardController extends ApiController<BoardService> {

    protected BoardController(BoardService service) {
        super(service);
    }

    @PostMapping(PATH + "/board/createBoard")
    public ApiResponse<Void> createBoard(@RequestBody BoardDto dto, @AuthenticationPrincipal UserDetails userDetails) {
        AuthUser authUser = userDetails.authUser();
        System.out.println(authUser);
        service.createBoard(dto,authUser);
        return new ApiResponse<>(200);
    }

    @GetMapping(PATH+"/board/{id}")
    public ApiResponse<BoardGetAllDto> get(@PathVariable("id") Long id){
        BoardGetAllDto boardGetOne = service.getById(id);
        System.out.println(boardGetOne);
        return  new ApiResponse<>(boardGetOne);
    }

    @GetMapping(PATH+"/board/getAll/{workspace_id}")
    public ApiResponse<List<BoardGetAllDto>> getAll(@PathVariable("workspace_id") Long workspace_id, @AuthenticationPrincipal UserDetails userDetails) {
        List<BoardGetAllDto> boardGetAllDtos = service.getAll(workspace_id, userDetails.authUser().getId());
        return  new ApiResponse<>(boardGetAllDtos);
    }

    @PutMapping(PATH + "/board/updateBoard")
    public ApiResponse<BoardGetAllDto> updateBoard(@RequestBody BoardUpdateDto dto) {
        BoardGetAllDto boardGetAllDto = service.updateBoard(dto);
        return new ApiResponse<>(boardGetAllDto,200);
    }

    @GetMapping(PATH+"/board/delete/{board_id}")
    public ApiResponse<Void> deleteById(@PathVariable("board_id") Long id) {
        service.deleteById(id);
        return  new ApiResponse<>(200);
    }





}

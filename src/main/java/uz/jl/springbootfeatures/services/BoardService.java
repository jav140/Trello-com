package uz.jl.springbootfeatures.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.jl.springbootfeatures.domains.Board;
import uz.jl.springbootfeatures.domains.Workspace;
import uz.jl.springbootfeatures.domains.auth.AuthUser;
import uz.jl.springbootfeatures.dtos.board.BoardDto;
import uz.jl.springbootfeatures.dtos.board.BoardGetAllDto;
import uz.jl.springbootfeatures.dtos.board.BoardUpdateDto;
import uz.jl.springbootfeatures.exceptions.GenericNotFoundException;
import uz.jl.springbootfeatures.mappers.BoardMapper;
import uz.jl.springbootfeatures.repository.BoardRepository;
import uz.jl.springbootfeatures.repository.WorkspaceRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService  {

    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;

    private final WorkspaceRepository workspaceRepository;

    public void createBoard(BoardDto dto, AuthUser authUser) {
        Workspace workspace = workspaceRepository.findById(dto.getId()).orElseThrow();
        Board board = Board.childBuilder()
                .createdAt(LocalDateTime.now())
                .createdBy(authUser.getId())
                .isDeleted(false)
                .title(dto.getTitle())
                .workspace(workspace)
                .authUsers(List.of(authUser))
                .build();
        board.setCreatedBy(authUser.getId());
        System.out.println(board);
        boardRepository.save(board);
    }

    public BoardGetAllDto getById(Long id) {

        Board board = boardRepository.findById(id).orElseThrow(() ->
                new GenericNotFoundException("board not found", 404)
        );

        return boardMapper.toDto(board);
    }
    public BoardGetAllDto updateBoard(BoardUpdateDto dto) {

        Board board = boardRepository.findById(dto.getId()).orElseThrow();
        board.setTitle(dto.getTitle());
        board.setVisibility(dto.getVisibility());
        boardRepository.save(board);
        return boardMapper.toDto(board);
    }

    public void deleteById(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new GenericNotFoundException("Board not found",404));
        board.setDeleted(true);
        boardRepository.save(board);
    }

    public List<BoardGetAllDto> getAll(Long workspace_id, Long userId) {
        List<Board> boards = boardRepository.findByWorkspace(workspace_id, userId);
        return boardMapper.toListBoardUpdates(boards);

    }
}

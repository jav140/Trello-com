package uz.jl.springbootfeatures.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.jl.springbootfeatures.domains.Board;
import uz.jl.springbootfeatures.domains.Workspace;
import uz.jl.springbootfeatures.domains.auth.AuthUser;
import uz.jl.springbootfeatures.dtos.board.BoardDto;
import uz.jl.springbootfeatures.dtos.board.BoardUpdateDto;
import uz.jl.springbootfeatures.exceptions.GenericNotFoundException;
import uz.jl.springbootfeatures.repository.BoardRepository;
import uz.jl.springbootfeatures.repository.ColumnRepository;
import uz.jl.springbootfeatures.repository.WorkspaceRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ColumnService {

    private final ColumnRepository columnRepository ;
    private final BoardRepository boardRepository;

//    public void createBoard(ColumnDto dto, AuthUser authUser) {
//        Workspace workspace = workspaceRepository.findById(dto.getId()).orElseThrow();
//        Board board = Board.childBuilder()
//                .createdAt(LocalDateTime.now())
//                .createdBy(authUser.getId())
//                .isDeleted(false)
//                .title(dto.getTitle())
//                .workspace(workspace)
//                .authUsers(List.of(authUser))
//                .build();
//        board.setCreatedBy(authUser.getId());
//        System.out.println(board);
//        boardRepository.save(board);
//    }
//
//    public Board getById(Long id) {
//        return boardRepository.findById(id).orElseThrow(() ->
//             new GenericNotFoundException("board not found",404)
//        );
//    }
//    public Board updateBoard(BoardUpdateDto dto) {
//
//        Board board = boardRepository.findById(dto.getId()).orElseThrow();
//        board.setTitle(dto.getTitle());
//        board.setVisibility(dto.getVisibility());
//        boardRepository.save(board);
//        return board;
//    }
//
//    public void deleteById(Long id) {
//        Board board = boardRepository.findById(id).orElseThrow();
//        board.setDeleted(true);
//        boardRepository.save(board);
//    }
//
//    public List<Board> getAll(Long id) {
//
//        return boardRepository.findByWorkspace(id);
//    }
}

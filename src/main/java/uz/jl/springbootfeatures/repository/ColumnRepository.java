package uz.jl.springbootfeatures.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.jl.springbootfeatures.domains.BoardColumn;

import java.util.List;

public interface ColumnRepository extends JpaRepository<BoardColumn,Long> {

    @Query(value = "select b from BoardColumn b where b.board.id =:board_id and b.deleted=false")
    List<BoardColumn> findByBoardId(Long board_id);
}

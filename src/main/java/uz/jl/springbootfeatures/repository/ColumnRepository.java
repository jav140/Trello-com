package uz.jl.springbootfeatures.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uz.jl.springbootfeatures.domains.BoardColumn;

import java.util.List;

@Transactional
public interface ColumnRepository extends JpaRepository<BoardColumn,Long> {

    @Query(value = "select b from BoardColumn b where b.board.id =:board_id and b.isDeleted=false")
    List<BoardColumn> findByBoardId(Long board_id);

    @Query(value = "select c from BoardColumn c inner join c.board b inner join b.authUsers u where c.isDeleted=false and b.id =:boardId and u.id=:userId")
    List<BoardColumn> findAllByFalse(@Param(value = "boardId") Long boardId, @Param(value = "userId") Long userId);

    @Modifying
    @Transactional
    @Query("update BoardColumn c set c.orderColumn = c.orderColumn - 1 where c.id > :id")
    void update(Long id);

    @Modifying
    @Transactional
    @Query("update BoardColumn c set c.orderColumn = c.orderColumn + 1 where c.orderColumn >= :movingColumn and c.orderColumn < :orderColumn")
    void moveBack(int orderColumn, int movingColumn);

    @Modifying
    @Transactional
    @Query("update BoardColumn c set c.orderColumn = c.orderColumn - 1 where c.orderColumn <= :movingColumn and c.orderColumn > :orderColumn")
    void moveForward(int orderColumn, int movingColumn);

}

package uz.jl.springbootfeatures.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import uz.jl.springbootfeatures.domains.Board;

import java.util.List;
import java.util.Optional;

@Transactional
public interface BoardRepository extends JpaRepository<Board,Long> {

    @Query(value = "select b from Board b join b.workspace w join w.authUsers u where w.id=:workspaceId and u.id=:userId and b.isDeleted=false")
    List<Board> findByWorkspace(Long workspaceId, Long userId);

    @Query(value = "select b from Board b inner join b.authUsers u where b.id=:id and b.isDeleted=false and  u.id=:userId")
    Optional<Board> getOneBYId(Long id, Long userId);

//    @Transactional
//    @Query(nativeQuery = true,value = "update test_app.board set is_deleted = true where id =:id")
//    void deleteBy(@Param("id") Long id);

}

package uz.jl.springbootfeatures.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uz.jl.springbootfeatures.domains.Workspace;

import java.util.List;

public interface WorkspaceRepository extends JpaRepository<Workspace,Long> {

    @Transactional
    @Query(nativeQuery = true,value = "select * from test_app.workspace " +
            "where id in (select workspace_id from workspace_user where user_id =:id);")
    List<Workspace> findByCreatedBy(@Param("id") Long id);




//    @Transactional
//    @Modifying
//    @Query(value = "update Workspace set Workspace.deleted = true where id =: id")
//    void deleteWorkspace(Long id);
}

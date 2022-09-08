package uz.jl.springbootfeatures.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import uz.jl.springbootfeatures.domains.Task;

import java.util.List;

@Transactional
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(value = "select t from Task t where t.isDeleted=false and t.column.id=:columnId")
    List<Task> findAllByFalse(Long columnId);

}

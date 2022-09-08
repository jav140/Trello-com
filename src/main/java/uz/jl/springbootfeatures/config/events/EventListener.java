package uz.jl.springbootfeatures.config.events;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.jl.springbootfeatures.domains.BoardColumn;
import uz.jl.springbootfeatures.domains.Task;
import uz.jl.springbootfeatures.dtos.column.ColumnMoveDTO;
import uz.jl.springbootfeatures.dtos.task.TaskMoveDTO;
import uz.jl.springbootfeatures.exceptions.GenericNotFoundException;
import uz.jl.springbootfeatures.repository.ColumnRepository;
import uz.jl.springbootfeatures.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EventListener {

    private final TaskRepository taskRepository;
    private final ColumnRepository columnRepository;


    @org.springframework.context.event.EventListener
    public void moveTask(TaskMoveDTO dto) {
        Task task = taskRepository.findById(dto.getTaskId()).orElseThrow(() -> {
            throw new GenericNotFoundException("Task not Found!", 404);
        });

        BoardColumn boardColumn = columnRepository.findById(dto.getColumnID()).orElseThrow(() -> {
            throw new GenericNotFoundException("Column not Found!", 404);
        });

        task.setColumn(boardColumn);
        taskRepository.save(task);

        System.out.println("Task successfully moved!!!!!");
    }

    @org.springframework.context.event.EventListener
    public void copyTask(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> {
            throw new GenericNotFoundException("Task not Found!", 404);
        });

        Task copyTask = Task.childBuilder()
                .name(task.getName())
                .description(task.getDescription())
                .column(task.getColumn())
                .createdBy(task.getCreatedBy())
                .authUsers(new ArrayList<>(task.getAuthUsers()))
                .build();

        taskRepository.save(copyTask);
        System.out.println("Task successfully copied!!!");

    }

    @org.springframework.context.event.EventListener
    public void moveColumn(ColumnMoveDTO dto) {
        BoardColumn column = columnRepository.findById(dto.getColumnId()).orElseThrow(() ->
                new GenericNotFoundException("Column not found!", 404));

        if (column.getOrderColumn() > dto.getMovingOrder()) {
            columnRepository.moveBack(column.getOrderColumn(), dto.getMovingOrder());
        } else {
            columnRepository.moveForward(column.getOrderColumn(), dto.getMovingOrder());
        }
        column.setOrderColumn(dto.getMovingOrder());
        columnRepository.save(column);
    }

}

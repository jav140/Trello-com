package uz.jl.springbootfeatures.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.jl.springbootfeatures.config.events.EventPublisher;
import uz.jl.springbootfeatures.config.events.GenericEvent;
import uz.jl.springbootfeatures.domains.BoardColumn;
import uz.jl.springbootfeatures.domains.Task;
import uz.jl.springbootfeatures.dtos.task.TaskCreateDTO;
import uz.jl.springbootfeatures.dtos.task.TaskGetDTO;
import uz.jl.springbootfeatures.dtos.task.TaskMoveDTO;
import uz.jl.springbootfeatures.dtos.task.TaskUpdateDTO;
import uz.jl.springbootfeatures.exceptions.GenericNotFoundException;
import uz.jl.springbootfeatures.mappers.TaskMapper;
import uz.jl.springbootfeatures.repository.BoardRepository;
import uz.jl.springbootfeatures.repository.ColumnRepository;
import uz.jl.springbootfeatures.repository.TaskRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository repository;
    private final ColumnRepository columnRepository;
    private final BoardRepository boardRepository;
    private final TaskMapper mapper;
    private final EventPublisher publisher;

    public List<TaskGetDTO> getAll(Long columnId, Long userId) {
        BoardColumn column = columnRepository.findById(columnId).orElseThrow(() ->
                new GenericNotFoundException("Task not Found!", 404));
        boardRepository.getOneBYId(column.getBoard().getId(), userId).orElseThrow(() ->
                new GenericNotFoundException("Board not Found!", 404));
//        TaskGetDTO taskGetDTO = new TaskGetDTO();
//        taskGetDTO.setColumn_id();
        return mapper.toDTO(repository.findAllByFalse(columnId));
    }

    public TaskGetDTO get(Long id) {
        return mapper.toDTO(repository.findById(id).orElseThrow(() ->
                new GenericNotFoundException("Task not Found!", 404)));
    }

    public TaskGetDTO update(TaskUpdateDTO dto) {
        Task task = repository.findById(dto.getId()).orElseThrow(() -> {
            throw new GenericNotFoundException("Task not Found!", 404);
        });

        task.setName(dto.getTitle());
        task.setDescription(dto.getDescription());

        return mapper.toDTO(repository.save(task));
    }

    public void create(TaskCreateDTO dto, Long userId) {
        BoardColumn column = columnRepository.findById(dto.getColumnId()).orElseThrow(() -> {
            throw new GenericNotFoundException("Column not Found!", 404);
        });

        Task task = mapper.fromDTO(dto);
        task.setColumn(column);
        task.setCreatedBy(userId);
        task.setName(dto.getName());
        repository.save(task);
    }

    public void delete(Long id) {
        Task task = repository.findById(id).orElseThrow(() -> {
            throw new GenericNotFoundException("Task not Found!", 404);
        });
        task.setDeleted(true);
        repository.save(task);
    }

    public void move(TaskMoveDTO dto) {
       repository.findById(dto.getTaskId()).orElseThrow(() -> {
            throw new GenericNotFoundException("Task not Found!", 404);
        });

        columnRepository.findById(dto.getColumnID()).orElseThrow(() -> {
            throw new GenericNotFoundException("Column not Found!", 404);
        });

        GenericEvent<TaskMoveDTO> genericEvent = new GenericEvent<>(dto, true);
        publisher.publishCustomEvent(genericEvent);
    }

    public void copy(Long taskId) {
        repository.findById(taskId).orElseThrow(() -> {
            throw new GenericNotFoundException("Task not Found!", 404);
        });

        GenericEvent<Long> genericEvent = new GenericEvent<>(taskId, true);
        publisher.publishCustomEvent(genericEvent);
    }
}

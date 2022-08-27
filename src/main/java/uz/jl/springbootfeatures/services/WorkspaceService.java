package uz.jl.springbootfeatures.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.jl.springbootfeatures.domains.Workspace;
import uz.jl.springbootfeatures.domains.auth.AuthUser;
import uz.jl.springbootfeatures.dtos.workspace.WorkspaceDto;
import uz.jl.springbootfeatures.dtos.workspace.WorkspaceGetDto;
import uz.jl.springbootfeatures.dtos.workspace.WorkspaceUpdateDto;
import uz.jl.springbootfeatures.exceptions.GenericNotFoundException;
import uz.jl.springbootfeatures.mappers.WorkspaceMapper;
import uz.jl.springbootfeatures.repository.WorkspaceRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkspaceService {

    private final WorkspaceRepository workspaceRepository;
    private final WorkspaceMapper workspaceMapper;

    public void createWorkspace(WorkspaceDto dto, AuthUser authUser) {
        List<AuthUser> members = new ArrayList<>();
        members.add(authUser);
        Workspace workspace = workspaceMapper.fromCreateDto(dto);
        workspace.setCreatedAt(LocalDateTime.now());
        workspace.setCreatedBy(authUser.getId());
        workspace.setDeleted(false);
        workspace.setAuthUsers(members);
        workspace.setWorkspaceStatus(Workspace.WorkspaceStatus.FREE_TRIAL);
        workspaceRepository.save(workspace);
    }

    public WorkspaceGetDto getById(Long id) {
        Workspace workspace = workspaceRepository.findById(id).orElseThrow();
        return workspaceMapper.toGetOne(workspace);
    }

    public WorkspaceGetDto updateWorkspace(WorkspaceUpdateDto dto) {
        Workspace workspace = workspaceRepository.findById(dto.getId()).orElseThrow();
        workspace.setName(dto.getName());
        workspace.setType(dto.getType());
        workspace.setDescription(dto.getDescription());
        workspaceRepository.save(workspace);
        return workspaceMapper.toGetOne(workspace);

    }

    public List<WorkspaceGetDto> getAllWorkSpaces(Long id) {
        List<Workspace> workspaces = workspaceRepository.findByCreatedBy(id);
        return workspaceMapper.toGetDto(workspaces);
    }

    public void deleteById(Long id, Long userId) {
        Workspace workspace = workspaceRepository.findById(id).orElseThrow(() ->
                new GenericNotFoundException("Workspace not found", 404)
        );
        if(!workspace.isDeleted() && workspace.getCreatedBy().equals(userId)){
            workspace.setDeleted(true);
            workspaceRepository.save(workspace);
        } else {
            throw new GenericNotFoundException("User not matched",404);
        }

    }
}

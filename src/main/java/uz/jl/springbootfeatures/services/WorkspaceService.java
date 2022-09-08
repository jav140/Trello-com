package uz.jl.springbootfeatures.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.jl.springbootfeatures.domains.Workspace;
import uz.jl.springbootfeatures.domains.auth.AuthUser;
import uz.jl.springbootfeatures.dtos.workspace.*;
import uz.jl.springbootfeatures.exceptions.GenericNotFoundException;
import uz.jl.springbootfeatures.logging.ExampleBot;
import uz.jl.springbootfeatures.mappers.WorkspaceMapper;
import uz.jl.springbootfeatures.repository.AuthUserRepository;
import uz.jl.springbootfeatures.repository.WorkspaceRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class WorkspaceService {

    private final WorkspaceRepository workspaceRepository;

    private final AuthUserRepository authUserRepository;
    private final WorkspaceMapper workspaceMapper;
    private String info;


    private final ExampleBot exampleBot;

    public void createWorkspace(WorkspaceDto dto, AuthUser authUser) {
        info = "create workspace method is called";
        log.info(info);
        exampleBot.sendInfo(info);
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
        info = "Getting One workspace by id : {} ";
        log.info(info, id);
        exampleBot.sendInfo(info+id);
        Workspace workspace = workspaceRepository.findById(id).orElseThrow();
        return workspaceMapper.toGetOne(workspace);
    }

    public WorkspaceGetDto updateWorkspace(WorkspaceUpdateDto dto) {
         info = "updateWorkspace method is called";
        log.info(info);
        exampleBot.sendInfo(info);
        Workspace workspace = workspaceRepository.findById(dto.getId()).orElseThrow();
        workspace.setName(dto.getName());
        workspace.setType(dto.getType());
        workspace.setDescription(dto.getDescription());
        workspaceRepository.save(workspace);
        return workspaceMapper.toGetOne(workspace);

    }

    public List<WorkspaceProjection> getAllWorkSpaces(Long id) {
        info = "Getting all workspaces by id : {} ";
        log.info(info, id);
        exampleBot.sendInfo(info+id);
//        List<Workspace> workspaces = workspaceRepository.findByCreatedBy(id);
        return workspaceRepository.toGetDto(id);
//        return workspaceMapper.toGetDto(workspaces);
    }

    public void deleteById(Long id, Long userId) {
        info = "deleting process by id : {} ";
        log.info(info, id);
        exampleBot.sendInfo(info);
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

//    public void inviteMembers(InviteWorkspaceMembersDto dto) {
//        Workspace workspace = workspaceRepository.findById(dto.getWorkspaceId()).orElseThrow(() -> new GenericNotFoundException("workspace not found", 404));
//        AuthUser authUser = authUserRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new GenericNotFoundException("user not found", 404));
//        List<AuthUser> authUsers = workspace.getAuthUsers();
//        authUsers.add(authUser);
//        workspace.setAuthUsers(authUsers);
//        workspaceRepository.save(workspace);
//    }
}

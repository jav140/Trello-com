package uz.jl.springbootfeatures.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import uz.jl.springbootfeatures.config.security.UserDetails;
import uz.jl.springbootfeatures.domains.Workspace;
import uz.jl.springbootfeatures.domains.auth.AuthUser;
import uz.jl.springbootfeatures.dtos.workspace.WorkspaceDto;
import uz.jl.springbootfeatures.dtos.workspace.WorkspaceGetDto;
import uz.jl.springbootfeatures.dtos.workspace.WorkspaceUpdateDto;
import uz.jl.springbootfeatures.response.ApiResponse;
import uz.jl.springbootfeatures.services.WorkspaceService;


import javax.validation.Valid;
import java.util.List;

@RestController
public class WorkspaceController extends ApiController<WorkspaceService>{

    protected WorkspaceController(WorkspaceService service) {
        super(service);
    }

//    @GetMapping(PATH+"/workspace/getAll")
//    public ApiResponse<List<Workspace>> getAll(@AuthenticationPrincipal UserDetails userDetails) {
//        List<Workspace> workspaces = service.getAll(userDetails.authUser().getId());
//        return  new ApiResponse<>(workspaces);
//    }

    @GetMapping(PATH + "/workspace/getAllNow")
    public ApiResponse<List<WorkspaceGetDto>> getAllNow(@AuthenticationPrincipal UserDetails userDetails) {
        List<WorkspaceGetDto> allWorkSpaces = service.getAllWorkSpaces(userDetails.authUser().getId());
        return new ApiResponse<>(allWorkSpaces);
    }

    @GetMapping(PATH+"/workspace/{id}")
    public ApiResponse<WorkspaceGetDto> get(@PathVariable("id") Long id) {
        WorkspaceGetDto workspaceGetDto = service.getById(id);
        return  new ApiResponse<>(workspaceGetDto);
    }

    @GetMapping(PATH+"/workspace/delete/{id}")
    public ApiResponse<Void> deleteById(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        service.deleteById(id, userDetails.authUser().getId());
        return  new ApiResponse<>(200);
    }

    @PostMapping(PATH + "/workspace/createWorkspace")
    public ApiResponse<Void> createWorkspace(@Valid @RequestBody WorkspaceDto dto, @AuthenticationPrincipal UserDetails userDetails) {
        AuthUser authUser = userDetails.authUser();

        System.out.println(authUser);

        service.createWorkspace(dto,authUser);

        return new ApiResponse<>(200);

    }

    @PutMapping(PATH + "/workspace/updateWorkspace")
    public ApiResponse<WorkspaceGetDto> updateWorkspace(@RequestBody WorkspaceUpdateDto dto) {
        WorkspaceGetDto workspaceGetDto = service.updateWorkspace(dto);
        return new ApiResponse<>(workspaceGetDto,200);

    }

}

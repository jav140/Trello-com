package uz.jl.springbootfeatures.mappers;


import org.mapstruct.Mapper;
import uz.jl.springbootfeatures.domains.Workspace;
import uz.jl.springbootfeatures.dtos.workspace.WorkspaceDto;
import uz.jl.springbootfeatures.dtos.workspace.WorkspaceGetDto;
import uz.jl.springbootfeatures.dtos.workspace.WorkspaceUpdateDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WorkspaceMapper {

    Workspace fromCreateDto(WorkspaceDto dto);

    List<WorkspaceUpdateDto> toDto(List<Workspace> workspaces);


    List<WorkspaceGetDto> toGetDto(List<Workspace> workspaces);

    WorkspaceGetDto toGetOne(Workspace workspace);
}

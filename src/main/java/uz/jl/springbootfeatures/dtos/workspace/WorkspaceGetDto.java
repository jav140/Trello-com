package uz.jl.springbootfeatures.dtos.workspace;

import lombok.*;
import uz.jl.springbootfeatures.domains.Workspace;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkspaceGetDto {
    private Long id;
    private String name;
    private Workspace.Type type;
    private String description;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Workspace.WorkspaceStatus status = Workspace.WorkspaceStatus.FREE_TRIAL;
    private boolean deleted;
}

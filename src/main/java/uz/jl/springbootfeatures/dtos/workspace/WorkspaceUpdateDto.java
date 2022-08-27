package uz.jl.springbootfeatures.dtos.workspace;

import lombok.*;
import uz.jl.springbootfeatures.domains.Workspace;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkspaceUpdateDto {

    private Long id;
    private String name;
    private Workspace.Type type;
    private String description;

}

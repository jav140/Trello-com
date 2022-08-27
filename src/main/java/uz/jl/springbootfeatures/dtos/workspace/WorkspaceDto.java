package uz.jl.springbootfeatures.dtos.workspace;


import lombok.*;
import uz.jl.springbootfeatures.domains.Workspace;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkspaceDto {

    @NotBlank(message = "workspace name can not be blank")
    private String name;
    @NotBlank(message = "workspace type can not be blank")
    private String type;
    private String description;


}

package uz.jl.springbootfeatures.dtos.workspace;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InviteWorkspaceMembersDto {

    private Long workspaceId;

    private String email;

}

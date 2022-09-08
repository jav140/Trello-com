package uz.jl.springbootfeatures.dtos.board;

import lombok.*;
import uz.jl.springbootfeatures.domains.Board;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardChangeVisibilityDto {
    private Long boardId;
    private String visibility;
}

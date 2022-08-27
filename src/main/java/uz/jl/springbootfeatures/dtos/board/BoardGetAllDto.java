package uz.jl.springbootfeatures.dtos.board;

import lombok.*;
import uz.jl.springbootfeatures.domains.Board;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardGetAllDto {
    private Long id;
    private String title;
    private Board.Visibility visibility;
}

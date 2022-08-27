package uz.jl.springbootfeatures.dtos.board;

import lombok.*;
import uz.jl.springbootfeatures.domains.Board;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardUpdateDto {

    private Long id;
    private String title;
    private Board.Visibility visibility;

}

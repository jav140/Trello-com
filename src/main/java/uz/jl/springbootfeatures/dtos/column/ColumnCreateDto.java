package uz.jl.springbootfeatures.dtos.column;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ColumnCreateDto {

    private Long board_id;

    private String name;

    private int orderColumn;

}

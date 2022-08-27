package uz.jl.springbootfeatures.dtos.column;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ColumnCreateDto {

    @NotBlank(message = "board id cannot be blank")
    private Long board_id;

    @NotBlank(message = "column name cannot be blank")
    private String name;

    @NotBlank(message = "column order cannot be blank")
    private int order;

}

package uz.jl.springbootfeatures.dtos.column;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ColumnMoveDTO {
    private Long columnId;
    private int movingOrder;
}

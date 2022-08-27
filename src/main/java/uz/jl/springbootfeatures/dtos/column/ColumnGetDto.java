package uz.jl.springbootfeatures.dtos.column;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ColumnGetDto {

    private Long id;
    private String name;
    private int order;
    private LocalDateTime createdAt;
    private boolean isDeleted;

}
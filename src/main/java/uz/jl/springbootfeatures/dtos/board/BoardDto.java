package uz.jl.springbootfeatures.dtos.board;


import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDto {

   private Long id;
   private String title;


}

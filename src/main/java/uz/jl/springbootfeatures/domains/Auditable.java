package uz.jl.springbootfeatures.domains;

import lombok.*;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public abstract class Auditable {

    protected LocalDateTime createdAt = LocalDateTime.now();
    protected Long createdBy;
    protected boolean isDeleted;

}

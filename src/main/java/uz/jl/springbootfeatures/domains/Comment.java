package uz.jl.springbootfeatures.domains;


import lombok.*;
import uz.jl.springbootfeatures.domains.auth.AuthUser;

import javax.persistence.Column;
import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String body;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Task task;

    @ManyToOne
    private AuthUser authUser;

    @Builder(builderMethodName = "childBuilder")
    public Comment(LocalDateTime createdAt, Long createdBy, boolean isDeleted, String body, Task task, AuthUser authUser) {
        super(createdAt, createdBy, isDeleted);
        this.body = body;
        this.task = task;
        this.authUser = authUser;
    }
}

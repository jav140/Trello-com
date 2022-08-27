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
    @JoinColumn(name = "authUser_id", nullable = false)
    private AuthUser authUser;

    @Builder(builderMethodName = "childBuilder")
    public Comment(LocalDateTime createdAt, Long createdBy, boolean isDeleted, Long id, String body) {
        super(createdAt, createdBy, isDeleted);
        this.id = id;
        this.body = body;
    }
}

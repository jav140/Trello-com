package uz.jl.springbootfeatures.domains;


import lombok.*;
import uz.jl.springbootfeatures.domains.auth.AuthUser;

import javax.persistence.*;
import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "task")
public class Task extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private BoardColumn column;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "task_user",
            joinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "auth_user_id", referencedColumnName = "id")
    )
    private List<AuthUser> authUsers;


    @Builder(builderMethodName = "childBuilder")
    public Task(LocalDateTime createdAt, Long createdBy, boolean isDeleted, Long id, String name, String description) {
        super(createdAt, createdBy, isDeleted);
        this.id = id;
        this.name = name;
        this.description = description;
    }
}

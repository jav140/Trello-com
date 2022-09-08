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
@Table(name = "workspace")
public class Workspace extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private Type type; // education,human resources,IT-management....
    @Column(nullable = true)
    private String description;

    @Enumerated(value = EnumType.STRING)
    @Builder.Default
    private WorkspaceStatus workspaceStatus = WorkspaceStatus.FREE_TRIAL;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(
            name = "workspace_user",
            joinColumns = @JoinColumn(name = "workspace_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    private List<AuthUser> authUsers;

    @Builder(builderMethodName = "childBuilder")
    public Workspace(LocalDateTime createdAt, Long createdBy, boolean isDeleted, Long id, String name, Type type, String description, WorkspaceStatus workspaceStatus) {
        super(createdAt, createdBy, isDeleted);
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.workspaceStatus = workspaceStatus;
    }

    public enum WorkspaceStatus{
        FREE_TRIAL,
        PREMIUM
    }

    public enum Type{
        SMALL_BUSINESS,
        EDUCATION,
        HUMAN_RESOURCES,
        OPERATIONS,
        MARKETING,
        OTHER

    }

//    public List<AuthUser> getAuthUsers() {
//        return authUsers;
//    }
}

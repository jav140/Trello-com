package uz.jl.springbootfeatures.domains;


import lombok.*;
import uz.jl.springbootfeatures.domains.auth.AuthRole;
import uz.jl.springbootfeatures.domains.auth.AuthUser;

import javax.persistence.*;

import java.beans.Visibility;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "board")
@Builder
@ToString
public class Board extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Enumerated(value = EnumType.STRING)
    @Builder.Default
    private Visibility visibility = Visibility.WORKSPACE;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Workspace workspace;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(
            name = "board_member",
            joinColumns = @JoinColumn(name = "auth_user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "board_id", referencedColumnName = "id")
    )
    private Collection<AuthUser> authUsers;
    public enum Visibility{
        PRIVATE,
        WORKSPACE,
        PUBLIC
    }


    @Builder(builderMethodName = "childBuilder")
    public Board(LocalDateTime createdAt, Long createdBy, boolean isDeleted, String title, Visibility visibility, Workspace workspace,Collection<AuthUser> authUsers) {
        super(createdAt, createdBy, isDeleted);
        this.title = title;
        this.visibility = visibility;
        this.workspace = workspace;
        this.authUsers = authUsers;
    }
}

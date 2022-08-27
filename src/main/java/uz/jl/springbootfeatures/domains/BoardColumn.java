package uz.jl.springbootfeatures.domains;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "BoardColumn")
public class BoardColumn extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @javax.persistence.Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Board board;


    @Column(nullable = false, unique = true)
    private int orderColumn;

    @Builder(builderMethodName = "childBuilder")
    public BoardColumn(LocalDateTime createdAt, Long createdBy, boolean isDeleted, Long id, String name) {
        super(createdAt, createdBy, isDeleted);
        this.id = id;
        this.name = name;
    }
}

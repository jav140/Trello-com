package uz.jl.springbootfeatures.dtos.workspace;

import org.postgresql.largeobject.LargeObject;
import uz.jl.springbootfeatures.domains.auth.AuthUser;
import uz.jl.springbootfeatures.dtos.auth.UserProjection;

import java.util.List;

public interface WorkspaceProjection {
    Long getId();
    String getName();
    String getDescription();
//    List<UserProjection> getAuthUsers();

}

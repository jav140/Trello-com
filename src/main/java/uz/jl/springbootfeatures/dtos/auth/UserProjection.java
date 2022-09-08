package uz.jl.springbootfeatures.dtos.auth;

import uz.jl.springbootfeatures.domains.auth.AuthUser;

import java.util.List;

public interface UserProjection {
    Long getId();
    String getUsername();
}

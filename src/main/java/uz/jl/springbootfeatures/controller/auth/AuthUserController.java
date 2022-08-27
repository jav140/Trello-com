package uz.jl.springbootfeatures.controller.auth;

import org.springframework.web.bind.annotation.*;
import uz.jl.springbootfeatures.controller.ApiController;
import uz.jl.springbootfeatures.domains.auth.AuthUser;
import uz.jl.springbootfeatures.dtos.JwtResponse;
import uz.jl.springbootfeatures.dtos.LoginRequest;
import uz.jl.springbootfeatures.dtos.RefreshTokenRequest;
import uz.jl.springbootfeatures.dtos.UserRegisterDTO;
import uz.jl.springbootfeatures.response.ApiResponse;
import uz.jl.springbootfeatures.services.AuthUserService;

import javax.validation.Valid;


/**
 * @author "Isroilov Javohir"
 * @since 19/08/22/10:50 (Friday)
 * spring-boot-features/IntelliJ IDEA
 */

@RestController
public class AuthUserController extends ApiController<AuthUserService> {
    protected AuthUserController(AuthUserService service) {
        super(service);
    }

    @PostMapping(value = PATH + "/auth/login", produces = "application/json")
    public ApiResponse<JwtResponse> login(@RequestBody LoginRequest loginRequest) {
        return new ApiResponse<>(service.login(loginRequest));
    }

    @GetMapping(value = PATH + "/auth/refresh", produces = "application/json")
    public ApiResponse<JwtResponse> login(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return new ApiResponse<>(service.refreshToken(refreshTokenRequest));
    }

    @PostMapping(PATH + "/auth/register")
    public ApiResponse<AuthUser> register(@Valid @RequestBody UserRegisterDTO dto) {
        return new ApiResponse<>(service.register(dto));
    }

    @GetMapping(PATH + "/auth/activate")
    public ApiResponse<Boolean> register(@RequestParam(name = "activation_code") String activationCode) {
        return new ApiResponse<>(service.activateUser(activationCode));
    }

    @GetMapping(PATH + "/auth/me")
    public AuthUser me() {
        return null;
    }
}

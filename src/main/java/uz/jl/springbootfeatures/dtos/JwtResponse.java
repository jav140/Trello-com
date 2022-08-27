package uz.jl.springbootfeatures.dtos;

/**
 * @author "Isroilov Javohir"
 * @since 19/08/22/12:08 (Friday)
 * spring-boot-features/IntelliJ IDEA
 */
public record JwtResponse(
        String accessToken,
        String refreshToken,
        String tokenType) {
}

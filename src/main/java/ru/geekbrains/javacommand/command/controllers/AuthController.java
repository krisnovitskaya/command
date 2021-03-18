package ru.geekbrains.javacommand.command.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.javacommand.command.controllers.facade.AuthControllerApi;
import ru.geekbrains.javacommand.command.dtos.JwtRequest;
import ru.geekbrains.javacommand.command.exceptions.CommandError;
import ru.geekbrains.javacommand.command.services.UserService;

import javax.security.auth.message.AuthException;
import java.util.Date;

@RestController
@RequiredArgsConstructor
public class AuthController
        implements AuthControllerApi {

    @Value("${security.jwt.secret-key}")
    private String secret;

    @Value("${security.jwt.expiration-time}")
    private Integer expiresIn;

    @Value("${security.jwt.token-prefix}")
    private String jwt_prefix;

    private final UserService userDetailsService;
    private final AuthenticationManager authenticationManager;

    @Override
    @PostMapping(value = "${security.authorization-path}", consumes = "application/json")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        String[] claims = userDetailsService
                .loadUserByUsername(authRequest.getUsername()).getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toArray(String[]::new);

        String jwtToken = jwt_prefix + JWT.create()
                .withSubject(authRequest.getUsername())
                .withArrayClaim("role", claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + expiresIn * 3600000))
                .withIssuer("errands_app")
                .sign(Algorithm.HMAC512(secret));
        return ResponseEntity.ok(jwtToken);
    }

    @ExceptionHandler({AuthException.class, BadCredentialsException.class, JWTDecodeException.class})
    public @ResponseBody ResponseEntity<?> authExceptionHandler(Exception e) {
        return new ResponseEntity<>(
                new CommandError(HttpStatus.UNAUTHORIZED.value(), e.getMessage()), HttpStatus.UNAUTHORIZED);
    }
}

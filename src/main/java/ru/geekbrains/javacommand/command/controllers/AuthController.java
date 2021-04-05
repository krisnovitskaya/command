package ru.geekbrains.javacommand.command.controllers;

import com.auth0.jwt.exceptions.JWTDecodeException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.javacommand.command.controllers.facade.AuthControllerApi;
import ru.geekbrains.javacommand.command.dtos.JwtRequest;
import ru.geekbrains.javacommand.command.dtos.JwtResponse;
import ru.geekbrains.javacommand.command.exceptions.CommandError;
import ru.geekbrains.javacommand.command.security.JWTTokenManager;
import ru.geekbrains.javacommand.command.services.UserService;

import javax.security.auth.message.AuthException;

/**
 * @author owpk
 * @see JWTTokenManager
 * @see ru.geekbrains.javacommand.command.security.JWTAuthorizationFilter
 * @see ru.geekbrains.javacommand.command.security.ErrandsAppSecurityConfiguration
 */
@RestController
@RequiredArgsConstructor
public class AuthController
        implements AuthControllerApi {

    private final UserService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JWTTokenManager tokenManager;

    @Override
    @PostMapping(value = "${security.authorization-path}", consumes = "application/json")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        String[] claims = userDetailsService
                .loadUserByUsername(authRequest.getUsername()).getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toArray(String[]::new);

        String jwtToken = tokenManager.createToken(claims, authRequest.getUsername());

        return ResponseEntity.ok(new JwtResponse(jwtToken));
    }

    @ExceptionHandler({AuthException.class, BadCredentialsException.class, JWTDecodeException.class, AuthenticationException.class})
    public @ResponseBody ResponseEntity<?> authExceptionHandler(Exception e) {
        return new ResponseEntity<>(
                new CommandError(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}

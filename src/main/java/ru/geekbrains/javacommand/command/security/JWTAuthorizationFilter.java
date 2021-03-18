package ru.geekbrains.javacommand.command.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.security.auth.message.AuthException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Value("${security.jwt.token-header}")
    private String TOKEN_HEADER;

    @Value("${security.jwt.token-prefix}")
    private String TOKEN_PREFIX;

    @Value("${security.jwt.secret-key}")
    private String SECRET;

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(TOKEN_HEADER);
        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }
        try {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(req, res);
        } catch (BadCredentialsException | JWTDecodeException | IOException | ServletException e) {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getLocalizedMessage());
            log.error("jwt filter error: {}", e.getMessage());
        }
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_HEADER);

        if (token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            DecodedJWT jwt = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, ""));
            String user = jwt.getSubject();
            Map<String, Claim> claims = jwt.getClaims();
            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null,
                        claims.get("role").asList(String.class).stream()
                                .map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
            }
        }
        return null;
    }
}
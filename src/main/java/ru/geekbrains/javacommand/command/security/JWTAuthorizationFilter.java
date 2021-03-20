package ru.geekbrains.javacommand.command.security;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author owpk
 * @author jackwizard88
 */
 @Component
@Slf4j
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Value("${security.jwt.token-header}")
    private String TOKEN_HEADER;

    @Value("${security.jwt.token-prefix}")
    private String TOKEN_PREFIX;

    @Autowired
    private JWTTokenManager tokenManager;

    /**
     * JWT фильтр запросов пользователей
     * {@link JWTAuthorizationFilter#TOKEN_HEADER}
     * {@link JWTAuthorizationFilter#TOKEN_PREFIX}
     * @param req
     * @param res
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
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
        DecodedJWT jwt = tokenManager.decodeAndVerifyRawToken(token);
        if (jwt != null && SecurityContextHolder.getContext().getAuthentication() == null) {
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
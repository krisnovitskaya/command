package ru.geekbrains.javacommand.command.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author owpk
 * @author jackwizard88
 * Создает и верифицирует JWT токен используя библеотеку <a href="https://github.com/auth0/java-jwt>auth0 java-jwt</a>
 * Параметры токена конфигурируются в application.yaml
 * {@link JWTTokenManager#jwt_prefix}   -- тип токена
 * {@link JWTTokenManager#expiresIn}    -- время действия
 * {@link JWTTokenManager#secret}       -- ключ подписи
 * Токен создается по запросу к /auth ендпоинту {@link ru.geekbrains.javacommand.command.controllers.AuthController}
 * Ендпоинт авторизации конфигурируется в application.yaml
 */
@Component
public class JWTTokenManager {

    @Value("${security.jwt.secret-key}")
    private String secret;

    @Value("${security.jwt.expiration-time}")
    private Integer expiresIn;

    @Value("${security.jwt.token-prefix}")
    private String jwt_prefix;

    /**
     * Генерация токена
     * {@link JWTTokenManager#expiresIn} значение используется в часах
     * Токен подписывается с использование алгоритма HmacSHA256
     * @see JWT
     * @param claims
     * @param username
     */
    public String createToken(String[] claims, String username) {
        return JWT.create()
                .withSubject(username)
                .withArrayClaim("role", claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + expiresIn * 3600000))
                .withIssuer("errands_app")
                .sign(Algorithm.HMAC256(secret));
    }

    /**
     * Верификация и парсинг токена
     * @see JWT
     * @param jwtToken
     * @return {@link DecodedJWT}
     */
    public DecodedJWT decodeAndVerifyRawToken(String jwtToken) {
        return JWT.require(Algorithm.HMAC256(secret))
                .build()
                .verify(jwtToken.replace(jwt_prefix, ""));
    }
}

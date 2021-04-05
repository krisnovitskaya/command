package ru.geekbrains.javacommand.command.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author owpk
 * @author jackwizard88
 */
@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class ErrandsAppSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JWTAuthorizationFilter authorizationFilter;

    @Value("${security.authorization-path}")
    private String SIGN_IN_URL;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/api/v1/post/**").hasAnyRole("ADMIN", "POST")
                .antMatchers("/api/v1/employees/**").authenticated()
                .antMatchers("/api/v1/administration/**").hasRole("ADMIN")
                .antMatchers("/api/v1/errands_pending/**").hasAnyRole("ADMIN", "MASTER")
                .antMatchers("/db/**").hasRole("ADMIN")
                .anyRequest().permitAll()
                .and()
                .headers().frameOptions().disable()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
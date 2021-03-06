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
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

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
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);

        http.addFilterBefore(filter, CsrfFilter.class)
                .cors().and()
                .authorizeRequests()
                .antMatchers("/api/v1/employees/**").authenticated()
                .antMatchers("/errands/api/v1/**").authenticated()
                .antMatchers("/api/v1/post/**").hasAnyRole("ADMIN", "POST")
                .antMatchers("/api/v1/administration/**").hasRole("ADMIN")
//                .antMatchers("/api/v1/statistics/**").authenticated()
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
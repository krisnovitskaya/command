package ru.geekbrains.javacommand.command;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.geekbrains.javacommand.command.services.UserService;
import ru.geekbrains.javacommand.command.services.impl.UserServiceImpl;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TestSecuredController.class)
public class SecurityTest {

    @Autowired
    private MockMvc mvc;

    @Value("${security.jwt.secret-key}")
    private String secret;

    @MockBean
    private UserServiceImpl userDetailsService;

    @Test
    public void givenAuthRequestOnPrivateServiceShouldSucceedWith200() throws Exception {
        String token = "Bearer" + JWT.create()
                .withSubject("user")
                .withArrayClaim("role", new String[]{"USER"})
                .sign(Algorithm.HMAC512(secret));

        var details = new org.springframework.security.core.userdetails
                .User("user", "", List.of(new SimpleGrantedAuthority("USER")));
        when(userDetailsService.loadUserByUsername(any())).thenReturn(details);

        mvc.perform(get("/authenticated/hello").contentType(MediaType.APPLICATION_JSON).header("Authorization", token))
                .andExpect(status().isOk());
    }
}
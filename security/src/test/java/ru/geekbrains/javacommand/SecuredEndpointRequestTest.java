package ru.geekbrains.javacommand;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.geekbrains.javacommand.configs.JwtTokenUtil;
import ru.geekbrains.javacommand.controllers.SecuredController;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SecuredEndpointRequestTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @WithMockUser(username = "user", password = "100")
    @Test
    public void givenAuthRequestOnPrivateServiceShouldSucceedWith200() throws Exception {
        UserDetails userDetails = new
        org.springframework.security.core.userdetails.User("user", "100",
                new ArrayList<>(List.of(new SimpleGrantedAuthority("USER"))));
        String token = jwtTokenUtil.generateToken(userDetails);
        mvc.perform(get("/api/v1/authenticated/hello").contentType(MediaType.APPLICATION_JSON).header("Authorization", token))
                .andExpect(status().isOk());
    }
}
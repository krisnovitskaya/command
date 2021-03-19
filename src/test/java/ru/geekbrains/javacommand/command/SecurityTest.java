package ru.geekbrains.javacommand.command;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles({"test"})
public class SecurityTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser(username = "user1", password = "100", roles = "USER")
    public void givenAuthRequestOnPrivateServiceShouldSucceedWith200() throws Exception {
        mvc.perform(get("/api/v1/test"))
                .andExpect(status().isOk());
    }
}
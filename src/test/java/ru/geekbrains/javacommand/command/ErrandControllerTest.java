package ru.geekbrains.javacommand.command;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("default")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class ErrandControllerTest {

  @Autowired private MockMvc mockMvc;

  @Test
  @WithMockUser(username = "admin", roles = "ADMIN")
  public void checkMatters() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.get("/api/v1/errands/matters"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(
            MockMvcResultMatchers.content()
                .string(
                    "[{\"id\":1,\"matter\":\"LOCAL\"},{\"id\":2,\"matter\":\"PRIVATE\"},{\"id\":3,\"matter\":\"LONG\"}]"));
  }

  @Test
  @WithMockUser(username = "admin", roles = "ADMIN")
  public void checkFindById() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.get("/api/v1/errands/findbyid?id=11"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(
            MockMvcResultMatchers.content()
                .string(
                    "{\"id\":11,"
										+ "\"created\":\"2021-03-19T00:00:00+03:00\","
										+ "\"updated\":\"2021-03-19T00:00:00+03:00\","
										+ "\"statusType\":\"REQUESTED\","
										+ "\"dateStart\":\"2021-03-18T00:00:00+03:00\","
										+ "\"dateEnd\":\"2021-03-19T00:00:00+03:00\","
										+ "\"employeeFirstName\":\"Имя5\","
										+ "\"employeeMiddleName\":\"Отчество5\","
										+ "\"employeeLastName\":\"Фамилия5\","
										+ "\"employeePosition\":\"position5\","
										+ "\"employeeUserName\":\"master5\","
										+ "\"departmentTitle\":\"Департамент 5\","
										+ "\"departmentMasterFirstName\":\"Имя5\","
										+ "\"departmentMasterMiddleName\":\"Отчество5\","
										+ "\"departmentMasterLastName\":\"Фамилия5\","
										+ "\"departmentMasterUserName\":\"master5\","
										+ "\"detailMatterType\":\"LOCAL\","
										+ "\"detailPlace\":\"Место4\","
										+ "\"detailPlaceType\":\"place_type4\","
										+ "\"detailComment\":\"Комментарий4\","
										+ "\"detailCreatedByFirstName\":\"Имя5\","
										+ "\"detailCreatedByMiddleName\":\"Отчество5\","
										+ "\"detailCreatedByLastName\":\"Фамилия5\","
										+ "\"detailConfirmedOrRejectedByFirstName\":\"Имя2\","
										+ "\"detailConfirmedOrRejectedByMiddleName\":\"Отчество2\","
										+ "\"detailConfirmedOrRejectedByLastname\":\"Фамилия2\"}"));
  }
}

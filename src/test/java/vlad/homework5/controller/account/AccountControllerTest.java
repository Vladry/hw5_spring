package vlad.homework5.controller.account;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import vlad.homework5.DTO.account.AccountTransferDto;
import vlad.homework5.service.AccountService;
import vlad.homework5.service.CustomerService;
import vlad.homework5.service.dtoMappers.AccountRequestDtoMapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;  //import org.junit.jupiter.api.Test;
// org.junit.Test = JUnit 4, org.junit.jupiter.api.Test = JUnit 5
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = AccountController.class)
@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
@Slf4j
public class AccountControllerTest {

    //    private static final Logger logger = LoggerFactory.getLogger(AccountControllerTest.class);
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AccountService accService;
    @MockBean
    private CustomerService customerService;
    @MockBean
    private AccountRequestDtoMapper accReqDtoMapper;

    @Test
    public void check_putAmount_success() throws Exception {
        AccountTransferDto dto = new AccountTransferDto()
                .setAmount(50.0)
                .setFrom("1").setTo("2");
        ObjectMapper om = new ObjectMapper();
        ObjectWriter ow = om.writer().withDefaultPrettyPrinter();
        String dtoSrt = ow.writeValueAsString(dto);
        when(accService.putAmount(any(String.class), any(Double.class))).thenReturn(true);
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post("/accounts/put-amount")
                        .contentType("application/json").content(dtoSrt))
                .andExpect(status().isOk())
//                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("true"))
                .andReturn();
        String res = result.getResponse().getContentAsString();
        System.out.println("AccountController returned: " + res);

    }

    @Test
    public void check_drawAmount_success() throws Exception {
        AccountTransferDto dto = new AccountTransferDto()
                .setAmount(50.0)
                .setFrom("1").setTo("2");
        String dtoJson = new ObjectMapper().writer().writeValueAsString(dto);
        when(accService.drawAmount(any(String.class), any(Double.class))).thenReturn(true);
        MvcResult res = this.mockMvc.perform(MockMvcRequestBuilders.post("/accounts/draw-amount")
                        .contentType("application/json").content(dtoJson))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("true"))
                .andReturn();
        String resStr = res.getResponse().getContentAsString();
        System.out.println("AccountController.drawAmount returns: " + resStr);
    }

    @Test
    public void check_transferAmount_success() throws Exception{
        AccountTransferDto dto = new AccountTransferDto().setTo("1").setFrom("2").setAmount(50.0);
        String dtoJson = new ObjectMapper().writer().writeValueAsString(dto);
        when(accService.transferAmount(any(String.class), any(String.class), any(Double.class))).thenReturn(true);

        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post("/accounts/transfer-amount")
                        .contentType("application/json")
                        .content(dtoJson))
                .andExpect(status().isOk())
                .andReturn();
        String MockedServerReply = mvcResult.getResponse().getContentAsString();
        System.out.println("Mocked controller.transferAmount() returned: " +MockedServerReply);
    }


}
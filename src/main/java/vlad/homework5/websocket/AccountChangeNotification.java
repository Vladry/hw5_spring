package vlad.homework5.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vlad.homework5.DTO.account.AccountWsRsDto;
import vlad.homework5.domain.Account;
import vlad.homework5.facade.AccountWsFacade;

@Component
@Data
@RequiredArgsConstructor
public class AccountChangeNotification {
    private final AccountWsFacade accWsFacade;
    private final ObjectMapper om;
    private String message;


    private String convertToJson(AccountWsRsDto acc) {
        ObjectWriter ow = om.writer().withDefaultPrettyPrinter();
        try {
            return ow.writeValueAsString(acc);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    public String convertToWebsocketMessage(Account account) {
        AccountWsRsDto accountWsRsDto = accWsFacade.convertToDto(account);
//        return "{\"accUpdateMsg\":"+convertToJson(accountWsRsDto)+"}";
        this.message = convertToJson(accountWsRsDto);
        return this.message;
    }
}

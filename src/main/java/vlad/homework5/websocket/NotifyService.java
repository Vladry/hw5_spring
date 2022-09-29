package vlad.homework5.websocket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import vlad.homework5.domain.Account;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotifyService {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final AccountChangeNotification acn;


public void notifier(Account account) {
    String JsonMsg = acn.convertToWebsocketMessage(account);
    simpMessagingTemplate.convertAndSend("/topic/accounts", JsonMsg);
//    log.info("NotifyService.notifier-> webSocket: \n" + JsonMsg);

}
}

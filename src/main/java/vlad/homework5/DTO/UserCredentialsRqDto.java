package vlad.homework5.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCredentialsRqDto {

    private final String login;
    private final String password;

   public UserCredentialsRqDto(String login, String password) {
        this.login = login;
        this.password = password;
    }
}

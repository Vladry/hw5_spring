package vlad.homework5.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vlad.homework5.DTO.UserCredentialsRqDto;
import vlad.homework5.domain.SysUser;
import vlad.homework5.service.UserService;

@Slf4j
@RestController
public class UserController {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public UserController(PasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @PostMapping("/users/create")
    public SysUser register(
            @RequestBody UserCredentialsRqDto credentials) {
        log.info("in userController.register-> ");
        String login = credentials.getLogin();
        SysUser sysUser = null;
        try {
            sysUser = userService.findSecurityUserByUsername(login);
            if (sysUser == null) {
                String password = credentials.getPassword();
                SysUser user = new SysUser(login, passwordEncoder.encode(password));
                log.info("saving new user: "+ user);
                return userService.save(user);
            } else {
                System.out.println("user already exists");
                throw new RuntimeException("user already exists");
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return sysUser;
        }

    }
/*
    @PostMapping("/users/create2")
    public SysUser register2(
            @RequestParam("login") String login,
            @RequestParam("password") String password) {

        System.out.println("in register2-> ");
        SysUser sysUser = null;
        try {
            sysUser = userService.findSecurityUserByUsername(login);
            if (sysUser == null) {
                SysUser user = new SysUser(login, passwordEncoder.encode(password));

                return userService.save(user);
            } else {
                System.out.println("user already exists");
                throw new RuntimeException("user already exists");
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return sysUser;
        }

    }*/
}

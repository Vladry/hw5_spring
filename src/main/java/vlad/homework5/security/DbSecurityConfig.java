package vlad.homework5.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class DbSecurityConfig {
    //  https://www.thymeleaf.org/doc/articles/springsecurity.html
    //  https://www.baeldung.com/spring-security-login-react
    //  https://ru.stackoverflow.com/questions/1353310/%D0%9F%D0%B5%D1%80%D0%B5%D0%BE%D0%BF%D1%80%D0%B5%D0%B4%D0%B5%D0%BB%D0%B5%D0%BD%D0%B8%D0%B5-%D1%81%D1%82%D1%80%D0%B0%D0%BD%D0%B8%D1%86%D1%8B-%D0%B0%D0%B2%D1%82%D0%BE%D1%80%D0%B8%D0%B7%D0%B0%D1%86%D0%B8%D0%B8-spring-security

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
       // конфигурация для логина по ВЕБ-форме:
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(
                        HttpMethod.GET,
                        "/index*", "/static/**", "/*.js", "/*.json", "/*.ico").permitAll()
                .antMatchers("/", "/register", "/users/create", "/users/create2", "/login", "/logout", "/h2-console").permitAll()
//                .antMatchers().hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
                .and()
//                .httpBasic()   //используем basic аутентификацию
//                .and()
                .formLogin()// указывает, что мы будем логиниться с формы
                .loginPage("/login")// эта форма будет доступна по этому URL-у
                .defaultSuccessUrl("http://localhost:3000/", true)
                .permitAll()// и, туда мы пускаем всех
                .and()
                .logout()    //описывает как будет осуществлять логаут:
                .invalidateHttpSession(true) //при этом, будем уничтожать сессию
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/http://BADNAME@localhost:9000/login") // и, потом будет авто-переход на этот адрес
                .permitAll() //и логаут будет доступен для всех
        ;
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
//        return NoOpPasswordEncoder.getInstance();
    }



}

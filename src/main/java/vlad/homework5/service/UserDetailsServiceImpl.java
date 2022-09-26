package vlad.homework5.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vlad.homework5.domain.SysUser;
import vlad.homework5.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
// этот файл сразу включает режим аутентификации через БД. Тогда AuthenticationProvider автоматически использовать этот UserDetailsService и передавать результат в AuthenticationManager
private final UserRepository userRepository;

@Override
    public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException {
    SysUser sysUser = userRepository.findSecurityUserByUsername(username)
    .orElseThrow(()-> new UsernameNotFoundException("user with username"+username+"not registered"));

    List<SimpleGrantedAuthority> authorities = sysUser.getSysRoles().stream().map((sysRole->
            new SimpleGrantedAuthority(sysRole.getRole()))).collect(Collectors.toList());

    return new User(sysUser.getUsername(), sysUser.getPassword(), sysUser.getEnabled(), sysUser.getAccountNonExpired(), sysUser.getCredentialsNonExpired(), sysUser.getAccountNonLocked(), authorities);
}

}

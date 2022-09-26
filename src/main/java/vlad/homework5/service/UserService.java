package vlad.homework5.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vlad.homework5.domain.SysUser;
import vlad.homework5.exception.DataNotFoundException;
import vlad.homework5.exception.StoringDataException;
import vlad.homework5.repository.UserRepository;
import vlad.homework5.repository.UserRepositoryEm;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepositoryEm userRepositoryEm;
    private final UserRepository userRepository;

//    public SysUser save(SysUser user){
//        return userRepositoryEm.saveEntity(user);
//    }

    public SysUser save(SysUser user) throws StoringDataException {
        System.out.println("in userService.save(SysUser user)-> ");
        SysUser u = null;
        try {
            System.out.println("trying to register a user");
            u = userRepository.save(user);
        } catch (Exception e) {
            System.out.println("in catch Exception: error registering a user!  Check that a StoringDataException must be thrown!");
            String exMessage = "in userService.save(user)";
            throw new StoringDataException(exMessage);
        }
        return u;
    }


    public SysUser findSecurityUserByUsername(String name) throws DataNotFoundException {
        return userRepository.findSecurityUserByUsername(name).orElse(null);
    }
}

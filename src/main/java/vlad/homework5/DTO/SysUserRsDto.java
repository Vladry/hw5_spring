package vlad.homework5.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import vlad.homework5.domain.AbstractEntity;

@Data
@NoArgsConstructor
//@EqualsAndHashCode(callSuper = true)
public class SysUserRsDto extends AbstractEntity {
    private String username;
    private String password;

//    private Set<SysRoles> sysRoles = new HashSet<>(List.of(new SysRoles("USER")));

}

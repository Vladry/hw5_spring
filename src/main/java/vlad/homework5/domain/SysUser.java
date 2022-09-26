package vlad.homework5.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class SysUser extends AbstractEntity {

    public SysUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Column(name = "user_name", length = 36, nullable = false)
    private String username;
    @Column(length = 128, nullable = false)
    private String password;
    @Column(name = "account_non_expired", length = 1, nullable = false)
    private Boolean accountNonExpired = true;
    @Column(name = "account_non_locked", length = 1, nullable = false)
    private Boolean accountNonLocked = true;
    @Column(name = "credentials_non_expired", length = 1, nullable = false)
    private Boolean credentialsNonExpired = true;
    @Column(length = 1, nullable = false)
    private Boolean enabled = true;

    @OneToMany(mappedBy = "sysUser", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<SysRoles> sysRoles = new HashSet<>(List.of(new SysRoles("USER")));

    @Override
    public String toString() {
        return "SysUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", accountNonExpired=" + accountNonExpired +
                ", accountNonLocked=" + accountNonLocked +
                ", credentialsNonExpired=" + credentialsNonExpired +
                ", enabled=" + enabled +
                ", sysRoles=" + sysRoles +
//                ", createdDate=" + createdDate +
//                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}

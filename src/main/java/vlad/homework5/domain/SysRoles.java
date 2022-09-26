package vlad.homework5.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="roles")
public class SysRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private SysUser sysUser;
    private String role;

    public SysRoles(String role){
        this.role=role;
    }

    public String toString(){
        return
                "role: " +
                role
                ;
    }
}

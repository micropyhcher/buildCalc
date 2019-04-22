package by.tms.buildCalc.entity;

import by.tms.buildCalc.enums.UserRoles;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ROLES")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column //(name = "id")
    private Long id;

    @Column //(name = "user_role")
    private UserRoles userRolesEntity;

    @OneToOne(mappedBy = "role", fetch = FetchType.EAGER)
    private User user;

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", userRoles=" + userRolesEntity +
                '}';
    }
}

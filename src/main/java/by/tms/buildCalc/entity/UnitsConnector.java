package by.tms.buildCalc.entity;

import by.tms.buildCalc.entity.unit.Unit1;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "CONNECTORS")
public class UnitsConnector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calc_units_id")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @OneToOne(mappedBy = "unitsConnector", fetch = FetchType.EAGER)
    private User user;

    @OneToMany(mappedBy = "unitsConnector", cascade = CascadeType.ALL)
//    @JoinColumn(name = "calc_units_id", referencedColumnName = "calc_units_id")
    private List<Unit1> unit_1_list = new ArrayList<>();

    @Override
    public String toString() {
        return "UnitsConnector{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                '}';
    }
}

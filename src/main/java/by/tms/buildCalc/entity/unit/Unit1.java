package by.tms.buildCalc.entity.unit;

import by.tms.buildCalc.entity.UnitsConnector;
import by.tms.buildCalc.enums.Unit1Acrions;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "unit1")
public class Unit1 implements Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column //(name = "id_unit1")
    private Long unit1_id;

    @Transient
    private Integer a;

    @Transient
    private Integer b;

    @Transient
    private Unit1Acrions action;

    @Column(name = "rez_exp_unit1")
    private String unit1_rez;


    @ManyToOne //(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "unit_1_list")
    private UnitsConnector unitsConnector;

//    @ManyToOne
////    @JoinColumn(name = "user_id")
//    private User user;

    @Override
    public String toString() {
        return "Unit1{" +
                "unit1_id=" + unit1_id +
                ", a=" + a +
                ", action=" + action +
                ", b=" + b +
                ", unit1_rez=" + unit1_rez +
                '}';
    }

    @Override
    public void unitName() {

    }
}

//package by.tms.buildCalc.entity;
//
//import by.tms.buildCalc.entity.unit.Unit1;
//import lombok.Data;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Data
//@Entity
//@Table(name = "calc_units")
//public class CalcUnits {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "calc_units_id")
//    private Long id;
//
//    @Column(name = "unit_name")
//    private String unitName;
//
//    @OneToOne(mappedBy = "calcUnits", fetch = FetchType.EAGER)
//    private User user;
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "calcUnits", fetch = FetchType.LAZY)
////    @JoinColumn(name = "calc_units_id", referencedColumnName = "calc_units_id")
//    private List<Unit1> unit_1_list;
//
//    @Override
//    public String toString() {
//        return "CalcUnits{" +
//                "id=" + id +
//                ", unitName='" + unitName + '\'' +
//                '}';
//    }
//}

package by.tms.buildCalc.service;

import by.tms.buildCalc.entity.User;
import by.tms.buildCalc.entity.unit.Unit1;
import by.tms.buildCalc.repository.DBUnitsRepository;
import by.tms.buildCalc.repository.DBUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImplUnitService {

    @Autowired
    private DBUserRepository userRepository;

    @Autowired
    private DBUnitsRepository unitRepository;

    public boolean saveUnit(Unit1 unitForSave){
        return unitRepository.saveUnit(unitForSave);
    }

    public boolean saveUserWithUnit(User userWithUnit){
        return userRepository.editUser(userWithUnit);
    }

}

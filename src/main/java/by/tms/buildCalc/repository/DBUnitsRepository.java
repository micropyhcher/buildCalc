package by.tms.buildCalc.repository;

import by.tms.buildCalc.entity.User;
import by.tms.buildCalc.entity.unit.Unit1;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class DBUnitsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public boolean saveUnit(Unit1 unitFromForm) {

        try {

            entityManager.persist(unitFromForm);
            return true; // если ошибок не возникло, значит email пользователя уникален, значит добавление прошло успешно

        } catch (EntityExistsException entityExistsException) {

            return false; // если возникла ошибка, значит пользователь с таким email уже есть, и добавление не произошло

        }

    }

//    @Override // !!!!!!!!!!!!!!!!!!!!!!!!!!!! ХЕРНЯ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public boolean editUnit(User userForEdit) {

        try {

            entityManager.merge(userForEdit);
            return true;

        } catch (IllegalArgumentException illegalArgumentException) {

//            ilegalArgumentException.printStackTrace(); // todo !!!реализовать обработку исключения!!!
            return false;

        }
    }
}

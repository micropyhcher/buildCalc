package by.tms.buildCalc.repository;

import by.tms.buildCalc.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class DBUserRepository implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean saveUser(User userFromForm) {

        try {

            User userFromDB = getUserByEmail(userFromForm.getEmail());
            if (userFromDB.equals(new User())){
                entityManager.persist(userFromForm);
                return true; // если ошибок не возникло, значит email пользователя уникален, значит добавление прошло успешно
            }

            return false;

        } catch (EntityExistsException entityExistsException) {
//        } catch (PersistenceException persistenceException) {
//        } catch (NonUniqueResultException nonUniqueResultException) {

            return false; // если возникла ошибка, значит пользователь с таким email уже есть, и добавление не произошло
//            E.printStackTrace(); // todo !!!реализовать обработку исключения!!!

        }

    }

    @Override
    public User getUser(String email, String pass) {

        try {

            return entityManager.createNamedQuery("User.findUserByEmailAndPass", User.class)
                    .setParameter("email", email)
                    .setParameter("pass", pass)
                    .getSingleResult(); // если ошибок небыло, значит нашел пользователя с такими email и pass и возвращает заполненного найденного пользователя

        } catch (NoResultException noResultException) {

            return new User(); // если возникла ошибка, значит такого пользователя нет в базе, то возвращает пустого пользователя
//            noResultException.printStackTrace(); // todo !!!реализовать обработку исключения!!!

        }

    }

    @Override
    public User getUserById(Long id) {

        try {

            return entityManager.find(User.class, id); // если ошибок небыло, значит в базе есть пользователи и возвращается пользователь тип User.class с заданным id, как у переданной переменной

        } catch (IllegalArgumentException illegalArgumentException) {

            return new User(); // если возникла ошибка, значит такого пользователя с таким id нет в базе, и возвращает пустого пользователя
//            illegalArgumentException.printStackTrace(); // todo !!!реализовать обработку исключения!!!

        }
    }

    @Override
    public User getUserByEmail(String email){

        try {

            return entityManager.createNamedQuery("User.findUserByEmail", User.class)
                    .setParameter("email", email)
                    .getSingleResult(); // если ошибок небыло, значит нашел пользователя с такими email и pass и возвращает заполненного найденного пользователя

        } catch (NoResultException noResultException) {

            return new User(); // если возникла ошибка, значит такого пользователя нет в базе, то возвращает пустого пользователя
//            noResultException.printStackTrace(); // todo !!!реализовать обработку исключения!!!

        }
    }

    @Override
    public List<User> getUserList() {

        try {

            return entityManager.createNamedQuery("User.findAllUsers", User.class)
                    .getResultList(); // если ошибок небыло, значит в базе есть пользователи и возвращается список этих пользователей

        } catch (NoResultException noResultException) {

            return new ArrayList<User>(); // если возникла ошибка, значит база пользователей пуста и возвращается пустой ArrayList
//            noResultException.printStackTrace(); // todo !!!реализовать обработку исключения!!!

        }
    }

    @Override
    public boolean delUser(User userForDelete) {

        try {

            entityManager.remove(entityManager.contains(userForDelete) ? userForDelete : entityManager.merge(userForDelete)); // пользователь был успешно удален из БД
            return true;

        } catch (IllegalArgumentException ilegalArgumentException) { // если возникла ошибка, значит пользователь удален небыл.

            return false;
//            ilegalArgumentException.printStackTrace(); // todo !!!реализовать обработку исключения!!!

        }
    }

    @Override // !!!!!!!!!!!!!!!!!!!!!!!!!!!! ХЕРНЯ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public boolean editUser(User userForEdit) {

        try {

            entityManager.merge(userForEdit);
            return true;

        } catch (IllegalArgumentException illegalArgumentException) {

//            ilegalArgumentException.printStackTrace(); // todo !!!реализовать обработку исключения!!!
            return false;

        }
    }
}

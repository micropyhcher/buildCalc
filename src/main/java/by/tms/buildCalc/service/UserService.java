package by.tms.buildCalc.service;

import by.tms.buildCalc.entity.User;

import java.util.List;

public interface UserService {

	boolean saveUser(User userFromForm);
	User getUser(User userFromForm);
	User getUserById(User userFromForm, Long ID);
	User getUserByEmail(User userFromForm);
	List<User> getUserList();
	boolean delUser(User userForDelete);
	boolean editUser (User userFromSession, User userFromForm);

}

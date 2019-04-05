package by.tms.buildCalc.service;

import by.tms.buildCalc.entity.User;
import by.tms.buildCalc.repository.DBUserRepository;
import by.tms.buildCalc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//@Transactional
public class ImplUserService implements UserService {

	@Autowired
	private DBUserRepository userRepository;

	@Override
	public boolean saveUser(User userFromForm) {
		return userRepository.saveUser(userFromForm);
	}

	@Override
	public User getUser(User userFromForm) {
		return userRepository.getUser(userFromForm.getEmail(), userFromForm.getPass());
	}

	//todo реализовать логику
	@Override
	public User getUserById(User userFromForm, Long idFromForm) {
		Long idToGet;
		if (userFromForm.getId() == null) {
			idToGet = idFromForm;
		} else {
			idToGet = userFromForm.getId();
		}
		return userRepository.getUserById(idToGet);
	}

	@Override
	public User getUserByEmail(User userFromForm) {
		return userRepository.getUserByEmail(userFromForm.getEmail());
	}

	@Override
	public List<User> getUserList() {
		return userRepository.getUserList();
	}

	@Override
	public boolean delUser(User userForDelete) {
		return userRepository.delUser(userForDelete);
	}

	@Override
	public boolean editUser(User userFromSession, User userFromForm) {

		if (!userFromForm.getName().isEmpty()) {
			userFromSession.setName(userFromForm.getName());
		}

		if (userFromForm.getAge() != null) {
			userFromSession.setAge(userFromForm.getAge());
		}

		if (!userFromForm.getEmail().isEmpty()) {
			userFromSession.setEmail(userFromForm.getEmail());
		}

		if (!userFromForm.getPass().isEmpty()) {
			userFromSession.setPass(userFromForm.getPass());
		}

		return userRepository.editUser(userFromSession);
	}
}

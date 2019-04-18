package by.tms.buildCalc.controller;

import by.tms.buildCalc.entity.User;
import by.tms.buildCalc.enums.UserRoles;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static by.tms.buildCalc.entity.Constanta.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(path = "/")
public class IndexController {

//	@Autowired
//	private ImplUserService userService;

	private static Logger logger = Logger.getLogger(IndexController.class.getName());

	@GetMapping
	public ModelAndView indexLobby(ModelAndView modelAndView, HttpServletRequest request) {

//		========================== создание сессии =============================

//		if (request.getSession().isNew()){
////			request.getSession().setAttribute("userEnteredSession", new User()); // ======== старая версия вместо ролей ===========
//			request.getSession().setAttribute("userFromSession_Role", UserRoles.GUEST);
//		}

//		========================== =============== =============================

		modelAndView.setViewName("index");

		UserRoles userFromSession_Role = (UserRoles) request.getSession().getAttribute(USER_FROM_SESSION_ROLE);
		modelAndView.addObject(USER_ENTERED_ROLE,userFromSession_Role);
		if (userFromSession_Role == UserRoles.GUEST){
			modelAndView.addObject("userEntered_Flag",false);
			modelAndView.addObject(USER_ENTERED,"Вы не авторизированы");
			logger.info("Enter guest");
		}else{
			User userFromSession = (User) request.getSession().getAttribute(USER_FROM_SESSION);

			modelAndView.addObject("userEntered_Flag",true);
			String userEntered = userFromSession.getName() + " [" + userFromSession.getEmail() + "]";
			modelAndView.addObject(USER_ENTERED, userEntered);
//			modelAndView.addObject("userFromSession_Role",userFromSession_Role);
			logger.info("Enter " + userFromSession.getRole().getUserRolesEntity() + " : " + userFromSession.getName() + " | Email : " + userFromSession.getEmail() + " | id: " + userFromSession.getId());
		}

		return modelAndView;
	}

}

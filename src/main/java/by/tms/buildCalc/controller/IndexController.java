package by.tms.buildCalc.controller;

import by.tms.buildCalc.entity.Constanta;
import by.tms.buildCalc.entity.Role;
import by.tms.buildCalc.entity.User;
import by.tms.buildCalc.enums.UserRoles;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

		UserRoles userFromSession_Role = (UserRoles) request.getSession().getAttribute(Constanta.userFromSession_role);
		modelAndView.addObject(Constanta.userEntered_role,userFromSession_Role);
		if (userFromSession_Role == UserRoles.GUEST){
			modelAndView.addObject("userEntered_Flag",false);
			modelAndView.addObject(Constanta.userEntered,"Вы не авторизированы");
			logger.info("Enter guest");
		}else{
			User userFromSession = (User) request.getSession().getAttribute(Constanta.userFromSession);

			modelAndView.addObject("userEntered_Flag",true);
			String userEntered = userFromSession.getName() + " [" + userFromSession.getEmail() + "]";
			modelAndView.addObject(Constanta.userEntered, userEntered);
//			modelAndView.addObject("userFromSession_Role",userFromSession_Role);
			logger.info("Enter " + userFromSession.getRole().getUserRolesEntity() + " : " + userFromSession.getName() + " | Email : " + userFromSession.getEmail() + " | id: " + userFromSession.getId());
		}

		return modelAndView;
	}

// =================================== for rev1 ===============================================
//	@GetMapping
//	public String index( ModelAndView modelAndView, HttpServletRequest request) {
//		modelAndView.setViewName("index");
////		========================== создание пустой сессии =============================
//
//		if (request.getSession().isNew()){
//			request.getSession().setAttribute("userEnteredSession", new User());
//		}
//
//	------------------------- проверка на то, залогинлся ли пользоваатель ------------------------------------
////	?????
//		return "index"; // ?????
//	}
//
//	@GetMapping(path = "guest")
//	public ModelAndView indexGuest(ModelAndView modelAndView, HttpServletRequest request) {
//		modelAndView.setViewName("index_guest");
//
//		return modelAndView;
//	}
//
//	@GetMapping(path = "user")
//	public ModelAndView indexUser(ModelAndView modelAndView, HttpServletRequest request) {
//		modelAndView.setViewName("index_user");
//		User userFromSession = (User) request.getSession().getAttribute("userEnteredSession");
//		if (userFromSession == null || userFromSession.equals(new User())){
//			modelAndView.addObject("enteredUserFlag",false);
//			modelAndView.addObject("enteredUser","Вы не авторизированы");
//		}else{
//			modelAndView.addObject("enteredUserFlag",true);
//			modelAndView.addObject("enteredUser", userFromSession.getName() + " [" + userFromSession.getEmail() + "]");
//		}
//		return modelAndView;
//	}
//
//	@GetMapping(path = "admin")
//	public ModelAndView indexAdmin(ModelAndView modelAndView, HttpServletRequest request) {
//		modelAndView.setViewName("index_admin");
//		return modelAndView;
//	}

	// ==============================================================================================

}

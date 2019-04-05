package by.tms.buildCalc.controller.unit;

import by.tms.buildCalc.entity.Constanta;
import by.tms.buildCalc.entity.User;
import by.tms.buildCalc.entity.unit.Unit1;
import by.tms.buildCalc.service.ImplUnitService;
import by.tms.buildCalc.service.ImplUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(path = "/unit1page")
public class Unit1Controller {

    @Autowired
    private ImplUserService userService;

    @Autowired
    private ImplUnitService unitService;

    @GetMapping
    public ModelAndView unit1Lobby(ModelAndView modelAndView, HttpServletRequest request) {

        modelAndView.setViewName("u_unit_1");

        modelAndView.addObject("unit1form", new Unit1());
        return modelAndView;
    }

    @PostMapping
    public ModelAndView unit1Do(@ModelAttribute("unit1form") Unit1 unit1FromForm, ModelAndView modelAndView,
                                HttpServletRequest request){

        User userFromSession = (User) request.getSession().getAttribute(Constanta.userFromSession);

        modelAndView.setViewName("u_unit_1");

        int num1 = unit1FromForm.getA();
        int num2 = unit1FromForm.getB();
        int rez = num1 + num2;
        String rezString = num1 + " + " + num2 + " = " + rez;

//        User userWithNewUnit = userFromSession;

        unit1FromForm.setUnit1_rez(rezString);

        userFromSession.getUnit1().add(unit1FromForm);

        boolean isUnitAdd = unitService.saveUnit(unit1FromForm);

        if(isUnitAdd){
            System.out.println("+++++");
            modelAndView.addObject(Constanta.errorsList,"+++++");
        }
        else{
            modelAndView.addObject(Constanta.errorsList,"-----");
        }
        modelAndView.addObject("unit1list",unit1FromForm);




        return modelAndView;
    }

}

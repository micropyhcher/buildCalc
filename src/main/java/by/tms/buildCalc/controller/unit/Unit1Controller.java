package by.tms.buildCalc.controller.unit;

import by.tms.buildCalc.entity.UnitsConnector;
import by.tms.buildCalc.entity.User;
import by.tms.buildCalc.entity.unit.Unit1;
import by.tms.buildCalc.service.ImplUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static by.tms.buildCalc.entity.Constanta.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(path = "/unit1page")
public class Unit1Controller {

    @Autowired
    private ImplUnitService unitService;

    @GetMapping
    public ModelAndView unit1Lobby(ModelAndView modelAndView, HttpServletRequest request) {

        modelAndView.setViewName("u_unit_1");

//        List<String>
        modelAndView.addObject("unit1form", new Unit1());
        return modelAndView;
    }

    @PostMapping
    public ModelAndView unit1Do(@ModelAttribute("unit1form") Unit1 unit1FromForm, ModelAndView modelAndView,
                                HttpServletRequest request){

        User userFromSession = (User) request.getSession().getAttribute(USER_FROM_SESSION);

        modelAndView.setViewName("u_unit_1");

        int num1 = unit1FromForm.getA();
        int num2 = unit1FromForm.getB();
        int rez = num1 + num2;
        String rezString = num1 + " + " + num2 + " = " + rez;

//        -=-=-=-=-=-=-=-=-=-=-=-=- Unit Connector =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-

        UnitsConnector connector = userFromSession.getUnitsConnector();

        unit1FromForm.setUnit1_rez(rezString);
        unit1FromForm.setUnitsConnector(connector);

//        -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-

        boolean isUnitAdd = unitService.saveUnit(unit1FromForm);
//        boolean isUnitAdd = unitService.saveUserWithUnit(USER_FROM_SESSION);

        if(isUnitAdd){
            System.out.println("unit1isADD +++++");
            modelAndView.addObject(ERRORS_LIST,"unit1isADD +++++");
        }
        else{
            modelAndView.addObject(ERRORS_LIST,"unit1isADD -----");
        }
        modelAndView.addObject("unit1rezult",unit1FromForm);
//        modelAndView.addObject("unit1list",)
        modelAndView.addObject(USER_FROM_SESSION,userFromSession.getEmail());

        return modelAndView;
    }

}

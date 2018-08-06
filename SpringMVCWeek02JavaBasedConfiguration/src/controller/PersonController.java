package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/persons")
public class PersonController {
	//http://localhost:8080/SpringMVCWeek02JavaBasedConfiguration/persons/person.detail
	@RequestMapping("/person.detail")
	public ModelAndView personDetail(ModelAndView mv) {
		mv.setViewName("personPage");
		mv.addObject("message","PersonController#personDetail");
		return mv;
	}
}

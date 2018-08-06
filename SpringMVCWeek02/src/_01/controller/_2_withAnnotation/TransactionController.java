package _01.controller._2_withAnnotation;

//Burada Controller sýnýf tanýmý @Controller annotation'u yardýmý ile yapýlmýstýr.
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

	@RequestMapping(value = "/test1", method = RequestMethod.GET)
	// http://localhost:8080/SpringMVCWeek02/transactions/test1
	public String helloController(Model model) {
		model.addAttribute("message", "String return!");
		return "myTransactionPage";
	}

	@RequestMapping(value = "/test2", method = RequestMethod.GET)
	// http://localhost:8080/SpringMVCWeek02/transactions/test2
	public ModelAndView helloController() {
		ModelAndView mv = new ModelAndView("myTransactionPage");
		mv.addObject("message", "modelAndView return!");
		return mv;
	}
}
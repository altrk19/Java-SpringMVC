package _01.controller._1_withoutAnnotation;
///page1 isteginin çalýþmasý için applicationContext.xml'deki yorum satýrlarýný kaldýr.


//Burada Controller sýnýfý annotation kullanýlmadan ilgili bean'ler kullanýlarak applicationContext.xml içerisinde tanýmlanmýstýr.
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
//Controller sýnýfýmýz ModelAndView'de dönebilir string de dönebilir. geçen hafta öðrenmiþtik.
public class Page1Controller extends AbstractController{

	//http://localhost:8080/SpringMVCWeek02/myproject/page1
	//http://localhost:8080/SpringMVCWeek02/page1
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		ModelAndView mv=new ModelAndView("myPage1");
		mv.addObject("myMessage","HelloPage1Controller");
		return mv;
	}
}
//Annotation ile controller tanýmýný görmüþtük. Birde Controller sýnýflarýný kalýtarak yapabiliyoruz
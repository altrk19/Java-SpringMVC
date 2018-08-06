package _01.controller._1_withoutAnnotation;
///page1 isteginin �al��mas� i�in applicationContext.xml'deki yorum sat�rlar�n� kald�r.


//Burada Controller s�n�f� annotation kullan�lmadan ilgili bean'ler kullan�larak applicationContext.xml i�erisinde tan�mlanm�st�r.
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
//Controller s�n�f�m�z ModelAndView'de d�nebilir string de d�nebilir. ge�en hafta ��renmi�tik.
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
//Annotation ile controller tan�m�n� g�rm��t�k. Birde Controller s�n�flar�n� kal�tarak yapabiliyoruz
package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.HelloService;


@Controller
// sinifimizi @Controller olarak isaretliyoruz!

//@RequestMapping annotationini sinif ve method seviyesinde tanimlanabilir.
@RequestMapping(value = "/hello.controller")
public class SpringHelloController {

	@Autowired
	private HelloService service;
	
	//@RequestMapping("/print.message")                                             //b�ylede tan�mlanabilir.
	@RequestMapping(value="/print.message" , method = RequestMethod.GET)
	// http://localhost:8080/SpringMVCWeek01/hello.controller/print.message
	public String printMessage(ModelMap modelMap) {												//elimizdeki datay� view'de g�sterebilmek i�in dispatch etmem gerekiyor. Servlet-JSP b�l�m�de setAttribute ve RequestDispatch kullanm�st�k burada modelmap kullan�yoruz.2 yontem daha var
		
		//bu da legaldir! metodun i�ine yazmay�p burada da tan�mlayabiliriz.
		//ModelMap modelMap2 = new ModelMap();
		
		String createdMessage = service.createMessage();
		
		modelMap.addAttribute("myMessage", createdMessage);					//gonderdigimiz bu attribute'yi JSP sayfas�nda Expression Language ile g�rebiliriz.
		
		//return "/WEB-INF/view.pages/helloPage.jsp";
		return "helloPage";														//controller bize view ad� d�n�yor bu d�nen degeri view resolver'a veriyoruz
	}
	
	
// prefix ve suffix kullanarak daha kisa sekilde view name i verebiliriz.          �lgili jsp sayfam�z�n locationu verilir.
	
//<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
//	<property name="prefix" value="/WEB-INF/view.pages/"/>
//	<property name="suffix" value=".jsp"/>
//</bean>
}

// 1) istek/request geldi -> FrontController a geliyor (DispatcherServlet)
// gelen istegin hangi Controller tarfindan karsilanacagini DispatcherServlet
// bilemez.
// Bunun icin HandlerMapping'e gider. HandlerMapping hangi conttroller ilgili
// istegi karsilayacak bunun bilgisini donmektedir.
// N tane Controller olabilir.

//webcontent alt�ndaki dosyalata direk eri�im saglanabilir ama web-inf alt�ndaki dosyalara direk eri�im saglanamaz. bu y�zden jsp dosyalar� web-inf alt�nda tutulur.
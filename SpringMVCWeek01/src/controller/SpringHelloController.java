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
	
	//@RequestMapping("/print.message")                                             //böylede tanýmlanabilir.
	@RequestMapping(value="/print.message" , method = RequestMethod.GET)
	// http://localhost:8080/SpringMVCWeek01/hello.controller/print.message
	public String printMessage(ModelMap modelMap) {												//elimizdeki datayý view'de gösterebilmek için dispatch etmem gerekiyor. Servlet-JSP bölümüde setAttribute ve RequestDispatch kullanmýstýk burada modelmap kullanýyoruz.2 yontem daha var
		
		//bu da legaldir! metodun içine yazmayýp burada da tanýmlayabiliriz.
		//ModelMap modelMap2 = new ModelMap();
		
		String createdMessage = service.createMessage();
		
		modelMap.addAttribute("myMessage", createdMessage);					//gonderdigimiz bu attribute'yi JSP sayfasýnda Expression Language ile görebiliriz.
		
		//return "/WEB-INF/view.pages/helloPage.jsp";
		return "helloPage";														//controller bize view adý dönüyor bu dönen degeri view resolver'a veriyoruz
	}
	
	
// prefix ve suffix kullanarak daha kisa sekilde view name i verebiliriz.          Ýlgili jsp sayfamýzýn locationu verilir.
	
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

//webcontent altýndaki dosyalata direk eriþim saglanabilir ama web-inf altýndaki dosyalara direk eriþim saglanamaz. bu yüzden jsp dosyalarý web-inf altýnda tutulur.
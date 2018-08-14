package _01.exception.handling.xmlBased.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import _01.exception.handling.xmlBased.model.DatabaseException;


@Controller
public class SimpleMappingExceptionResolverController {

	@RequestMapping("/numberFormatException")
	//http://localhost:8080/SpringMVCWeek06/numberFormatException
	public ModelAndView throwNumberFormatException() {
		throw new NumberFormatException();
	}

	@RequestMapping("/nullPointerException")
	//http://localhost:8080/SpringMVCWeek06/nullPointerException
	public ModelAndView throwNullPointerException() {
		throw new NullPointerException();
	}

	@RequestMapping("/databaseException")
	//http://localhost:8080/SpringMVCWeek06/databaseException
	String throwDatabaseException() throws Exception {
		throw new DatabaseException("DB-1000", "DB Connection timeout...");
	}
}


//sadece spring-webmvc ve jstl dependency leri eklendi

//web.xml içerisinde  <param-value>/WEB-INF/01.exception.handling.xmlBased.xml</param-value>      yorum satýrýný kaldýr
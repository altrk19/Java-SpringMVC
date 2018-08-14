package _02.exception.handling.annotationBased.controller;

import java.sql.SQLDataException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExceptionHandlingController {

	@RequestMapping("/sqlException")																		
	//http://localhost:8080/SpringMVCWeek06v1/sqlException
	String throwSQLException() throws SQLException {														
		System.out.println("SQLException....");
		throw new SQLException();														//burda sqlexception atýlýyor.
	}

	@RequestMapping("/indexOutOfBoundsException")
	//http://localhost:8080/SpringMVCWeek06/indexOutOfBoundsException
	String throwIndexOutOfBoundsException() {
		System.out.println("IndexOutOfBoundsException....");
		throw new IndexOutOfBoundsException();
	}

	@ExceptionHandler({SQLException.class, SQLDataException.class})							//burda sqlhatasý atýlýnca ne yapýlacagý belirtiliyor. ilgili jsp sayfasýna yönlendiriliyor
	public String handleSQLException() {
		return "_02.exception.handling.annotationBased.view/sqlException";
	}

	@ExceptionHandler({IndexOutOfBoundsException.class})
	public ModelAndView handleIndexOutOfBoundsException(HttpServletRequest req, Exception exception) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("exception", exception);
		modelAndView.setViewName("_02.exception.handling.annotationBased.view/indexOutOfBoundsException");
		return modelAndView;
	}
}


//  <!--    <param-value>/WEB-INF/_02.exception.handling.annotationBased.xml</param-value>  -->          web.xml içerisinde yorum satýrýný kaldýr
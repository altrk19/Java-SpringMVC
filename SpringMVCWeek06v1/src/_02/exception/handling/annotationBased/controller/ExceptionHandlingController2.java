package _02.exception.handling.annotationBased.controller;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExceptionHandlingController2 {

	@RequestMapping("/sqlException2")
	//http://localhost:8080/SpringMVCWeek06/sqlException2
	String throwSQLException() throws SQLException {
		System.out.println("SQLException 2....");
		throw new SQLException();
	}

	@RequestMapping("/indexOutOfBoundsException2")
	//http://localhost:8080/SpringMVCWeek06/indexOutOfBoundsException2
	String throwIndexOutOfBoundsException() {
		System.out.println("IndexOutOfBoundsException 2....");
		throw new IndexOutOfBoundsException();
	}

	
	//exceptionhandle'lar tanýmlanmadýgý için bu istekler ilgili jsp sayfasýna yönlendirilmiyor ve son kullanýcýya hata sayfasýný gösteriyor. Bu istenmeyen bir durum.
}

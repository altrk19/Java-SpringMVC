package _02.exception.handling.annotationBased.controller;


import java.sql.SQLDataException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandlingController {

	@ExceptionHandler({SQLException.class, SQLDataException.class})
	public String handleGenericSQLException() {
		System.out.println("handleGenericSQLException...");
		return "_02.exception.handling.annotationBased.view/genericSqlException";
	}

	@ExceptionHandler({IndexOutOfBoundsException.class})
	public ModelAndView handleGenericIndexOutOfBoundsException(HttpServletRequest req, Exception exception) {
		System.out.println("handleGenericIndexOutOfBoundsException...");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("exception", exception);
		modelAndView.setViewName("_02.exception.handling.annotationBased.view/genericIndexOutOfBoundsException");
		return modelAndView;
	}
	
	
	//ExceptionHandlingController2 sýnýfýnda  exceptionHanler tanýmlanmadýgý için /sqlException2 isteginde jsp sayfasýna yönlendiremiyorduk. Fakat @ControllerAdvice 
	//kullandýgýmýz zaman ilgili exceptionlarý olusturan tüm sýnýflar override ediliyor ve ilgili sayfaya yönlendiriliyor.
	//yani her controlelr için ayrý ayrý exception handler tanýmlamamýza gerek yok.
	
	
	// Eger SimpleMappingExceptionResolver da kullanilmissa @ExceptionHandler
	// oncelikli olacaktir.
	// SimpleMappingExceptionResolver icin NullPointerException hatasi sonucu donecek
	// jsp dosyasini belirtmistik.
	// Ayni testi tekrar yaptigimizda genericNpe.jsp calisacaktir.
	//	 @ExceptionHandler({NullPointerException.class})
	//	 public String handleNullPointerException() {
	//		 return "_02.exception.handling.annotationBased.view/genericNpe";
	//	 }

}

package configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class RegisterSpring implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		AnnotationConfigWebApplicationContext webApplicationContext =new AnnotationConfigWebApplicationContext();
		webApplicationContext.register(BeanConfiguration.class);
		
		//aþaðýdaki kodlar servlet'lerin özellikleri
		DispatcherServlet dispatcherServlet=new DispatcherServlet(webApplicationContext);
		ServletRegistration.Dynamic dynamic=servletContext.addServlet("dispatcher", dispatcherServlet);
		dynamic.addMapping("/");
		dynamic.setLoadOnStartup(1);
		
		
	}

	
}

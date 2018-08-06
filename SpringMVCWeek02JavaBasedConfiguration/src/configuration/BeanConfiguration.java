package configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import controller.PersonController;

@Configuration
@ComponentScan(basePackages="controller")                  //16. satýrdaki metodu yorm satýrý haline getirebiliriz bunun sayesinde
@EnableWebMvc                               //mvc annotation driven'a karsýlýk gelir
public class BeanConfiguration {
	/*
	@Bean
	public PersonController createPersonController() {
		return new PersonController();
	}
	*/
	
	
	@Bean
	public ViewResolver createViewResolver() {       //iki yöntemle view dönebiliriz. 1)string 2) kendimiz viewResolver objesi olusturup donebiliriz
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view.pages/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
}

//<bean
//class="org.springframework.web.servlet.view.InternalResourceViewResolver">
//<property name="prefix" value="/WEB-INF/view.pages/"/>
//<property name="suffix" value=".jsp"/>
//</bean>
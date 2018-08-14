package _02.jaxb.oxm.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import _02.jaxb.oxm.model.Person;
import _02.jaxb.oxm.model.PersonList;


@Controller
public class JAXBPersonController {

	//
	// JAXB -> java architecutre for xml binding                java objelerini xml'e dönüþtürebiliyoruz
	// xml -> extensible markup language

	@RequestMapping("/getPersonXML")
	@ResponseBody
	// http://localhost:8080/SpringMVCWeek06v2/getPersonXML
	public Person getPersonXML() {
		return new Person("1", "Ali", "Bey", "1994");
	}

	@RequestMapping("/getPersonListXML")
	@ResponseBody
	// http://localhost:8080/SpringMVCWeek06v2/getPersonListXML
	public PersonList getPersonListXML() {
		// prepare data
		Person person1 = new Person("1", "Umut", "Can", "2000");
		Person person2 = new Person("2", "James", "Gosling", "1955");
		Person person3 = new Person("3", "Joshua", "Bloch", "1961");

		List<Person> persons = new ArrayList<Person>();
		persons.add(person1);
		persons.add(person2);
		persons.add(person3);

		PersonList personList = new PersonList(persons);

		return personList;
	}
	
	//eklenen dependecyler
	//spring-oxm ve jaxb-api jaxb-impl  jaxb-core
	
	
	
	// <param-value>/WEB-INF/_02.jaxb.xml</param-value>      yprum satýrýný kaldýr
	
	
	// geriye List<Person> donmeye calisirsak!
	// // HTTP Status 500 - Request processing failed; nested exception is
	// java.lang.IllegalArgumentException: No converter found for return value
	// of type: class java.util.ArrayList
}
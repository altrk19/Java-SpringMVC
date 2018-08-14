package _01.json.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import _01.json.model.Person;


@Controller
public class JSONController {

	@RequestMapping("/getPerson")
	@ResponseBody
	// http://localhost:8080/SpringMVCWeek06v2/getPerson
	public Person getPerson() {
		return new Person("1", "Ali", "Bey", "1994");
	}

	@RequestMapping("/getPersonList")
	@ResponseBody
	// http://localhost:8080/SpringMVCWeek06v2/getPersonList
	public List<Person> getPersonList() {
		// prepare data
		Person person1 = new Person("1", "Ali", "Beyy", "1994");
		Person person2 = new Person("2", "James", "Gosling", "1955");
		Person person3 = new Person("3", "Joshua", "Bloch", "1961");

		List<Person> personList = new ArrayList<Person>();
		personList.add(person1);
		personList.add(person2);
		personList.add(person3);

		return personList;
	}

}
//eklenen dependecy : jackson-databind

// <param-value>/WEB-INF/_01.json.xml</param-value>     yorum satýrý kaldýrýldý
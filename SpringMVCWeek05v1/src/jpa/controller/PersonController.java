package jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jpa.domain.Person;
import jpa.service.PersonService;

@Controller
public class PersonController {

	@Autowired
	private PersonService personServiceImpl;

	@RequestMapping(value = "/person.list", method = RequestMethod.GET)
	// http://localhost:8080/.../person.list
	public String getPersonList(Model model) {

		System.out.println("...getPersonList...");

		List<Person> personList = personServiceImpl.getAllPersons();
		model.addAttribute("newPerson", new Person());                              //yeni person objesi olusturma nedeni : input alanlarýna girilen bilgiler add yapýlýrken burada olusturulan person objesi ile kayýt ediliyor.   
		model.addAttribute("allPersons", personList);								//liste dispatch ediliyor ve tüm person'lar ekrana yazdýrýlýyor

		return "jpa.view/personList";
	}

	@RequestMapping(value = "/person.add", method = RequestMethod.POST)
	public String addPerson(@ModelAttribute("newPerson") Person person, Model model) {
		// ÖNEMLÝ //@modelattribute kullanma amacýmýz http://localhost:8080/SpringMVCWeek05/person/add  sayfasýnda girilen degelerin productToAdded objesine dolmasýdýr.
		//ÖNEMLÝ// <form:form  modelAttribute="newProduct" class="form-horizontal" method="post">          @ModelAttribute("newProduct")     newProduct isimleri ayný olmak zorundadýr.
		System.out.println("...addPerson...");

		personServiceImpl.insertPerson(person);

		return "redirect:/person.list";
	}

	@RequestMapping(value = "/person.delete{personId}", method = RequestMethod.GET)
	public String deletePerson(@RequestParam("personId") int id) {

		System.out.println("...deletePerson...");

		personServiceImpl.deletePerson(id);

		return "redirect:/person.list";
	}

	@RequestMapping(value = "/person.edit{personId}", method = RequestMethod.GET)
	public String editPerson(@RequestParam("personId") int id, Model model) {

		System.out.println("...editPerson ...");

		model.addAttribute("newPerson", personServiceImpl.getPersonById(id));               //seçilen person'un bilgilerini newPerson stringi ile jsp sayfasýna gönderdik ve jsp sayfasýnda bilgileri ekrana yazdýrdýk
		model.addAttribute("allPersons", personServiceImpl.getAllPersons());			    //30. satýrdaki kod ile ayný	
		
		model.addAttribute("update", "update");                                              //eðer edit butonuna basýlýrsa add butonunun adý degisir update olur ve linki degisir. bunu jsp sayfasýnda java kodu ile ayarladýk.
																							//jsp sayfasýnda <c:if test="${ !empty update}">  þeklinde test edilir.
		return "jpa.view/personList";
	}

	@RequestMapping(value = "/person.update", method = RequestMethod.POST)
	public String updatePerson(@ModelAttribute("newPerson") Person person, Model model) {

		System.out.println("...update person...");

		personServiceImpl.updatePerson(person);

		return "redirect:/person.list";
	}

}

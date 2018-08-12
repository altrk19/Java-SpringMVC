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
		model.addAttribute("newPerson", new Person());                              //yeni person objesi olusturma nedeni : input alanlar�na girilen bilgiler add yap�l�rken burada olusturulan person objesi ile kay�t ediliyor.   
		model.addAttribute("allPersons", personList);								//liste dispatch ediliyor ve t�m person'lar ekrana yazd�r�l�yor

		return "jpa.view/personList";
	}

	@RequestMapping(value = "/person.add", method = RequestMethod.POST)
	public String addPerson(@ModelAttribute("newPerson") Person person, Model model) {
		// �NEML� //@modelattribute kullanma amac�m�z http://localhost:8080/SpringMVCWeek05/person/add  sayfas�nda girilen degelerin productToAdded objesine dolmas�d�r.
		//�NEML�// <form:form  modelAttribute="newProduct" class="form-horizontal" method="post">          @ModelAttribute("newProduct")     newProduct isimleri ayn� olmak zorundad�r.
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

		model.addAttribute("newPerson", personServiceImpl.getPersonById(id));               //se�ilen person'un bilgilerini newPerson stringi ile jsp sayfas�na g�nderdik ve jsp sayfas�nda bilgileri ekrana yazd�rd�k
		model.addAttribute("allPersons", personServiceImpl.getAllPersons());			    //30. sat�rdaki kod ile ayn�	
		
		model.addAttribute("update", "update");                                              //e�er edit butonuna bas�l�rsa add butonunun ad� degisir update olur ve linki degisir. bunu jsp sayfas�nda java kodu ile ayarlad�k.
																							//jsp sayfas�nda <c:if test="${ !empty update}">  �eklinde test edilir.
		return "jpa.view/personList";
	}

	@RequestMapping(value = "/person.update", method = RequestMethod.POST)
	public String updatePerson(@ModelAttribute("newPerson") Person person, Model model) {

		System.out.println("...update person...");

		personServiceImpl.updatePerson(person);

		return "redirect:/person.list";
	}

}

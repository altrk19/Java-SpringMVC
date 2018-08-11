package controller;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import model.Product;
import service.ProductService;
import validator.ProductValidator;

@Controller
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	//Validation destegi i�in
	@Autowired
	private ProductValidator productValidator;
	
	
	//http://localhost:8080/SpringMVCWeek04/products/              istek ilk �nce default olarak buraya d���yor. ve �r�nler listeleniyor.
	@RequestMapping
	public String defaultPage(Model model) {
		List<Product> products=productService.getAllProducts();
		model.addAttribute("products",products);							//hem view s�n�f�n�n ad�n� d�n�yorum hem de view s�n�f�nda g�sterilecek elemanlar�n listesini d�n�yorum
		return "productList";
	}
	
	
	
	
	
	@RequestMapping("/all")
	public String allProducts(Model model) {
		List<Product> products=productService.getAllProducts();
		model.addAttribute("products",products);							//hem view s�n�f�n�n ad�n� d�n�yorum hem de view s�n�f�nda g�sterilecek elemanlar�n listesini d�n�yorum
		return "productList";
	}
	
	
	
	
	
	
	//productId'ye g�re �r�n getircez. �r�n detaylar�na bakacag�z
	//�r�n detay sayfas� olacak yani
	@RequestMapping("/product")
	public String getProductById(@RequestParam(name="id")String productId,Model model) {
		Product product=productService.getProductById(productId);                 //id bilgisini verip o id'li �r�n� istiyoruz
		model.addAttribute("product",product);
		return "productDetail";
	}
	//productList.jsp sat�r 123
	//href=" <c:url value="/products/product?id=${product.productId}" /> "                   burada kars�lan�r.       http://localhost:8080/SpringMVCWeek04/products/product?id=P1001          //buradaki /product?id=P1001 1001 bilgisi de jsp dosyas�nda objeden �ekildi.
	//id bilgisi sorgudan al�n�r ve ilgili �r�n objesi olusturulup jsp sayfas�nda g�sterilir.
	
	
	
	
	
	@RequestMapping("/category/{categoryName}")              //{categoryName} ne gelirse gelsin istek kar��lan�r ve {categoryName}'e g�re sonuc �retilir.
	public String getProductByCategoryNamed(@PathVariable("categoryName") String categoryName, Model model) {
		List<Product> products=productService.getProductsByCategory(categoryName);                         //istenilen categoryName ile kay�tlardaki categoryName'ler e�le�tirilir ayn� olanlar listeye eklenir ve liste view sayfas�na g�nderilir.
		model.addAttribute("products", products);
		return "productList";
	}
	//productList.jsp sat�r 100
	
	
	
	
	
	@RequestMapping("/brand/{brands}")              //{categoryName} ne gelirse gelsin istek kar��lan�r ve {categoryName}'e g�re sonuc �retilir.
	public String listProductsByBrands(@MatrixVariable(pathVar="brands") List<String> brands, Model model) {
		List<Product> products=productService.getProductsByBrands(brands);                         //istenilen categoryName ile kay�tlardaki categoryName'ler e�le�tirilir ayn� olanlar listeye eklenir ve liste view sayfas�na g�nderilir.
		model.addAttribute("products", products);
		return "productList";
	}
	
	
	
	
	
	
	//GET metodu ile �r�n ekleme sayfas�n� �a��rma
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String addProductPage(@ModelAttribute("newProduct") Product productToAdded) {
		System.out.println("addProductPage#get invoked");
		
		//�r�n ekleme sayfas�nda input alanlar�na ba�lang�� de�eri atamak i�in a�a��daki setleme metotlar� kullan�l�r
		productToAdded.setDescription("aciklama kismi..");
		productToAdded.setCategory("Laptop");
		return "productAdd_messageBundle_validation";
		//return "productAdd_messageBundle";
		//return "productAdd";
	}
	// �NEML� //@modelattribute kullanma amac�m�z http://localhost:8080/SpringMVCWeek04/products/add  sayfas�nda girilen degelerin productToAdded objesine dolmas�d�r.
	//�NEML�// <form:form  modelAttribute="newProduct" class="form-horizontal" method="post">          @ModelAttribute("newProduct")     newProduct isimleri ayn� olmak zorundad�r.
	//@ModelAttribute yard�m�yle formumuzu kolayca y�nettik
	
	
	
	@ModelAttribute("manufacturerList")
	public List<String>prepareManufacturers(){
		System.out.println("prepareManufacturers#invoked");
		return Arrays.asList("Apple","Samsung","Dell");
	}
	//productAdd.jsp sayfas�nda select kutular�n�n i�ine verileri dinamik oalrak yaz�yoruz.
	//<form:select id="manufacturer" path="manufacturer" type="text" class="form:input-large" items="${manufacturerList}"/>
	
	
	@ModelAttribute("categoryList")
	public List<String>prepareCategories(){
		System.out.println("prepareCategories#invoked");
		return Arrays.asList("Tablet","Laptop","SmartPhone");
	}
	
	@ModelAttribute("conditionMap")
	public Map<String,String>prepareConditions(){
		System.out.println("prepareConditions#invoked");
		Map<String,String> conditions=new LinkedHashMap<>();
		conditions.put("new", "New");
		conditions.put("old", "Old");
		conditions.put("refurbished", "Refurbished");
		return conditions;
	}
	
	
	
	
	//POST metodu ile �r�n ekleme           //validation'suz hali             //bir �nceki hafta ile ayn�
//	@RequestMapping(value="/add",method=RequestMethod.POST)
//	public String addProductPageSubmit(@ModelAttribute("newProduct") Product productToAdded) {
//		productService.addProduct(productToAdded);
//		return "redirect:/products";
//	}
	
	
	//validator'�n 2 y�ntemi var.
	//1. y�ntem: 	ProductValidator s�n�f� olusturuldu. ilgili validasyonlar bu s�n�fta yap�ld�       											
//	@RequestMapping(value="/add",method=RequestMethod.POST)
//	public String addProductPageSubmit(@ModelAttribute("newProduct") Product productToAdded,BindingResult bindingResult) {        //hatalar bindingResult objesine yaz�ld�
//		
//		productValidator.validate(productToAdded, bindingResult);
//		
	//hata varsa ilgili hatan�n input alan�n�n yan�na yaz�lmas� i�in ilgili jsp sayfas�na g�nderilir. Bu jsp sayfas�nda spring-message etiketi kullan�lm��t�r.yan�na yaz�lacak hata mesaj burdan yaz�l�r. Ne hatas� yazd�rmak istiyorsak properties dosyas�nda biz tan�ml�yoruz
//		if(bindingResult.hasErrors()) {
//			return "productAdd_messageBundle_validation";
//		}
//		
	//e�er hata yoksa kay�d� ekler ve �r�nler sayfas�na d�ner 
//		productService.addProduct(productToAdded);
//		return "redirect:/products";
//	}
	
	
	//2. y�ntem :BeanValidation yakla��m�      //Product s�n�f�nda variable'lar�n �st�ne annotation konuldu.  ------------pom.xml'e dependecy eklendi. hibernate validator kullan�ld�
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addProductPageSubmit(@ModelAttribute("newProduct") @Valid Product productToAdded,BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "productAdd_messageBundle_validation";					
		}
		//hata mesajlar� otomatik yaz�ld�. spring kendi halletti. (boyut 3 ile 10 aras�nda olmal�) bu hata mesajlar�n� override edelim.Bunu override etmek i�in properties dosyas�na ilgili hatalar�n mesajlar� yaz�ld�. eger bunlar� yazmazsak default olarak kendi mesaj�n� bas�yor spring
		//e�er hata yoksa kay�d� ekler ve �r�nler sayfas�na d�ner
		productService.addProduct(productToAdded);
		return "redirect:/products";
	}
}


/*
 <!-- �nemli nokta:1) taglib'leri eklememiz gerekiyor 
2)commandname ile modelAttribute ayn�d�r. command eskimi�tir.
3) path bilgisi classlarda instance variable isimleriyle ayn� olmak zorunda. s�n�f degiskeni ile yani.
-->*/

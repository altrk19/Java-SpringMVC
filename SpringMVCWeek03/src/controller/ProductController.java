package controller;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import model.Product;
import service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	
	//http://localhost:8080/SpringMVCWeek03/products/              istek ilk �nce default olarak buraya d���yor. ve �r�nler listeleniyor.
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
	//href=" <c:url value="/products/product?id=${product.productId}" /> "                   burada kars�lan�r.       http://localhost:8080/SpringMVCWeek03/products/product?id=P1001          //buradaki /product?id=P1001 1001 bilgisi de jsp dosyas�nda objeden �ekildi.
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
		return "productAdd";
	}
	// �NEML� //@modelattribute kullanma amac�m�z http://localhost:8080/SpringMVCWeek03/products/add  sayfas�nda girilen degelerin productToAdded objesine dolmas�d�r.
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
		conditions.put("old", "New");
		conditions.put("refurbished", "Refurbished");
		return conditions;
	}
	
	
	
	
	//POST metodu ile �r�n ekleme
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addProductPageSubmit(@ModelAttribute("newProduct") Product productToAdded) {
		productService.addProduct(productToAdded);
		return "redirect:/products";
	}
	
	
}


/*
 <!-- �nemli nokta:1) taglib'leri eklememiz gerekiyor 
2)commandname ile modelAttribute ayn�d�r. command eskimi�tir.
3) path bilgisi classlarda instance variable isimleriyle ayn� olmak zorunda. s�n�f degiskeni ile yani.
-->*/

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
	
	//Validation destegi için
	@Autowired
	private ProductValidator productValidator;
	
	
	//http://localhost:8080/SpringMVCWeek04/products/              istek ilk önce default olarak buraya düþüyor. ve ürünler listeleniyor.
	@RequestMapping
	public String defaultPage(Model model) {
		List<Product> products=productService.getAllProducts();
		model.addAttribute("products",products);							//hem view sýnýfýnýn adýný dönüyorum hem de view sýnýfýnda gösterilecek elemanlarýn listesini dönüyorum
		return "productList";
	}
	
	
	
	
	
	@RequestMapping("/all")
	public String allProducts(Model model) {
		List<Product> products=productService.getAllProducts();
		model.addAttribute("products",products);							//hem view sýnýfýnýn adýný dönüyorum hem de view sýnýfýnda gösterilecek elemanlarýn listesini dönüyorum
		return "productList";
	}
	
	
	
	
	
	
	//productId'ye göre ürün getircez. ürün detaylarýna bakacagýz
	//ürün detay sayfasý olacak yani
	@RequestMapping("/product")
	public String getProductById(@RequestParam(name="id")String productId,Model model) {
		Product product=productService.getProductById(productId);                 //id bilgisini verip o id'li ürünü istiyoruz
		model.addAttribute("product",product);
		return "productDetail";
	}
	//productList.jsp satýr 123
	//href=" <c:url value="/products/product?id=${product.productId}" /> "                   burada karsýlanýr.       http://localhost:8080/SpringMVCWeek04/products/product?id=P1001          //buradaki /product?id=P1001 1001 bilgisi de jsp dosyasýnda objeden çekildi.
	//id bilgisi sorgudan alýnýr ve ilgili ürün objesi olusturulup jsp sayfasýnda gösterilir.
	
	
	
	
	
	@RequestMapping("/category/{categoryName}")              //{categoryName} ne gelirse gelsin istek karþýlanýr ve {categoryName}'e göre sonuc üretilir.
	public String getProductByCategoryNamed(@PathVariable("categoryName") String categoryName, Model model) {
		List<Product> products=productService.getProductsByCategory(categoryName);                         //istenilen categoryName ile kayýtlardaki categoryName'ler eþleþtirilir ayný olanlar listeye eklenir ve liste view sayfasýna gönderilir.
		model.addAttribute("products", products);
		return "productList";
	}
	//productList.jsp satýr 100
	
	
	
	
	
	@RequestMapping("/brand/{brands}")              //{categoryName} ne gelirse gelsin istek karþýlanýr ve {categoryName}'e göre sonuc üretilir.
	public String listProductsByBrands(@MatrixVariable(pathVar="brands") List<String> brands, Model model) {
		List<Product> products=productService.getProductsByBrands(brands);                         //istenilen categoryName ile kayýtlardaki categoryName'ler eþleþtirilir ayný olanlar listeye eklenir ve liste view sayfasýna gönderilir.
		model.addAttribute("products", products);
		return "productList";
	}
	
	
	
	
	
	
	//GET metodu ile ürün ekleme sayfasýný çaðýrma
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String addProductPage(@ModelAttribute("newProduct") Product productToAdded) {
		System.out.println("addProductPage#get invoked");
		
		//ürün ekleme sayfasýnda input alanlarýna baþlangýç deðeri atamak için aþaðýdaki setleme metotlarý kullanýlýr
		productToAdded.setDescription("aciklama kismi..");
		productToAdded.setCategory("Laptop");
		return "productAdd_messageBundle_validation";
		//return "productAdd_messageBundle";
		//return "productAdd";
	}
	// ÖNEMLÝ //@modelattribute kullanma amacýmýz http://localhost:8080/SpringMVCWeek04/products/add  sayfasýnda girilen degelerin productToAdded objesine dolmasýdýr.
	//ÖNEMLÝ// <form:form  modelAttribute="newProduct" class="form-horizontal" method="post">          @ModelAttribute("newProduct")     newProduct isimleri ayný olmak zorundadýr.
	//@ModelAttribute yardýmýyle formumuzu kolayca yönettik
	
	
	
	@ModelAttribute("manufacturerList")
	public List<String>prepareManufacturers(){
		System.out.println("prepareManufacturers#invoked");
		return Arrays.asList("Apple","Samsung","Dell");
	}
	//productAdd.jsp sayfasýnda select kutularýnýn içine verileri dinamik oalrak yazýyoruz.
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
	
	
	
	
	//POST metodu ile ürün ekleme           //validation'suz hali             //bir önceki hafta ile ayný
//	@RequestMapping(value="/add",method=RequestMethod.POST)
//	public String addProductPageSubmit(@ModelAttribute("newProduct") Product productToAdded) {
//		productService.addProduct(productToAdded);
//		return "redirect:/products";
//	}
	
	
	//validator'ýn 2 yöntemi var.
	//1. yöntem: 	ProductValidator sýnýfý olusturuldu. ilgili validasyonlar bu sýnýfta yapýldý       											
//	@RequestMapping(value="/add",method=RequestMethod.POST)
//	public String addProductPageSubmit(@ModelAttribute("newProduct") Product productToAdded,BindingResult bindingResult) {        //hatalar bindingResult objesine yazýldý
//		
//		productValidator.validate(productToAdded, bindingResult);
//		
	//hata varsa ilgili hatanýn input alanýnýn yanýna yazýlmasý için ilgili jsp sayfasýna gönderilir. Bu jsp sayfasýnda spring-message etiketi kullanýlmýþtýr.yanýna yazýlacak hata mesaj burdan yazýlýr. Ne hatasý yazdýrmak istiyorsak properties dosyasýnda biz tanýmlýyoruz
//		if(bindingResult.hasErrors()) {
//			return "productAdd_messageBundle_validation";
//		}
//		
	//eðer hata yoksa kayýdý ekler ve ürünler sayfasýna döner 
//		productService.addProduct(productToAdded);
//		return "redirect:/products";
//	}
	
	
	//2. yöntem :BeanValidation yaklaþýmý      //Product sýnýfýnda variable'larýn üstüne annotation konuldu.  ------------pom.xml'e dependecy eklendi. hibernate validator kullanýldý
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addProductPageSubmit(@ModelAttribute("newProduct") @Valid Product productToAdded,BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "productAdd_messageBundle_validation";					
		}
		//hata mesajlarý otomatik yazýldý. spring kendi halletti. (boyut 3 ile 10 arasýnda olmalý) bu hata mesajlarýný override edelim.Bunu override etmek için properties dosyasýna ilgili hatalarýn mesajlarý yazýldý. eger bunlarý yazmazsak default olarak kendi mesajýný basýyor spring
		//eðer hata yoksa kayýdý ekler ve ürünler sayfasýna döner
		productService.addProduct(productToAdded);
		return "redirect:/products";
	}
}


/*
 <!-- önemli nokta:1) taglib'leri eklememiz gerekiyor 
2)commandname ile modelAttribute aynýdýr. command eskimiþtir.
3) path bilgisi classlarda instance variable isimleriyle ayný olmak zorunda. sýnýf degiskeni ile yani.
-->*/

package _01.controller._3_pathVariable;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/products")
public class ProductController {

	// @RequestMapping("/tablet")
	// public String handleRequestTablet() {
	// ///
	// //
	// }
	//
	// @RequestMapping("/laptop")
	// public String handleRequestLaptop() {
	// ///
	// //
	// }
	// //
	//
	// @RequestMapping("/desktop")
	// public String handleRequestDesktop() {
	// ///
	// //
	// }
	
	
	//N tane kategoriyi teke düþürüyoruz
	//http://localhost:8080/SpringMVCWeek02/products/tablet
	//http://localhost:8080/SpringMVCWeek02/products/laptop
	@RequestMapping("/{category}")       //desktop /tablet /laptop istegi geldigi zaman istek buraya düþecek.Sonsuz sayýda metot yazmaktan kurtuluyoruz.
	public String listProductsByCategory(@PathVariable("category") String category, Model model) {
		//Model model  burada da tanýmlayabiliriz.
		model.addAttribute("message", "listProductsByCategory : " + category);
		return "productPage";
	}

	
	
	
	
	
	
	
	@RequestMapping("/product.detail")
	// /product.detail?id=1000
	// request.getParemeter("id");

	// @RequestParam ve @PathVariable icin , varsayilan isim degisken ismi ile aynidir. (name="marka" = String marka)

	//http://localhost:8080/SpringMVCWeek02/products/product.detail?productId=100
	//http://localhost:8080/SpringMVCWeek02/products/product.detail?productId=100&marka=marka2
	public String productDetail(@RequestParam int productId,
			@RequestParam(required = false, name = "marka") String marka, Model model) {                         //required=false ile marka bilgisi yazýlmasa hata vermez
		model.addAttribute("message", "productDetail : " + productId + " marka: " + marka);
		return "productPage";
}

	
	//http://localhost:8080/SpringMVCWeek02/products/product.moredetail/colors=red,blue;lowPrice=100;highPrice=250
	@RequestMapping("/product.moredetail/{matrixVariables}")
	public String moreDetailParameter(@MatrixVariable(pathVar="matrixVariables",name="colors") List<String> colors,
			@MatrixVariable(pathVar="matrixVariables",name="lowPrice") String lowPrice,
			@MatrixVariable(pathVar="matrixVariables",name="highPrice") String highPrice,Model model) {
		
		model.addAttribute("message","moreDetailParameter : "+lowPrice+" "+highPrice+" "+colors);
		return "productPage";
	}
	

}

// /customers/100 --> ilgili customer detail
// /person/11111 -> ilgili person detail bilgisi.

// @PathVariable
// @RequestParam
// @MatrixVariable

// products/tablet
// products/computer
// products/phone
// products/tv
// ..N tane olabilir.

// query parameter
//
// /path?queryParameter1=value&queryParameter2=value2&queryParameter3=value3

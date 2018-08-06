package _01.controller._4_requestParam;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/products2")
public class ProductController2 {

	
	@RequestMapping("/product.detail")
	// /product.detail?id=1000
	// request.getParemeter("id");

	// @RequestParam ve @PathVariable icin , varsayilan isim degisken ismi ile aynidir. (name="marka" = String marka)

	//http://localhost:8080/SpringMVCWeek02/products2/product.detail?productId=100
	//http://localhost:8080/SpringMVCWeek02/products2/product.detail?productId=100&marka=marka2
	public String productDetail(@RequestParam int productId,
			@RequestParam(required = false, name = "marka") String marka, Model model) {                         //required=false ile marka bilgisi yazýlmasa hata vermez
		model.addAttribute("message", "productDetail : " + productId + " marka: " + marka);
		return "productPage";
	}

}
/*
@RequestParam annotation’ini ile query paremeter’lara erisim saglayabiliriz.
*/

// query parameter
//
// /path?queryParameter1=value&queryParameter2=value2&queryParameter3=value3

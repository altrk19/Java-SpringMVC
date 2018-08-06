package _01.controller._5_matrixVariable;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products3")
public class ProductController3 {

	
	//http://localhost:8080/SpringMVCWeek02/products3/product.moredetail/colors=red,blue;lowPrice=100;highPrice=250
	@RequestMapping("/product.moredetail/{matrixVariables}")
	public String moreDetailParameter(@MatrixVariable(pathVar="matrixVariables",name="colors") List<String> colors,
			@MatrixVariable(pathVar="matrixVariables",name="lowPrice") String lowPrice,
			@MatrixVariable(pathVar="matrixVariables",name="highPrice") String highPrice,Model model) {
		
		model.addAttribute("message","moreDetailParameter : "+lowPrice+" "+highPrice+" "+colors);
		return "productPage";
	}
	

}
/*
Matrix parameter�lar Spring 3.2 versiyonuyla birlikte Spgring�e dahil oldu.

Matrix variable , complex ve fazla sayidaki HTTP GET , URI argument/parametreleri yonetmek icin kolaylik saglamaktadir.

Matrix parameter�lar , URI �da equals (=) karakteriyle tanimlanir ve noktali virgul karakteriyle (;) birden fazla matrix parameter�i ayirabiliriz.
Ayni matrix parameter icin birden fazla deger vermek icin virgul (,) karakterini kullanabiliriz.

Genel format;

path/matrixParam=value1,value2,... valueX;matrixParam2=value1,value2... valueX
Marix Paremeter�lar varsayilan/default olarak kapalidir. (false)
Kullanabilmek icin XML konfigurasyon dosyamizda <mvc:annotation-driven> tag�inda enable-matrix-variable attribute�unu true yapabiliriz.
*/
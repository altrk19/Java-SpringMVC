package validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import model.Product;

@Component
public class ProductValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		Product product = (Product) target;
		
		//hata ile karþýlaþabileceðiz durumlar
		if (product.getProductId() == null || product.getProductId().trim().length() < 5) {        //id boþ olamaz veya 5 haneden küçük olamaz
			errors.rejectValue("productId", "required.productId");       //properties dosyasýndaki
			//productId , Product sinifindaki property alanina karsilik gelmektedir , 
			//required.productId ise kullanacagimiz mesaj ismine karsilik gelmektedir
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required.name");         //name alaný zorunku

		if (product.getUnitPrice() <= 0) {											//fiyat 0 dan küçük olamaz
			errors.rejectValue("unitPrice", "required.unitPrice");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "required.description");     //description zorunlu

		if (product.getUnitsInStock() <= 0) {											//stok sayýsý 0 dan küçük olamaz
			errors.rejectValue("unitsInStock", "required.unitsInStock");
		}
		
		System.out.println(errors);
		
	}

}

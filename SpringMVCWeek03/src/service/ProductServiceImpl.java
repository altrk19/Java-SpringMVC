package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ProductDAO;
import model.Product;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDAO productDao;
	
	@Override
	public List<Product> getAllProducts() {
		return productDao.getAllProducts();
	}

	@Override
	public Product getProductById(String productId) {
		return productDao.getProductById(productId);
	}

	@Override
	public List<Product> getProductsByCategory(String categoryName) {
		return productDao.getProductsByCategory(categoryName);
	}

	@Override
	public List<Product> getProductsByBrands(List<String> brands) {
		return productDao.getProductsByBrands(brands);
	}

	@Override
	public void addProduct(Product product) {
		productDao.addProduct(product);
		
	}



}

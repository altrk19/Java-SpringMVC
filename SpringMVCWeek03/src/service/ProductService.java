package service;

import java.util.List;

import model.Product;

public interface ProductService {
	
	public List<Product> getAllProducts();
	
	public Product getProductById(String productId);
	
	public List<Product> getProductsByCategory(String categoryName);
	
	public List<Product> getProductsByBrands(List<String> brands);
	
	public void addProduct(Product product);
}

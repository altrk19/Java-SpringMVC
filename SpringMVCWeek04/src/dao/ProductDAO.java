package dao;

import java.util.List;

import model.Product;

public interface ProductDAO {
	
	public List<Product> getAllProducts();
	
	public Product getProductById(String productId);
	
	public List<Product> getProductsByCategory(String categoryName);
	
	public List<Product> getProductsByBrands(List<String> brands);    //sql a��s�ndan IN mant�g�
	
	public void addProduct(Product product);
}

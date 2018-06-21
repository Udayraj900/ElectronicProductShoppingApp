/**
 * 
 */
package com.mindtree.webservices.service;

import java.util.List;
import com.mindtree.webservices.entity.Product;

/**
 * @author RShaw
 *
 */

public interface ProductService {

	public List<Product> getAllProducts();

	public Product getProductById(int productId);

	public Product addProduct(Product product);

	public Product updateProduct(Product product);

	public void deleteProduct(int productId);

	public List<Product> getProductbyCategory(String productCategory);

	public List<Product> getProductbyPrice(double productPrice);
}

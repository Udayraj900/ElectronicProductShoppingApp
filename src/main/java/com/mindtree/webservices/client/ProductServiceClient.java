/**
 * 
 */
package com.mindtree.webservices.client;

import java.util.List;

import org.springframework.ui.Model;

import com.mindtree.webservices.entity.Product;

/**
 * @author RShaw
 *
 */
public interface ProductServiceClient {

	public List<Product> getAllProducts();

	public Product getProductById(int id);

	public Product addProduct(Product product, Model model);

	public Product updateProduct(int id, Product product, Model model);

	public Product deleteProduct(String productId, Model model);

	public List<Product> getProductByCategory(String productCategory);

	public List<Product> getProductByPrice(String productprice);

	public Product buyProduct(int productId, Product product, Model model);
}

/**
 * 
 */
package com.mindtree.webservices.client;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.mindtree.webservices.entity.Product;
/**
 * @author RShaw
 *
 */
@Service
public class ProductServiceClientImpl implements ProductServiceClient {

	private static final String REST_SERVICE_URL = "http://localhost:8080/ElectronicProductShoppingApp";
	
	Client client;

	@PostConstruct
	public void init() {
		System.out.println("init method called");
		this.client = ClientBuilder.newClient();
	}

	
	public List<Product> getAllProducts() {
		GenericType<List<Product>> list = new GenericType<List<Product>>() {};
		List<Product> productList = client.target(REST_SERVICE_URL).path("products")
				.request().get(list);
		return productList;
	}


	@Override
	public Product getProductById(int id) {
		Product  product = client.target(REST_SERVICE_URL).path("products").path("{id}").resolveTemplate("id", id).request(MediaType.APPLICATION_JSON).get(Product.class);
		return product;
	}


	@Override
	public Product addProduct(Product product ,Model model ) {	
		GenericType<Product> genProduct = new GenericType<Product>() {};
		Product returnProduct = client.target(REST_SERVICE_URL).path("addProduct").request(MediaType.APPLICATION_XML)
				.post(Entity.entity(product, MediaType.APPLICATION_XML), genProduct);
		model.addAttribute("newProduct", returnProduct);
		return returnProduct;
	}


	@Override
	public Product updateProduct(int id, Product product, Model model) {
		GenericType<Product> genProduct = new GenericType<Product>() {};
		Product returnProduct = client.target(REST_SERVICE_URL).path("updateProduct").path("{id}").resolveTemplate("id", id).request(MediaType.APPLICATION_XML)
				.put(Entity.entity(product, MediaType.APPLICATION_XML), genProduct);
		model.addAttribute("updatedProduct", returnProduct);	
		return returnProduct;
	}


	@Override
	public Product deleteProduct(String productId, Model model) {
		
		Product deletedProduct = client.target(REST_SERVICE_URL).path("deleteProduct").path("{productId}").resolveTemplate("productId", productId).request(MediaType.APPLICATION_XML).delete(Product.class);
		System.out.println(deletedProduct.toString());
		model.addAttribute("deletedProduct", deletedProduct);	
		return deletedProduct;
	}


	@Override
	public List<Product> getProductByCategory(String productCategory) {
		GenericType<List<Product>> list = new GenericType<List<Product>>() {};
		List<Product> productList = client.target(REST_SERVICE_URL).path("category").path("{productCategory}").resolveTemplate("productCategory", productCategory)
				.request(MediaType.APPLICATION_JSON).get(list);
		return productList;
	}


	@Override
	public List<Product> getProductByPrice(String productPrice) {
		GenericType<List<Product>> list = new GenericType<List<Product>>() {};
		List<Product> productList = client.target(REST_SERVICE_URL).path("price").path("{productPrice}").resolveTemplate("productPrice", productPrice)
				.request(MediaType.APPLICATION_JSON).get(list);
		return productList;
	}


	@Override
	public Product buyProduct(int productId, Product product, Model model) {
		GenericType<Product> genProduct = new GenericType<Product>() {};
		Product returnProduct = client.target(REST_SERVICE_URL).path("buyProduct").path("{productId}").resolveTemplate("productId",productId).request(MediaType.APPLICATION_JSON)
				.put(Entity.entity(product, MediaType.APPLICATION_JSON), genProduct);
		model.addAttribute("boughtProduct", returnProduct);
		return returnProduct;
	}

}

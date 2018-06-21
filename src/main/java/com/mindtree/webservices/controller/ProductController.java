/**
 * 
 */
package com.mindtree.webservices.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.webservices.entity.ErrorResponse;
import com.mindtree.webservices.entity.Product;
import com.mindtree.webservices.exception.productException;
import com.mindtree.webservices.service.ProductService;

/**
 * @author RShaw
 *
 */
@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;

	
	
	@RequestMapping(value = "/products", method = RequestMethod.GET )
	public @ResponseBody List<Product> getAllProduct(){
		List<Product> products = productService.getAllProducts();
		
		return products;
	}
	
	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody Product getProductById(@PathVariable("id") int id) {
		Product product = productService.getProductById(id);
		return product;
	}
	
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST,produces= {"application/json","application/xml"},consumes ={"application/json","application/xml"})
	public @ResponseBody Product addProduct(@RequestBody Product product) {
		System.out.println("controller : "+product);
		productService.addProduct(product);	
		return product;
	}
	
	@RequestMapping(value = "/updateProduct/{id}", method = RequestMethod.PUT, produces= {"application/json","application/xml"},consumes ={"application/json","application/xml"})
	public @ResponseBody Product updateProduct(@PathVariable("id") int id,@RequestBody Product product) {
		product.setProductId(id);
		productService.updateProduct(product);
		
		return product;
	}
	
	@RequestMapping(value = "/deleteProduct/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Product deleteProduct(@PathVariable("id") int id) {
		Product product = productService.getProductById(id);
		productService.deleteProduct(id);
		
		return product;
	}
	
	@RequestMapping(value = "/category/{categoryName}", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody List<Product> getProductByCategory(@PathVariable("categoryName") String categoryName) {
		List<Product> product = productService.getProductbyCategory(categoryName);
		return product;
	}
	
	@RequestMapping(value = "/price/{priceRange}", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody List<Product> getProductByPrice(@PathVariable("priceRange") double priceRange) {
		List<Product> product = productService.getProductbyPrice(priceRange);
		return product;
	}
	
	@RequestMapping(value = "/buyProduct/{id}", method = RequestMethod.PUT, produces= {"application/json","application/xml"},consumes ={"application/json","application/xml"})
	public @ResponseBody Product buyProductById(@PathVariable("id") int id,@RequestBody Product product) {
		int currentStock = product.getCurrentStockNumbers() - 1;
		product.setProductId(id);
		product.setCurrentStockNumbers(currentStock);
		productService.updateProduct(product);
		
		return product;
	}

	
	@ExceptionHandler(productException.class)
	    public ResponseEntity<ErrorResponse> exceptionHandler(HttpServletRequest request, Exception ex) {
	        ErrorResponse error = new ErrorResponse(ex.toString(),request.getRequestURI());
	        return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
	    }

}

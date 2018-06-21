/**
 * 
 */
package com.mindtree.webservices.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mindtree.webservices.client.ProductServiceClient;
import com.mindtree.webservices.entity.Product;

/**
 * @author RShaw
 *
 */
@Controller
public class BuyerController {

	/**
	 * 
	 */
	@Autowired
	ProductServiceClient productServiceClient;

	@RequestMapping(value = "/listProductByCategoryPage" )
	public String getProductsByCategoryPage(Model model) {
		List<Product> productList = productServiceClient.getAllProducts();
		List<String> categories = productList.stream().map(Product::getProductCategory).collect(Collectors.toList()).stream().distinct().collect(Collectors.toList());
		
		model.addAttribute("categories", categories);
		System.out.println(categories);
		return "getProductsByCategory";
	}
	
	@RequestMapping(value = "/getProductListByCategory", produces = "application/json")
	public String getProductByCategory(@RequestParam("category") String productCategory, Model model) {
		System.out.println("product category :" +productCategory );
		List<Product> products = productServiceClient.getProductByCategory(productCategory);	
		model.addAttribute("productsByCategory", products);
		List<Product> productList = productServiceClient.getAllProducts();
		List<String> categories = productList.stream().map(Product::getProductCategory).collect(Collectors.toList()).stream().distinct().collect(Collectors.toList());
		model.addAttribute("categories", categories);
		
		return "getProductsByCategory";
	}
	
	@RequestMapping(value = "/listProductByPriceRangePage" )
	public String getProductsByPricePage(Model model) {
		return "getProductsByPrice";
	}
	
	@RequestMapping(value = "/getProductByPriceRange", produces = "application/json")
	public String getProductByPrice(@RequestParam("productPrice") String productPrice, Model model) {
		System.out.println("product price :" +productPrice );
		List<Product> products = productServiceClient.getProductByPrice(productPrice);	
		model.addAttribute("productsByPrice", products);
		
		return "getProductsByPrice";
	}
	
	@RequestMapping(value = "/buyProductByProductIdPage" )
	public String buyProductsById(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		return "buyProduct";
	}
	
	@RequestMapping("/buyProductById")
	public String buyAProduct(@ModelAttribute("product") Product product, Model model) {
		Product boughtProduct = productServiceClient.buyProduct(product.getProductId(),product, model);
		model.addAttribute("msg", "Product "+boughtProduct.getProductName()+ " is purchased successfully with product id :"+boughtProduct.getProductId());
		return "buyProduct";
	}
}

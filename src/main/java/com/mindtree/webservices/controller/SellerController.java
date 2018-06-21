package com.mindtree.webservices.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mindtree.webservices.client.ProductServiceClient;
import com.mindtree.webservices.entity.Product;
import com.mindtree.webservices.exception.productException;

@Controller
public class SellerController {

	@Autowired
	ProductServiceClient productServiceClient;
	
	@RequestMapping(value = "/getProductPage" )
	public String getProductPage(Model model) {
		List<Product> productList = productServiceClient.getAllProducts();
		model.addAttribute("productList", productList);
		System.out.println(productList.toString());
		return "getproduct";
	}

	@RequestMapping(value = "/getProductById", produces = "application/json")
	@ResponseBody
	public List<Product> getProductById(@RequestParam("product") String productId) {
		System.out.println("product id :" +productId );
		List<Product> products = new ArrayList<Product>();
		if(productId.equalsIgnoreCase("-1")) {
			products = productServiceClient.getAllProducts();
		}else {
		Product product = productServiceClient.getProductById(Integer.parseInt(productId));	
		products.add(product);
		}
		return products;
	}

	@RequestMapping(value="/addProductPage")
	public String getAddProductPage(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		return "addProduct";
	}
	
	@RequestMapping("/saveProduct")
	public String saveProduct(@ModelAttribute("product") Product product ,Model model) {
		System.out.println("saveProduct :"+product.toString());
		if(product!=null) {
		Product returnProduct = productServiceClient.addProduct(product , model);
		
		model.addAttribute("msg", "Product "+returnProduct.getProductName()+ " is added successfully with product id :"+returnProduct.getProductId());
		
		}return "addProduct";
	}

	@RequestMapping(value="/updateProductPage")
	public String getUpdateProductPage(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		return "updateProduct";
	}
	
	@RequestMapping("/updateExistingProduct")
	public String update(@ModelAttribute("product") Product product, Model model) {
		Product updatedProduct = productServiceClient.updateProduct(product.getProductId(),product, model);
		model.addAttribute("msg", "Product "+updatedProduct.getProductName()+ " is updated successfully for product id :"+updatedProduct.getProductId());
		return "updateProduct";
	}
	
	@RequestMapping(value="/deleteProductPage")
	public String getDeleteProductPage(Model model) {
		List<Product> productList = productServiceClient.getAllProducts();
		model.addAttribute("productList", productList);
		return "deleteProduct";
	}
	
	@RequestMapping("/deleteExistingProduct")
	public String delete(@RequestParam("product") String productId, Model model) {
		Product deletedProduct = productServiceClient.deleteProduct(productId, model);
		model.addAttribute("msg", "Product "+deletedProduct.getProductName()+ " is deleted successfully for product id :"+deletedProduct.getProductId());
		List<Product> productList = productServiceClient.getAllProducts();
		model.addAttribute("productList", productList);
		return "deleteProduct";
	}
	
	@ExceptionHandler(productException.class)
	public String handleException(Model model, Exception e) {
		model.addAttribute("exceptionMsg",e);
		return "errorPage";
	}
}

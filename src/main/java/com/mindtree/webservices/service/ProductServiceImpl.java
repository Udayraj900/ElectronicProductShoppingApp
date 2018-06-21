/**
 * 
 */
package com.mindtree.webservices.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.webservices.dao.ProductDao;
import com.mindtree.webservices.entity.Product;

/**
 * @author RShaw
 *
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductDao productDao;

	@Override
	public List<Product> getAllProducts() {
		return productDao.getAllProducts();
	}

	@Override
	public Product getProductById(int productId) {
		return productDao.getProductById(productId);
	}

	@Override
	public Product addProduct(Product product) {
		System.out.println("Service : "+product);
		return productDao.addProduct(product);
	}

	@Override
	public Product updateProduct(Product product) {
		return productDao.updateProduct(product);
	}

	@Override
	public void deleteProduct(int productId) {
		productDao.deleteProduct(productId);
	}

	@Override
	public List<Product> getProductbyCategory(String productCategory) {
		return productDao.getProductbyCategory(productCategory);
	}

	@Override
	public List<Product> getProductbyPrice(double productPrice) {
		return productDao.getProductbyPrice(productPrice);
	}

}

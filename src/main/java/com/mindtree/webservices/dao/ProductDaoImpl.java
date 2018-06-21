/**
 * 
 */
package com.mindtree.webservices.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mindtree.webservices.entity.Product;

/**
 * @author RShaw
 *
 */
@Repository
public class ProductDaoImpl implements ProductDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getAllProducts() {
		Criteria criteria = getSession().createCriteria(Product.class);
		return (List<Product>) criteria.list();
	}

	@Override
	public Product getProductById(int productId) {
		return (Product) getSession().get(Product.class, productId);
	}

	@Override
	public Product addProduct(Product product) {
		System.out.println("DAO : "+product);
		getSession().save(product);
		return product;
	}

	@Override
	public Product updateProduct(Product product) {
		getSession().saveOrUpdate(product);
		return product;
	}

	@Override
	public void deleteProduct(int productId) {
		Product product = (Product) getSession().get(Product.class, productId);
		getSession().delete(product); 
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getProductbyCategory(String productCategory) {
		String hql = "from Product where productCategory= :productCategory";
		Query query = getSession().createQuery(hql);
		query.setParameter("productCategory",productCategory);
		List<Product> productList = query.list();
		if (productList.size() != 0) {
			return productList;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getProductbyPrice(double productPrice) {
		String hql = "from Product where price >= :productPrice";
		Query query = getSession().createQuery(hql);
		query.setParameter("productPrice",productPrice);
		List<Product> productList = query.list();
		if (productList.size() != 0) {
			return productList;
		}
		return null;
	}
	

}

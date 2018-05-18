package com.ip.itest.generics.services.impl;


import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ip.itest.generics.dbo.ProductCategory;
import com.ip.itest.generics.dbo.Products;
import com.ip.itest.generics.services.ProductCategoryService;

@Service("productCategoryService")
public class ProductCategoryServiceImpl extends GenericServiceImpl<ProductCategory, ProductCategory, Long> implements ProductCategoryService{

	private static final Logger log = (Logger) LoggerFactory.getLogger(ProductCategoryServiceImpl.class);

	@Override
	public void doNoob(String data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Products> getProductsByCategory(int catId) {
		ProductCategory productCategory= findById((long)catId);
		if (productCategory==null) return null;
		return(productCategory.getProducts());
		
	}

	
	
}

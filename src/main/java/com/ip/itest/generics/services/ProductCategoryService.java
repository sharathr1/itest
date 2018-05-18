package com.ip.itest.generics.services;

import java.util.List;
import java.util.Set;

import com.ip.itest.generics.dbo.ProductCategory;
import com.ip.itest.generics.dbo.Products;

public interface ProductCategoryService extends GenericService<ProductCategory, ProductCategory, Long> 
{
	
	void doNoob(String data);
	Set<Products> getProductsByCategory(int catId);
	
}
package com.coding404.myweb.product.service;

import java.util.ArrayList;

import com.coding404.myweb.command.ProductVO;

public interface ProductService {
	
	public int regist(ProductVO vo); //insert
	public ArrayList<ProductVO> getList();
	public ProductVO getDetail(int prod_id);
	public int update(ProductVO vo); //update;
	public void delete (int prod_id);
	

}

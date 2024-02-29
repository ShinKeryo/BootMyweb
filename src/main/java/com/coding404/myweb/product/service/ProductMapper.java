package com.coding404.myweb.product.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.coding404.myweb.command.ProductVO;

@Mapper //스트링부트에서는 필수
public interface ProductMapper {
	
	public int regist(ProductVO vo); //insert
	public ArrayList<ProductVO> getList(); //select
	public ProductVO getDetail(int prod_id); //
	public int update(ProductVO vo); //update;
	public void delete (int prod_id);//delete
}

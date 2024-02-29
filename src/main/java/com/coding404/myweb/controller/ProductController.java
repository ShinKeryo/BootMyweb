package com.coding404.myweb.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.product.service.ProductService;


@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	@Qualifier("productService")
	private ProductService productService;
	
	
	
	
	@GetMapping("/productList")
	public String list(Model model) {
		
		//목록을 가지고 나와서 데이터를 담고 나감
		ArrayList<ProductVO> list = productService.getList();
		model.addAttribute("list",list);
		
		return "product/productList";
	}
	
	@GetMapping("/productReg")
	public String reg() {
		
		return "product/productReg";
	}
	
	@GetMapping("/productDetail")
	public String detail(@RequestParam("prod_id") int prod_id, Model model) {
		
		ProductVO vo = productService.getDetail(prod_id);
		model.addAttribute("vo",vo);
		
		return "product/productDetail";
	}
	
	//상품등록요청
	@PostMapping("/productForm")
	public String productForm(ProductVO vo,RedirectAttributes ra) {
		
		int result = productService.regist(vo);
		
		if(result ==1 ) {//성공
			ra.addFlashAttribute("msg","정상처리 되었습니다"); //리다이렉트 시에 1회성 데이터
			
		}else {//실패
			ra.addFlashAttribute("msg","등록에 실패했습니다. 관리자에게 문의하세요");
			
		}
		
		//같은 페이지에 있는 컨트롤러를 태우고 나간다 redirect:/
		return "redirect:/product/productList";
	}
	
	//업데이트
	
	@PostMapping("/updateForm")
	public String updateForm(ProductVO vo,RedirectAttributes ra) {
		
		int result = productService.update(vo);
		
		
		if(result == 1) {
			
			ra.addFlashAttribute("msg","수정되었습니다" );
			
		}else {
			
			ra.addFlashAttribute("msg","실패했습니다" );
		}
		
		
		//1. 화면에서 넘오는값을 받는다
		//2. 서비스에 update메서드를 생성
		//3. enddate, prod_name, price,count,discount, 설명,내용 업데이트
		//4. 업데이 성공 실패 여부를 담고 저장해서 목록화면으로 이동.
		
		return "redirect:/product/productList";
		
	}
	@PostMapping("/deleteForm")
	public String deleteForm(@RequestParam("prod_id") int prod_id) {
		
		productService.delete(prod_id);
		
		return "redirect:/product/productList";
	}
	
}

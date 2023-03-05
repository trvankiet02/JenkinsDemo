package vn.iotstar.Controller.Admin;

import java.util.List;

import java.util.Optional;

import java.util.stream.Collectors;

import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import org.springframework.util.StringUtils;

import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.ModelAndView;

import vn.iotstar.Entity.Category;
import vn.iotstar.Model.CategoryModel;
import vn.iotstar.Service.ICategoryService;

@Controller

@RequestMapping("admin/categories")

public class CategoryController {

	@Autowired

	ICategoryService categoryService;

	@RequestMapping("")
	public String list(ModelMap model) {

		// gọi hàm findAll() trong service

		List<Category> list = categoryService.findAll();

		// chuyển dữ liệu từ list lên biến categories

		model.addAttribute("categories", list);

		return "admin/categories/listcategory";

	}

	@GetMapping("add")
	public String Add(ModelMap model) {
		CategoryModel cate = new CategoryModel();
		cate.setIsEdit(false);
		model.addAttribute("category", cate);
		return "admin/categories/addOrEdit";
	}

	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("category") CategoryModel cate,
			BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("admin/categories/addOrEdit");
		}
		Category entity = new Category();
		// copy từ Model sang entity
		BeanUtils.copyProperties(cate, entity);

		categoryService.save(entity);

		String message = "";
		if (cate.getIsEdit() == true) {
			message = "Category đã được cập nhật";
		} else {
			message = "Category đã được thêm thành công";
		}
		model.addAttribute("message", message);
		return new ModelAndView("forward:/admin/categories", model);
	}
	
	@GetMapping("edit/{categoryId}")
	public ModelAndView edit(ModelMap model, 
			@PathVariable("categoryId") Long categoryId) {
		
		Optional<Category> opt = categoryService.findById(categoryId);
		
		CategoryModel cate = new CategoryModel();
		if(opt.isPresent()) {
			Category entity = opt.get();
			BeanUtils.copyProperties(entity, cate); // copy từ entity sang model
			cate.setIsEdit(true); 
			model.addAttribute("category",cate);
			return new ModelAndView("admin/categories/addOrEdit",model);
		}
		// Trả về thông báo
		model.addAttribute("message","Category không tồn tại");
		return new ModelAndView("forward:/admin/categories",model);
	}
	
	@GetMapping("delete/{categoryId}")
	public ModelAndView delete(ModelMap model, 
			@PathVariable("categoryId") Long categoryId) {
		categoryService.deleteById(categoryId);
		
		model.addAttribute("message","Category đã được xóa thành công");
		
		// redirect: chuyển nội dung vào trang mới
		// forward: nhúng nội dung vào một trang nào đó
		return new ModelAndView("redirect:/admin/categories",model);
	}
	
	// "name" từ phần views truyền xuống
	// required=false: không nhập hiện ra list ban đầu
	@GetMapping("search")
	public String search(ModelMap model, @RequestParam(name="name",required=false) String name) {
		List<Category> list = null;
		if(StringUtils.hasText(name)) {
			list = categoryService.findByCategorynameContaining(name);
		} else {
			list = categoryService.findAll();
		}
		model.addAttribute("categories",list);
		
		return "admin/categories/search";
	}
	
	// size: số phần tử được hiện thị trên 1 trang
	@GetMapping("searchpaginated")
	public String search(ModelMap model, 
			@RequestParam(name="name",required=false) String name,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		
		int count = (int) categoryService.count();
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(3); // 3 phần tử trên cùng 1 trang
		
		Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by("categoryId")); // sắp xếp theo trường Id
		Page<Category> resultPage = null;
		
		
	
		if(StringUtils.hasText(name)) {
			resultPage = categoryService.findByCategorynameContaining(name,pageable);
			model.addAttribute("name",name);
		} else {
			resultPage = categoryService.findAll(pageable);
		}
		
		// trả về tổng số các trang được phân trang
		int totalPages = resultPage.getTotalPages();
		if (totalPages > 0) {
			int start = Math.max(1, currentPage-2);
			int end = Math.min(currentPage+2, totalPages);
			
			if(totalPages > count) {
				if (end == totalPages) start = end -count;
				else if (start == 1) end = start + count;
			}
			List<Integer> pageNumbers = IntStream.rangeClosed(start, end)
					.boxed()
					.collect(Collectors.toList());
			
			model.addAttribute("pageNumbers",pageNumbers);
		}
		
		model.addAttribute("categoryPage",resultPage);
		
		return "admin/categories/searchpaginated";
	}
	

}

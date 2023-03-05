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

import vn.iotstar.Entity.Video;
import vn.iotstar.Model.VideoModel;
import vn.iotstar.Service.IVideoService;

@Controller

@RequestMapping("admin/videos")

public class VideoController {

	@Autowired

	IVideoService videoService;

	@RequestMapping("")
	public String list(ModelMap model) {

		// gọi hàm findAll() trong service

		List<Video> list = videoService.findAll();

		// chuyển dữ liệu từ list lên biến videos

		model.addAttribute("videos", list);

		return "admin/videos/list";

	}

	@GetMapping("add")
	public String Add(ModelMap model) {
		VideoModel cate = new VideoModel();
		cate.setIsEdit(false);
		model.addAttribute("video", cate);
		return "admin/videos/addOrEdit";
	}

	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("Video") VideoModel cate,
			BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("admin/videos/addOrEdit");
		}
		Video entity = new Video();
		// copy từ Model sang entity
		BeanUtils.copyProperties(cate, entity);

		videoService.save(entity);

		String message = "";
		if (cate.getIsEdit() == true) {
			message = "Video đã được cập nhật";
		} else {
			message = "Video đã được thêm thành công";
		}
		model.addAttribute("message", message);
		return new ModelAndView("forward:/admin/videos", model);
	}
	
	@GetMapping("edit/{videoId}")
	public ModelAndView edit(ModelMap model, 
			@PathVariable("videoId") String videoId) {
		
		Optional<Video> opt = videoService.findById(videoId);
		
		VideoModel cate = new VideoModel();
		if(opt.isPresent()) {
			Video entity = opt.get();
			BeanUtils.copyProperties(entity, cate); // copy từ entity sang model
			cate.setIsEdit(true); 
			model.addAttribute("video",cate);
			return new ModelAndView("admin/videos/addOrEdit",model);
		}
		// Trả về thông báo
		model.addAttribute("message","Video không tồn tại");
		return new ModelAndView("forward:/admin/videos",model);
	}
	
	@GetMapping("delete/{videoId}")
	public ModelAndView delete(ModelMap model, 
			@PathVariable("VideoId") String videoId) {
		videoService.deleteById(videoId);
		
		model.addAttribute("message","Video đã được xóa thành công");
		
		// redirect: chuyển nội dung vào trang mới
		// forward: nhúng nội dung vào một trang nào đó
		return new ModelAndView("redirect:/admin/videos",model);
	}
	
	// "name" từ phần views truyền xuống
	// required=false: không nhập hiện ra list ban đầu
	@GetMapping("search")
	public String search(ModelMap model, @RequestParam(name="name",required=false) String name) {
		List<Video> list = null;
		if(StringUtils.hasText(name)) {
			list = videoService.findByTitleContaining(name);
		} else {
			list = videoService.findAll();
		}
		model.addAttribute("videos",list);
		
		return "admin/videos/search";
	}
	
	// size: số phần tử được hiện thị trên 1 trang
	@GetMapping("searchpaginated")
	public String search(ModelMap model, 
			@RequestParam(name="name",required=false) String name,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		
		int count = (int) videoService.count();
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(3); // 3 phần tử trên cùng 1 trang
		
		Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by("videoId")); // sắp xếp theo trường Id
		Page<Video> resultPage = null;
		
		
	
		if(StringUtils.hasText(name)) {
			resultPage = videoService.findByTitleContaining(name,pageable);
			model.addAttribute("name",name);
		} else {
			resultPage = videoService.findAll(pageable);
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
		
		model.addAttribute("videoPage",resultPage);
		
		return "admin/videos/searchpaginated";
	}
	

}

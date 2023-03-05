package vn.iotstar.Service.Impl;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Example;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;

import org.springframework.util.StringUtils;

import vn.iotstar.Entity.Category;
import vn.iotstar.Repository.CategoryRepository;
import vn.iotstar.Service.ICategoryService;



//khai báo service

@Service

public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	//source -> Generate Constructor using Field, xóa super()

	public CategoryServiceImpl(CategoryRepository categoryRepository) {

		this.categoryRepository = categoryRepository;

	}

	@Override

	public <S extends Category> S save(S entity) {

		if (entity.getCategoryId() == null) {

			return categoryRepository.save(entity);

		} else {

			Optional<Category> opt = findById(entity.getCategoryId());

			if (opt.isPresent()) {

				if (StringUtils.isEmpty(entity.getCategoryname())) {

					entity.setCategoryname(opt.get().getCategoryname());

				} else {

					//lấy lại images cũ

					entity.setCategoryname(entity.getCategoryname());

				}

			}

			return categoryRepository.save(entity);

		}

	}

	@Override

	public List<Category> findAll() {

		return categoryRepository.findAll();

	}

	@Override

	public Page<Category> findAll(Pageable pageable) {

		return categoryRepository.findAll(pageable);

	}

	@Override

	public List<Category> findAll(Sort sort) {

		return categoryRepository.findAll(sort);

	}

	@Override

	public List<Category> findAllById(Iterable<Long> ids) {

		return categoryRepository.findAllById(ids);

	}

	@Override

	public Optional<Category> findById(Long id) {

		return categoryRepository.findById(id);

	}

	public <S extends Category> Optional<S> findOne(Example<S> example) {

		return categoryRepository.findOne(example);

	}

	@Override

	public long count() {

		return categoryRepository.count();

	}

	@Override

	public void deleteById(Long id) {

		categoryRepository.deleteById(id);

	}

	@Override

	public void delete(Category entity) {

		categoryRepository.delete(entity);

	}

	@Override

	public void deleteAll() {

		categoryRepository.deleteAll();

	}
	
	
	@Override

	public List<Category> findByCategorynameContaining(String name) {

		return categoryRepository.findByCategorynameContaining(name);

	}

	@Override

	public Page<Category> findByCategorynameContaining(String name, Pageable pageable) {

		return categoryRepository.findByCategorynameContaining(name, pageable);

	}

}
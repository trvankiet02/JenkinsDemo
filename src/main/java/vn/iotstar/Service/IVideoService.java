package vn.iotstar.Service;

import java.util.List;

import java.util.Optional;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Sort;

import vn.iotstar.Entity.Video;


public interface IVideoService {

	void deleteAll();

	void delete(Video entity);

	void deleteById(String id);

	long count();

	Optional<Video> findById(String id);

	List<Video> findAllById(Iterable<String> ids);

	List<Video> findAll(Sort sort);

	Page<Video> findAll(Pageable pageable);

	List<Video> findAll();

	<S extends Video> S save(S entity);

	List<Video> findByTitleContaining(String name);

	Page<Video> findByTitleContaining(String name, Pageable pageable);

}

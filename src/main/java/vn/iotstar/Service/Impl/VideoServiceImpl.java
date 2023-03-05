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

import vn.iotstar.Entity.Video;
import vn.iotstar.Repository.VideoRepository;
import vn.iotstar.Service.IVideoService;

@Service
public class VideoServiceImpl implements IVideoService {

	@Autowired

	VideoRepository videoRepository;

	//source -> Generate Constructor using Field, xóa super()

	public VideoServiceImpl(VideoRepository videoRepository) {

		this.videoRepository = videoRepository;

	}

	
	// source -> Generate Delegate 
	@Override

	public <S extends Video> S save(S entity) {

		if (entity.getVideoId() == null) {

			return videoRepository.save(entity);

		} else {

			//kiểm tra Images tồn tại

			Optional<Video> opt = findById(entity.getVideoId());

			if (opt.isPresent()) {

				if (StringUtils.isEmpty(entity.getPoster())) {

					entity.setPoster(opt.get().getPoster());

				} else {

					//lấy lại images cũ

					entity.setPoster(entity.getPoster());

				}

			}

			return videoRepository.save(entity);

		}

	}

	@Override

	public List<Video> findAll() {

		return videoRepository.findAll();

	}

	@Override

	public Page<Video> findAll(Pageable pageable) {

		return videoRepository.findAll(pageable);

	}

	@Override

	public List<Video> findAll(Sort sort) {

		return videoRepository.findAll(sort);

	}

	@Override

	public List<Video> findAllById(Iterable<String> ids) {

		return videoRepository.findAllById(ids);

	}

	@Override

	public Optional<Video> findById(String id) {

		return videoRepository.findById(id);

	}

	public <S extends Video> Optional<S> findOne(Example<S> example) {

		return videoRepository.findOne(example);

	}

	@Override

	public long count() {

		return videoRepository.count();

	}

	@Override

	public void deleteById(String id) {

		videoRepository.deleteById(id);

	}

	@Override

	public void delete(Video entity) {

		videoRepository.delete(entity);

	}

	@Override

	public void deleteAll() {

		videoRepository.deleteAll();

	}

	@Override

	public List<Video> findByTitleContaining(String name) {

		return videoRepository.findByTitleContaining(name);

	}

	@Override

	public Page<Video> findByTitleContaining(String name, Pageable pageable) {

		return videoRepository.findByTitleContaining(name, pageable);

	}

}

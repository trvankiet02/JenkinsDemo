package vn.iotstar.Repository;

import java.util.List;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import vn.iotstar.Entity.Video;



@Repository
public interface VideoRepository extends JpaRepository<Video, String> {

	//Tìm Kiếm theo nội dung tên

	List<Video> findByTitleContaining(String name);

	//Tìm kiếm và Phân trang

	Page<Video> findByTitleContaining(String name,Pageable pageable);

}
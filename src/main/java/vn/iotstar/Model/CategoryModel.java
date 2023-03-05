package vn.iotstar.Model;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import org.springframework.web.multipart.MultipartFile;

import lombok.*;

@Data

@AllArgsConstructor

@NoArgsConstructor

public class CategoryModel {

	private Long categoryId;

	private String categorycode;

	@NotEmpty
	@Length(min = 5)
	private String categoryname;

	private MultipartFile imageFile; // lưu hình

	private String images;

	private boolean status;

	private Boolean isEdit = false;

	public Boolean getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(Boolean isEdit) {
		this.isEdit = isEdit;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategorycode() {
		return categorycode;
	}

	public void setCategorycode(String categorycode) {
		this.categorycode = categorycode;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	

}
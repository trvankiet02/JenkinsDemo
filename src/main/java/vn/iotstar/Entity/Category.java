package vn.iotstar.Entity;

import java.io.Serializable;

import javax.persistence.*;



import lombok.*;

import java.util.Set;

@Data

@NoArgsConstructor

@AllArgsConstructor



@Entity

@Table(name="Category")

@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category implements Serializable  {
	private static final long serialVersionUID = 1L;

	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name="CategoryId")

	private Long categoryId;



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



	public Set<Video> getVideos() {
		return videos;
	}



	public void setVideos(Set<Video> videos) {
		this.videos = videos;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Column(name="Categorycode")

	private String categorycode;



	@Column(name="Categoryname", length = 200, columnDefinition = "nvarchar(200) not null")
	private String categoryname;
	

	@Column(name="Images")
	private String images;

	@Column(name="Status")
	private boolean status;



	//bi-directional many-to-one association to Video
	@OneToMany(mappedBy="category", cascade = CascadeType.ALL )
	private Set<Video> videos;



}

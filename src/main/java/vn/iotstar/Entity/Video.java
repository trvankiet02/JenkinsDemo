package vn.iotstar.Entity;

import java.io.Serializable;





import javax.persistence.*;



import lombok.*;



@Data

@NoArgsConstructor

@AllArgsConstructor

@Entity

@Table(name="Videos")

@NamedQuery(name="Video.findAll", query="SELECT v FROM Video v")

public class Video implements Serializable {
	
	private static final long serialVersionUID = 1L;



	public String getVideoId() {
		return videoId;
	}



	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}



	public boolean isActive() {
		return active;
	}



	public void setActive(boolean active) {
		this.active = active;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getPoster() {
		return poster;
	}



	public void setPoster(String poster) {
		this.poster = poster;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public int getViews() {
		return views;
	}



	public void setViews(int views) {
		this.views = views;
	}



	public Category getCategory() {
		return category;
	}



	public void setCategory(Category category) {
		this.category = category;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Id

	@Column(name="VideoId")

	private String videoId;



	@Column(name="Active")

	private boolean active;



	@Column(name="Description", columnDefinition = "nvarchar(MAX) not null")

	private String description;



	@Column(name="Poster")

	private String poster;



	@Column(name="Title", columnDefinition = "nvarchar(255) not null")

	private String title;



	@Column(name="Views")

	private int views;
	

	//bi-directional many-to-one association to Category

	@ManyToOne

	@JoinColumn(name="CategoryId")

	private Category category;

}

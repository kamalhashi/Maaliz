package com.hashi.rest.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.hashi.rest.enums.ImageStatus;


@Entity
@Table(name = "IMAGES")
public class Images  implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IMAGE_ID", nullable = false, unique = true)
	private Long imageId;
	
	@Column(name = "IMAGE_NAME")
	private String imageName;
	
	@Column(name = "IMAGE_INDEX")
	private Integer imageIndex;
	
	


	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public Long getImageId() {
		return imageId;
	}
	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}
	public Integer getImageIndex() {
		return imageIndex;
	}
	public void setImageIndex(Integer imageIndex) {
		this.imageIndex = imageIndex;
	}
}

package com.hashi.rest.domain;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;
import org.springframework.cache.annotation.Cacheable;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Immutable
@Table(name = "CATEGORIES")
public class CategoryEnglish implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "CATEGORY_ID", nullable = false, unique = true)
	private Long categoryId;
	@Column(name = "CATEGORY_NAME", nullable = true, unique = false)
	private String categoryName;
	@Column(name = "CATEGORY_DESCRIPTION", nullable = true, unique = false)
	private String categoryDescription;
	@Column(name = "LFT")
	private Integer lft;
	@Column(name = "RGT")
	private Integer rgt;
	@Column(name = "DEPTH")
	private Integer depth;
	

	
	public Integer getLft() {
		return lft;
	}

	public void setLft(Integer lft) {
		this.lft = lft;
	}

	public Integer getRgt() {
		return rgt;
	}

	public void setRgt(Integer rgt) {
		this.rgt = rgt;
	}

	public Integer getDepth() {
		return depth;
	}

	public void setDepth(Integer depth) {
		this.depth = depth;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

}

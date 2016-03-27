package com.hashi.rest.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.hashi.rest.enums.ImageStatus;
import com.hashi.rest.enums.LanguageType;
import com.hashi.rest.enums.LiveType;
import com.hashi.rest.enums.ProductPriority;

import javax.persistence.NamedAttributeNode;


@Entity
@Table(name = "PRODUCTS")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedEntityGraphs({
    @NamedEntityGraph(
        name = "productWithImagesAndUser",
        attributeNodes = {
            @NamedAttributeNode("images"),
            @NamedAttributeNode("user")
        }),
    
        @NamedEntityGraph(
            name = "productWithImages",
            attributeNodes = {
                @NamedAttributeNode("images"),
            }),
            
            @NamedEntityGraph(
                    name = "productWithProfiles",
                    attributeNodes = {
                        @NamedAttributeNode("profileProduct"),
                        @NamedAttributeNode("user")
            })
    })


//json stuff
@JsonInclude(Include.NON_EMPTY) 
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes(value = {
    @JsonSubTypes.Type(value = MotorCar.class),
    @JsonSubTypes.Type(value = MotorCycles.class),
    @JsonSubTypes.Type(value = MotorCarParts.class),
    @JsonSubTypes.Type(value = MotorBoats.class),
    @JsonSubTypes.Type(value = MotorHeavyVehicle.class),
    @JsonSubTypes.Type(value = MotorCycles.class),
    @JsonSubTypes.Type(value = PropertySaleRes.class),
    @JsonSubTypes.Type(value = PropertySaleComm.class),
    @JsonSubTypes.Type(value = PropertySaleLand.class),
    @JsonSubTypes.Type(value = PropertySaleUnits.class),
    @JsonSubTypes.Type(value = PropertyRentRes.class),
    @JsonSubTypes.Type(value = PropertyRentComm.class),
    @JsonSubTypes.Type(value = PropertyRentRoom.class),
    @JsonSubTypes.Type(value = PropertyRentHotel.class),
    @JsonSubTypes.Type(value = PropertyRentShortMonthly.class),
    @JsonSubTypes.Type(value = ClassifiedBabyItems.class),
    @JsonSubTypes.Type(value = ClassifiedBooks.class),
    @JsonSubTypes.Type(value = ClassifiedBusinessIndustrial.class),
    @JsonSubTypes.Type(value = ClassifiedCameras.class),
    @JsonSubTypes.Type(value = ClassifiedClothing.class),
    @JsonSubTypes.Type(value = ClassifiedCollectibles.class),
    @JsonSubTypes.Type(value = ClassifiedComputers.class),
    @JsonSubTypes.Type(value = ClassifiedNetworking.class),
    @JsonSubTypes.Type(value = ClassifiedDvdMovies.class),
    @JsonSubTypes.Type(value = ClassifiedElectronics.class),
    @JsonSubTypes.Type(value = ClassifiedFurniture.class),
    @JsonSubTypes.Type(value = ClassifiedGaming.class),
    @JsonSubTypes.Type(value = ClassifiedHomeAppliances.class),
    @JsonSubTypes.Type(value = ClassifiedJewelry.class),
    @JsonSubTypes.Type(value = ClassifiedLostFound.class),
    @JsonSubTypes.Type(value = ClassifiedMobile.class),
    @JsonSubTypes.Type(value = ClassifiedSportsEquipment.class),
    @JsonSubTypes.Type(value = ClassifiedToys.class),
    @JsonSubTypes.Type(value = JobEntity.class)

})

public class Product implements Serializable {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRODUCT_ID", nullable = false, unique = true)
	private Long productId;
	
	@Column(name = "PRODUCT_TITLE", unique = false, nullable = false)
	private String productTitle;
	
	@Column(name = "PRODUCT_PRICE", unique = false, nullable = false)
	private Double productPrice;
    
    @Column(name = "PRODUCT_DESCRIPTION", unique = false, nullable = false)
	private String productDescription;
    
    @Column(name = "COVER_PHOTO", unique = false, nullable = false)
    private String coverPhoto; 
    
    @Column(name = "LOGO_PHOTO", unique = false, nullable = false)
    private String logoPhoto; 
    
    
	@Column(name = "LIVE_AD", unique = false, nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private LiveType liveType;
	
	@Column(name = "LANGUAGE", unique = false, nullable = false)
	@Enumerated(EnumType.STRING)
	private LanguageType productLanguage;
	

	@Column(name = "AD_PRIORITY", unique = false, nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private ProductPriority productPriority;
	
	@Column(name = "IMAGE_STATUS")
	@Enumerated(EnumType.ORDINAL)
	private ImageStatus imageStatus;
	
	
	/* a product can belong to one image of row or more */
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, orphanRemoval=true )
	@JoinColumn(name = "PRODUCT_ID", referencedColumnName="PRODUCT_ID")
	private List<Images> images;


	/* one product category belongs or can have many products */
	@ManyToOne(cascade = { CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinColumn(name = "CATEGORY_ID")
	private CategoryEnglish categoriesEntity;
    
	@ManyToOne(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
  	private User user;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade=CascadeType.ALL)
	private List<ProfileProduct> profileProduct;
	
	
	/*
	 * The PrimaryKeyJoinColumn annotation does say that the primary key of the
	 * entity is used as the foreign key value to the associated entity.
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@PrimaryKeyJoinColumn
	private Locations location;
	
    @JsonInclude(JsonInclude.Include.ALWAYS)
	@Version
	@Column(name = "version")
	private Long version;

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED", nullable = false)
	private Date created;

	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATED", nullable = false)
	private Date updated;

	@Temporal(TemporalType.DATE)
	@Column(name = "EXPIRY_DATE", nullable = false)
	private Date expiryDate;

	@PrePersist
	protected void onCreate() {
		updated = created = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updated = new Date();
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public Locations getLocation() {
		return location;
	}

	public void setLocation(Locations location) {
		this.location = location;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public CategoryEnglish getCategoriesEntity() {
		return categoriesEntity;
	}

	public void setCategoriesEntity(CategoryEnglish categoriesEntity) {
		this.categoriesEntity = categoriesEntity;
	}

	public LiveType getLiveType() {
		return liveType;
	}

	public void setLiveType(LiveType liveType) {
		this.liveType = liveType;
	}

	public ProductPriority getProductPriority() {
		return productPriority;
	}

	public void setProductPriority(ProductPriority productPriority) {
		this.productPriority = productPriority;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getCoverPhoto() {
		return coverPhoto;
	}

	public void setCoverPhoto(String coverPhoto) {
		this.coverPhoto = coverPhoto;
	}

	public List<Images> getImages() {
		return images;
	}

	public void setImages(List<Images> images) {
		this.images = images;
	}
	
	public LanguageType getProductLanguage() {
		return productLanguage;
	}

	public void setProductLanguage(LanguageType productLanguage) {
		this.productLanguage = productLanguage;
	}


	public ImageStatus getImageStatus() {
		return imageStatus;
	}

	public void setImageStatus(ImageStatus imageStatus) {
		this.imageStatus = imageStatus;
	}

	public List<ProfileProduct> getProfileProduct() {
		return profileProduct;
	}

	public void setProfileProduct(List<ProfileProduct> profileProduct) {
		this.profileProduct = profileProduct;
	}

	public String getLogoPhoto() {
		return logoPhoto;
	}

	public void setLogoPhoto(String logoPhoto) {
		this.logoPhoto = logoPhoto;
	}
}

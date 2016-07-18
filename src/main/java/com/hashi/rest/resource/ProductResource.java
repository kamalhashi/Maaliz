package com.hashi.rest.resource;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hashi.config.ApplicationConfig;
import com.hashi.repository.CarModelRepository;
import com.hashi.rest.domain.CategoryEnglish;
import com.hashi.rest.domain.Images;
import com.hashi.rest.domain.Locations;
import com.hashi.rest.domain.Product;
import com.hashi.rest.enums.CategoryType;
import com.hashi.rest.enums.ImageStatus;
import com.hashi.rest.enums.LanguageType;
import com.hashi.rest.enums.ProductPriority;
import com.hashi.rest.service.CategoryService;
import com.hashi.rest.service.ProductService;
import com.hashi.rest.service.UserService;
import com.hashi.search.vo.AdvanceSearch;
import com.hashi.util.AmazonS3Service;
import com.hashi.util.MultipartToFile;

@RestController
@RequestMapping("/products")
public class ProductResource {
	private final static Logger logger = Logger.getLogger(ProductResource.class
			.getName());

	private ProductService productService;
	private CategoryService categoryService;
	private UserService userService;
	private AmazonS3Service amazonS3Service;
	private ApplicationConfig config;

	@Autowired
	public ProductResource(UserService userService,
			ProductService productService, CategoryService categoryService,
			MessageSource messageSource,
			AmazonS3Service amazonS3Service,
			CarModelRepository carModelRepository,
			ApplicationConfig config) {
		this.productService = productService;
		this.categoryService = categoryService;
		this.userService = userService;
		this.amazonS3Service = amazonS3Service;
		this.config= config;
	}

	/**
	 * List all products
	 * 
	 * @param <T>
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@RequestMapping(value = "/{categoryId}/{userId}", method = RequestMethod.POST,consumes = {"multipart/form-data"}, produces="application/json")
	@PreAuthorize("permitAll")
	public ResponseEntity<Long> saveProduct(
			@RequestParam(value= "product"  ,required=true) String  productJson, 
			@RequestParam(value= "location"  ,required=true) String  locationJson,
			@RequestParam(value= "imageNames"  ,required=true) String  imageNames,
			@RequestParam(value= "telephone"  ,required=true) String telephone,
			@PathVariable Long categoryId,
			@PathVariable Long userId,
			@RequestPart(value= "file"  ,required=true) MultipartFile[] files,
			@RequestPart(value="logoImage", required=false)MultipartFile logoPhoto) throws JsonParseException, JsonMappingException, IOException {

		Locations location = new ObjectMapper().readValue(locationJson, Locations.class);

		Product productEntity = new ObjectMapper().readValue(productJson, Product.class);
		List<Images> imagesList= new ObjectMapper().readValue(imageNames, new TypeReference<List<Images>>(){});

		
		// find the category and then associate the category to product for
		productEntity.setUser(userService.findByUserId(userId));
		productEntity.getUser().setTelephone(telephone);

		productEntity.setLocation(location);
		productEntity.setImages(imagesList);
		location.setProduct(productEntity);

		productEntity.setCategoriesEntity((CategoryEnglish) categoryService
				.findCategoryByCategoryId(categoryId, LanguageType.en_US));
		Product savedProduct = productService.saveProduct(productEntity);
			
		if (files.length > 0) {
			amazonS3Service.asyncUploadS3Files(
					MultipartToFile.multipartsToFiles(files),
					savedProduct.getProductId(),
					config.getBucketProductImages());
		}
		if (logoPhoto != null && !logoPhoto.isEmpty()) {
			amazonS3Service.asyncUploadS3File(
					MultipartToFile.multipartToFile(logoPhoto),
					savedProduct.getProductId(), config.getBucketProductLogo());
		} 
		return new ResponseEntity<Long>(savedProduct.getProductId(), HttpStatus.OK);

	}


	/**
	 * List all products
	 * 
	 * @param <T>
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST,consumes = {"multipart/form-data"}, produces="application/json")
	@PreAuthorize("permitAll")
	public ResponseEntity<Long> updateProduct(@RequestParam(value= "product"  ,required=true) String  productJson,
			@RequestParam(value= "telephone"  ,required=true) String telephone,
			@RequestParam(value= "userId"  ,required=true) Long userId,
			@RequestPart(value= "file"  ,required=true) MultipartFile[] files,
			@RequestPart(value="logoImage", required=false) MultipartFile logoPhoto) throws JsonParseException, JsonMappingException, IOException {
		    
			Product productEntity = new ObjectMapper().readValue(productJson, Product.class);
		    productEntity.setUser(userService.findByUserId(userId));
			productEntity.getUser().setTelephone(telephone);
			Long productId = productService.updateProduct(productEntity);
			
			if(files.length > 0){
				//first delete the product images related from amazon
				amazonS3Service.asyncUploadS3Files(MultipartToFile.multipartsToFiles(files), productId,  config.getBucketProductImages());			
			}
			
			//check of the logoPhoto and delete if its empty 
			if (logoPhoto != null && !logoPhoto.isEmpty()) {
				amazonS3Service.asyncUploadS3File(MultipartToFile.multipartToFile(logoPhoto), productId, config.getBucketProductLogo());
			} else { //delete if its empty 
				amazonS3Service.asyncDeleteS3Directory(productId, config.getBucketProductLogo());
			}
			ResponseEntity<Long> responseEntity = new ResponseEntity<Long>(productId, HttpStatus.OK);
			return responseEntity;
	}




	/**
	 * List all categories 
	 * @return
	 */
	
	@RequestMapping(value="/featured", method=RequestMethod.GET)
	public ResponseEntity<?>  getFeaturedProducts() {

		List<Product> featuredProducts = productService.getFeaturedProducts();
		ResponseEntity<?> responseEntity =
				new ResponseEntity(featuredProducts, HttpStatus.OK);
		return responseEntity;
	}



	/**
	 * Search products by {categoryId, cityname, searchText}
	 * 
	 * @param language
	 * @param categoryId
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("permitAll")
	public ResponseEntity<Page<Product>> basicSearch(
			@RequestParam(value ="categoryId" ,required=false) Long categoryId,
			@RequestParam(value ="cityId"   ,required=false) Long cityId,
			@RequestParam(value="searchText"  ,required=false) String searchText,
			@RequestParam(value= "pageIndex"  ,required=false) Integer pageIndex,
			@RequestParam(value= "sortColumn"  ,required=false) String sortColumn,
			@RequestParam(value= "sortDirection"  ,required=false) String sortDirection) {
		Page<Product> products = productService.basicSearch(categoryId, cityId, searchText, pageIndex, sortColumn, sortDirection);
		return new ResponseEntity <Page<Product>> (products, HttpStatus.OK);

	}

	/**
	 * get product by {categoryId}, for details page
	 * 
	 * @param language
	 * @param categoryId
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value="/product/details/{productId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("permitAll")
	public ResponseEntity <Product> findProductWithUserAndImagesByProductId(
			@PathVariable  Long productId) {

		Product product = productService.findProductWithUserAndImagesByProductId(productId);
		return new ResponseEntity <Product> (product, HttpStatus.OK);
	}


	/**
	 * get product without user for modifying, editing page {categoryId}
	 * 
	 * @param language
	 * @param categoryId
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value="/product/edit/{productId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("permitAll")
	public ResponseEntity <Product> findProductWithUserByProductId(
			@PathVariable  Long productId) {
		Product product = productService.findProductWithUserByProductId(productId);
		return new ResponseEntity <Product> (product, HttpStatus.OK);
	}


	/**
	 * get product by {categoryId}
	 * 
	 * @param language
	 * @param categoryId
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value="/count/{categoryId}/{cityId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("permitAll")
	public ResponseEntity <Long> countByCategoryId(
			@PathVariable  Long categoryId,
			@PathVariable  Long cityId) {

		long count  = productService.countProductsByCategoryId(categoryId, cityId);
		return new ResponseEntity <Long> (count, HttpStatus.OK);

	}

	/**
	 * count top root categories  by {categoryId}
	 * 
	 * @param language
	 * @param categoryId
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value="/count/byDepth/{categoryId}/{cityId}/{language}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("permitAll")
	public ResponseEntity<List<Object>>  countProductsByDepth(
			@PathVariable  Long categoryId,
			@PathVariable  Long cityId,
			@PathVariable("language") LanguageType language) {
		if(categoryId <= CategoryType.ALL_CATEGORIES.getValue()){
			List<Object> countedObject  = productService.countProductsByDepth(1L, 1, language);
			return new ResponseEntity<List<Object>>  (countedObject, HttpStatus.OK);
		}else{
			List<Object> countedObject  = productService.countProductsByDepth(categoryId, 2, language);
			return new ResponseEntity<List<Object>>  (countedObject, HttpStatus.OK);
		}
	}



	/**
	 * List sub categories that belong to a particular category
	 * 
	 * @param language
	 * @param categoryId
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value= "/advanceSearch" , method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("permitAll")
	public ResponseEntity<Page<Product>> advanceSearch(
			@RequestParam(value ="categoryId" ,required=false) Long categoryId,
			@RequestParam(value ="cityId"   ,required=false) Long cityId,
			@RequestParam(value="searchText"  ,required=false) String searchText,
			@RequestParam(value= "pageIndex"  ,required=false) Integer pageIndex,
			@RequestBody (required=false)  AdvanceSearch advanceSearch,
			@RequestParam(value= "sortColumn"  ,required=false) String sortColumn,
			@RequestParam(value= "sortDirection"  ,required=false) String sortDirection) {
		Page<Product> products = productService.advanceSearch(categoryId, cityId, searchText, pageIndex, advanceSearch, sortColumn, sortDirection);
		return new ResponseEntity <Page<Product>> (products, HttpStatus.OK);
	}
	
	
	/**
	 * get all products posted by  user
	 * 
	 * @param language
	 * @param userId
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value="/user/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("permitAll")
	public ResponseEntity<List<Product>> findProductsByUserId(
			@PathVariable  Long userId) {
		List<Product> product = productService.findProductsByUserId(userId);
		return new ResponseEntity<List<Product>> (product, HttpStatus.OK);

	}

	@PreAuthorize("permitAll")
	@RequestMapping(value="/{productId}", method = RequestMethod.DELETE)
	public @ResponseBody void deleteProduct(
			@PathVariable  Long productId) {
		productService.deleteProduct(productId);
		amazonS3Service.asyncDeleteS3Directory(productId, config.getBucketProductImages());
		amazonS3Service.asyncDeleteS3Directory(productId, config.getBucketProductLogo());
	}
	
	
	/****************The following method is for the admin *****************************/
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/admin/product/{productId}/imageStatus/{imageStatus}", method = RequestMethod.PUT)
	public @ResponseBody void updateImageStatusForProduct(
			@PathVariable  Long productId, @PathVariable  ImageStatus imageStatus) {	
		Product product =productService.findProductWithUserByProductId(productId);
		productService.updateImageStatusForProduct(product, imageStatus);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/admin/product/{productId}/productPriority/{productPriority}", method = RequestMethod.PUT)
	public @ResponseBody void updatePriorityForProduct(
			@PathVariable  Long productId, @PathVariable  ProductPriority productPriority) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		Product product =productService.findProductWithUserByProductId(productId);
		productService.updatePriorityForProduct(product, productPriority);
	}


}


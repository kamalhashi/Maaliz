package xaashi;


import java.io.IOException;















import org.jclouds.s3.domain.AccessControlList.EmailAddressGrantee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.hashi.UiApplication;
import com.hashi.repository.UserRepository;
import com.hashi.rest.domain.User;
import com.hashi.rest.domain.UserRole;
import com.hashi.rest.domain.VerificationToken;
import com.hashi.rest.enums.MailType;
import com.hashi.rest.enums.Role;
import com.hashi.rest.service.EmailService;
import com.hashi.rest.service.ProfileProductService;
import com.hashi.rest.vo.EmailAdPendingVO;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UiApplication.class)
@WebAppConfiguration

public class CategoriesRepositoryTest {
/*
	@Autowired
	CategoryEnglishRepository categoryRepository;
	@Autowired
	ProductService productService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	UserRepository userRepository;
	*/
	@Autowired
	ProfileProductService profileProductService;
	
	@Autowired
	EmailService emailService;
	@Autowired
	private UserRepository userRepository;


	@Test
	public void testEmail(){
	    EmailAdPendingVO emailAdPending = new EmailAdPendingVO();
		emailService.sendEmailAdPending(userRepository.findOne(822L));
	}
	public void testName() throws IOException {
		/*
		System.out.println(productService.countProductsByCategoryId(21L, 0L));
		System.out.println(productService.countProductsByCategoryId(21L, 0L));
		System.out.println(productService.countProductsByCategoryId(21L, 0L));*/
		
		//System.out.println(productService.findProductWithUserByProductId(142L));
		//System.out.println(productService.findProductWithUserByProductId(142L));
		//System.out.println(productService.countProductsByCategoryId(142L, 0L));
		/*List<Object> list = productService.countProductsByDepth(4L, 2);
		for (int i=0; i<list.size(); i++){
			Object[] row = (Object[]) list.get(i);
			System.out.println("Element "+i+Arrays.toString(row));
		}*/
		
		//productService.deleteProduct(306L);
		
		//userService.updateUserByColumnName(493L, "firstname", "Huhhhhahhah");
		
		//amazonS3Service.deleteS3Directory(123L, "xaashi-somalia.other-image");
		
		
		User user= new User();
		
		
		user.setFirstname("Solution");
		user.setSurname("Faster you cut");
		user.setEmail("Solution");
		user.setPassword("888");
		user.setEnabled(true);
		
		UserRole userRole= new UserRole();
		userRole.setRole(Role.ROLE_USER);
		userRole.setUser(user);
		
		VerificationToken token= new VerificationToken(user , MailType.EMAIL_REGISTRATION);

		
		user.setUserRole(userRole);
		user.setVerificationTokens(token);
		
		
		//userRepository.saveAndFlush(user);
		
		//Product p = new Product();
		
		System.out.println("Done:");
	}		
}


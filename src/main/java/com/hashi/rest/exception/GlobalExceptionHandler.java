package com.hashi.rest.exception;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger logger = Logger
			.getLogger(GlobalExceptionHandler.class.getName());

	@ExceptionHandler(SQLException.class)
	public void handleSQLException(HttpServletRequest request, SQLException ex) {
		logger.log(Level.WARNING, ex.getMessage());
	}

	@ExceptionHandler(DuplicateUserException.class)
	@ResponseStatus(HttpStatus.FOUND)
	@ResponseBody
	public Map<String, Object> handleEmailExistException(
			DuplicateUserException emailException, HttpServletResponse response)
					throws IOException {
		HashMap<String, Object> result = new HashMap<>();
		result.put("error", true);
		result.put("message", emailException.getMessage());
		logger.log(Level.WARNING, emailException.getMessage());
		return result;
	}

	@ExceptionHandler(VerificationNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public Map<String, Object> handleVerificationExpiredException(
			VerificationNotFoundException verificationException, HttpServletResponse response)
					throws IOException {
		HashMap<String, Object> result = new HashMap<>();
		result.put("error", true);
		result.put("message", verificationException.getMessage());
		logger.log(Level.WARNING, verificationException.getMessage());
		return result;
	}
	
	
	@ExceptionHandler(AlreadyVerifiedException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	//after registration user has been verified, again cannot be verified
	public Map<String, Object> handleAlreadyVerifiedException(
			AlreadyVerifiedException verifiedException, HttpServletResponse response)
					throws IOException {
		HashMap<String, Object> result = new HashMap<>();
		result.put("error", true);
		result.put("message", verifiedException.getMessage());
		logger.log(Level.WARNING, verifiedException.getMessage());
		return result;
	}
	
	@ExceptionHandler(TokenHasExpiredException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	//after registration user has been verified, again cannot be verified
	public Map<String, Object> handleTokenHasExpiredException(
			TokenHasExpiredException tokenHasExpiredException, HttpServletResponse response)
					throws IOException {
		HashMap<String, Object> result = new HashMap<>();
		result.put("error", true);
		result.put("message", tokenHasExpiredException.getMessage());
		logger.log(Level.WARNING, tokenHasExpiredException.getMessage());
		return result;
	}

	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public Map<String, Object> handleUserNotFoundException(
			UserNotFoundException userNotFoundException, HttpServletResponse response)
					throws IOException {
		HashMap<String, Object> result = new HashMap<>();
		result.put("error", true);
		result.put("message", userNotFoundException.getMessage());
		logger.log(Level.WARNING, userNotFoundException.getMessage());
		return result;
	}
	
	@ExceptionHandler(CustomMessagingException.class)
	@ResponseStatus(HttpStatus.FAILED_DEPENDENCY)
	@ResponseBody
	public Map<String, Object> handleCustomMessagingException(
			CustomMessagingException customMessagingException, HttpServletResponse response)
					throws IOException {
		HashMap<String, Object> result = new HashMap<>();
		result.put("error", true);
		result.put("message", customMessagingException.getMessage());
		logger.log(Level.WARNING, customMessagingException.getMessage());
		//or send email this is important because of email verification not sent
		return result;
	}
	
	
	@ExceptionHandler(EmailNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public Map<String, Object> handleEmailNoFoundException(
			EmailNotFoundException emailNotFoundException, HttpServletResponse response)
					throws IOException {
		HashMap<String, Object> result = new HashMap<>();
		result.put("error", true);
		result.put("message", emailNotFoundException.getMessage());
		logger.log(Level.WARNING, emailNotFoundException.getMessage());
		//or send email this is important because of email verification not sent
		return result;
	}
	
	@ExceptionHandler(CategoryNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public Map<String, Object> handleCategoryNotFoundException(
			CategoryNotFoundException categoryNotFoundException, HttpServletResponse response)
					throws IOException {
		HashMap<String, Object> result = new HashMap<>();
		result.put("error", true);
		result.put("message", categoryNotFoundException.getMessage());
		logger.log(Level.WARNING, categoryNotFoundException.getMessage());
		//or send email this is important because of email verification not sent
		return result;
	}
	
	
	@ExceptionHandler(ProductCreationException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public Map<String, Object> handleProductCreationException(
			ProductCreationException productCreationException, HttpServletResponse response)
					throws IOException {
		HashMap<String, Object> result = new HashMap<>();
		result.put("error", true);
		result.put("message", productCreationException.getMessage());
		logger.log(Level.WARNING, productCreationException.getMessage());
		return result;
	}
	
	@ExceptionHandler(ApplicantExistException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public Map<String, Object> handleApplicantExistException(
			ApplicantExistException applicantExistException, HttpServletResponse response)
					throws IOException {
		HashMap<String, Object> result = new HashMap<>();
		result.put("error", true);
		result.put("message", applicantExistException.getMessage());
		logger.log(Level.WARNING, applicantExistException.getMessage());
		return result;
	}

}

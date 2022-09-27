package com.stewartjumbe.rest.webservices.restfulwebservices.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.stewartjumbe.rest.webservices.restfulwebservices.user.UserNotFoundException;

@ControllerAdvice //this will now apply to all controllers
public class CustomisedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(Exception.class)//defining what exceptions i want to handle...everything
	public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request)throws Exception{

		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), 
				ex.getMessage(), request.getDescription(false));

		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);


	}

	@ExceptionHandler(UserNotFoundException.class)//defining what exceptions i want to handle...everything
	public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex, WebRequest request) throws Exception{

		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),ex.getMessage(), request.getDescription(false));

		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);


	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),

				getErrorMessages(ex), request.getDescription(false));



		return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
	}

	public String getErrorMessages(MethodArgumentNotValidException ex) {



		List<ObjectError> listOfErrors = ex.getAllErrors();

		String multiErrorMsg = "";
		String multiErrorMsg2 = "";
		String singleErrorMsg = "";
		for(int i=0; i<=listOfErrors.size()-1;i++) {

			System.out.println("size of list: " + listOfErrors.size() + "count=" +i);
			multiErrorMsg2 = multiErrorMsg;
			if(ex.getErrorCount() >1) {

				multiErrorMsg = "errors remaining, first error: " + listOfErrors.get(i).getDefaultMessage();
				


			}	else {

				//singleErrorMsg = "You have " + ex.getErrorCount() + " error remaining: "+ listOfErrors.get(i).getDefaultMessage();
			}	

		}
		return multiErrorMsg +" " +multiErrorMsg2 + " " +singleErrorMsg;}


}

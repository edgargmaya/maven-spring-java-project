package com.edgar.escuela.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest wr){
		
		ExceptionResponse exception = new ExceptionResponse();
		exception.setFecha(new Date());
		exception.setMensaje("Vaya!, esto si que es raro, ocurrió un error dificil de explicar");
		exception.setHttpCode("500");
		exception.setHttpError( HttpStatus.INTERNAL_SERVER_ERROR.name() );
		
		return new ResponseEntity<>(exception, HttpStatus.INTERNAL_SERVER_ERROR );
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ExceptionResponse exception = new ExceptionResponse();
		exception.setFecha(new Date());
					
		exception.setMensaje( "validation failed: " + ex.getBindingResult().getAllErrors().get(0).getDefaultMessage() );
		exception.setHttpCode("400");
		exception.setHttpError( HttpStatus.BAD_REQUEST.name() );
		
		return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST );
	}
	
	@ExceptionHandler(UserExistException.class)
	public final ResponseEntity<Object> handleUserExistException(Exception ex, WebRequest wr){
		
		ExceptionResponse exception = new ExceptionResponse();
		exception.setFecha(new Date());
		exception.setMensaje( ex.getMessage() );
		exception.setHttpCode("400");
		exception.setHttpError( HttpStatus.BAD_REQUEST.name() );
		
		return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST );
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleNotFoundExceptions(Exception ex, WebRequest wr){
		
		ExceptionResponse exception = new ExceptionResponse();
		exception.setFecha(new Date());
		exception.setMensaje( ex.getMessage() );
		exception.setHttpCode("404");
		exception.setHttpError( HttpStatus.NOT_FOUND.name() );
		
		return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND );
	}
	
}

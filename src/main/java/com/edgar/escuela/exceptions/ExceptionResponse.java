package com.edgar.escuela.exceptions;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExceptionResponse {
	
	
	private Date fecha;
	
	@JsonProperty("codigo http")
	private String httpCode;
	
	@JsonProperty("error http")
	private String httpError;
	
	private String mensaje;
	
	public ExceptionResponse(){
		
	}

	public ExceptionResponse(Date fecha, String mensaje, String httpCode, String httpError) {
		super();
		this.fecha = fecha;
		this.mensaje = mensaje;
		this.httpCode = httpCode;
		this.httpError = httpError;
	}

	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getHttpCode() {
		return httpCode;
	}
	public void setHttpCode(String httpCode) {
		this.httpCode = httpCode;
	}

	public String getHttpError() {
		return httpError;
	}
	public void setHttpError(String httpError) {
		this.httpError = httpError;
	}

	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
}

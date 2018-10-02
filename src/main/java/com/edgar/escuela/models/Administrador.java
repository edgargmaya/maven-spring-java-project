package com.edgar.escuela.models;

import javax.validation.constraints.*;

public class Administrador {
	
	private int id;
	
	@NotNull (message = "El campo 'nombre' es obligatorio" )
	@Size(min = 3, max = 20, message = "El 'nombre' debe tener al menos 5 letras y maximo 20")
	private String nombre;
	
	@NotNull (message = "El campo 'primer apellido' es obligatorio" )
	@Size(min = 3, max = 20, message = "El 'primer apellido' debe tener al menos 5 letras y maximo 20")
	private String primerApellido;
	
	@NotNull (message = "El campo 'segundo apellido' es obligatorio" )
	@Size(min = 3, max = 20, message = "El 'segundo apellido' debe tener al menos 5 letras y maximo 20")
	private String segundoApellido;
	
	@NotNull (message = "El campo 'nivel' es obligatorio" )
	@Digits(integer = 1, fraction = 0, message = "El 'nivel' debe ser un solo digito")
	@Min(value = 0, message = "El 'nivel' debe ser 0 ó 1")
	@Max(value = 1, message = "El 'nivel' debe ser 0 ó 1")
	private int nivel;
	
	@NotNull (message = "El campo 'email' es obligatorio" )
	@Email(message = "El 'email' no cumple con el formato requerido" )
	@Size(min = 5, max = 30, message = "El 'email' debe tener al menos 5 caracteres y maximo 30" )
	private String email;
	
	@NotNull (message = "El campo 'contraseña' es obligatorio" )
	@Size(min = 5, max = 20, message = "La 'contraseña' debe tener al menos 5 caracteres y maximo 60")
	private String password;
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getPrimerApellido() {
		return primerApellido;
	}
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}
	
	public String getSegundoApellido() {
		return segundoApellido;
	}
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}
	
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}	
	
}

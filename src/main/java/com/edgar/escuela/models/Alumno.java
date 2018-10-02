package com.edgar.escuela.models;

public class Alumno {
	
	private String Matricula;
	private String Nombre;
	private String ApellidoP;
	private String ApellidoM;
	private int Edad;
	private String ClaveGrupo;
	
	public String getMatricula() {
		return Matricula;
	}
	public void setMatricula(String matricula) {
		Matricula = matricula;
	}
	
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	
	public String getApellidoP() {
		return ApellidoP;
	}
	public void setApellidoP(String apellidoP) {
		ApellidoP = apellidoP;
	}
	
	public String getApellidoM() {
		return ApellidoM;
	}
	public void setApellidoM(String apellidoM) {
		ApellidoM = apellidoM;
	}
	
	public int getEdad() {
		return Edad;
	}
	public void setEdad(int edad) {
		Edad = edad;
	}
	
	public String getClaveGrupo() {
		return ClaveGrupo;
	}
	public void setClaveGrupo(String claveGrupo) {
		ClaveGrupo = claveGrupo;
	}
	
}

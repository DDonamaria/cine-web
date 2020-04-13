package com.spring.app.model;

import java.util.Date;

public class Horario {
	
	private int id;
	private Date fecha;
	private String sala;
	private String hora;
	private double precio;
	private Pelicula pelicula;
	
	public Horario() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getSala() {
		return sala;
	}
	public void setSala(String sala) {
		this.sala = sala;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public Pelicula getPelicula() {
		return pelicula;
	}
	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}
	
	@Override
	public String toString() {
		return "Horario [id=" + id + ", fecha=" + fecha + ", sala=" + sala + ", hora=" + hora + ", precio=" + precio
				+ ", pelicula=" + pelicula + "]";
	}
	
	

}

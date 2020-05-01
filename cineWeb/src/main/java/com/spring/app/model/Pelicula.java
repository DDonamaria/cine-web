package com.spring.app.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Peliculas")
public class Pelicula {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //IDENTITY para MySql AUTOINCREMENTAL
	private int id;
	private String titulo;
	private int duracion;
	private String genero;
	@Column(name="clasificacion")
	private String categoria;
	@Temporal(TemporalType.DATE)
	private Date fechaEstreno;
	private String imagen = "cinema.png";
	private String estatus="Activa";
	
//	@Transient //Ignora el atributo al guardar y recoger datos de BBDD
	@OneToOne
	@JoinColumn(name="idDetalle")
	private Detalle detalle;
	
	@OneToMany(mappedBy = "pelicula", fetch = FetchType.EAGER)
	private List<Horario> horarios;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public Date getFechaEstreno() {
		return fechaEstreno;
	}
	public void setFechaEstreno(Date fechaEstreno) {
		this.fechaEstreno = fechaEstreno;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public Detalle getDetalle() {
		return detalle;
	}
	public void setDetalle(Detalle detalle) {
		this.detalle = detalle;
	}
	public List<Horario> getHorarios() {
		return horarios;
	}
	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}
	@Override
	public String toString() {
		return "Pelicula [id=" + id + ", titulo=" + titulo + ", duracion=" + duracion + ", genero=" + genero
				+ ", categoria=" + categoria + ", fechaEstreno=" + fechaEstreno + ", imagen=" + imagen + ", estatus="
				+ estatus + ", detalle=" + detalle + "]";
	}
	
	
	
}

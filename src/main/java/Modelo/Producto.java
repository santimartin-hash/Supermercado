package Modelo;

import java.util.Date;

public class Producto {
	private int id;
	private String codigo;
	private String nombre;
	private int cantidad;
	private double precio;
	private Date caducidad;
	private int id_seccion;
	private String NombreSeccion;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public Date getCaducidad() {
		return caducidad;
	}
	public void setCaducidad(Date caducidad) {
		this.caducidad = caducidad;
	}
	public int getId_seccion() {
		return id_seccion;
	}
	public void setId_seccion(int id_seccion) {
		this.id_seccion = id_seccion;
	}
	public String getNombreSeccion() {
		return NombreSeccion;
	}
	public void setNombreSeccion(String nombreSeccion) {
		NombreSeccion = nombreSeccion;
	}
}

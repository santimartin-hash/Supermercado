package Modelo;

import java.util.ArrayList;

public class Supermercado {
private ArrayList<Producto> producto = new ArrayList<Producto>();
private int id;
private String Nombre;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getNombre() {
	return Nombre;
}
public void setNombre(String nombre) {
	Nombre = nombre;
}
public ArrayList<Producto> getLibros() {
	return producto;
}
public void setLibros(ArrayList<Producto> producto) {
	this.producto = producto;
}
}

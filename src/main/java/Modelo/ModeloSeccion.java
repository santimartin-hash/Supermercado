package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModeloSeccion  extends Conector{
 ResultSet rs;
 PreparedStatement pst;
 
public ArrayList<Seccion> getSecciones() throws SQLException {
	ArrayList<Seccion> secciones = new ArrayList<Seccion>();
	pst = conexion.prepareStatement("Select * From secciones");	
	pst.executeQuery();
	
	rs = pst.executeQuery();
	while(rs.next()) {
		Seccion seccion = new Seccion();
		seccion.setId(rs.getInt("id"));
		seccion.setNombre(rs.getString("nombre"));
		secciones.add(seccion);
	}
	return secciones;
	
	
}
	
	
	
	
	
}

package Modelo;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModeloSupermercado extends Conector {
	PreparedStatement pst;
	ResultSet rs;
	
public ArrayList<Supermercado> getSupermercados() {
		
		ArrayList<Supermercado> Supermercados = new ArrayList<Supermercado>();
		try {
			pst = conexion.prepareStatement("SELECT * FROM  supermercados");
			pst.executeQuery();
			
			rs = pst.executeQuery();
			while(rs.next()) {
				Supermercado supermercado = new Supermercado();
				supermercado.setId(rs.getInt("id"));

				supermercado.setNombre(rs.getString("Nombre"));
	
				Supermercados.add(supermercado);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return Supermercados;
	}
public void insertarProductoEnSupermercado( int id, String id_supermercado) {
	
	try {
		pst = conexion.prepareStatement("INSERT INTO productos_supermercados( id_producto,id_supermercado) VALUES (?,?)");

		pst.setInt(1,id);
		pst.setString(2, id_supermercado);
		pst.execute();
		

	} catch (SQLException e) {
		
		e.printStackTrace();
	}

}
public void eliminarProductoEnSupermercados( int id) {
	
	try {
		pst = conexion.prepareStatement("Delete from productos_supermercados where id_producto = ? ");
		pst.setInt(1,id);
		pst.execute();
		

	} catch (SQLException e) {
		
		e.printStackTrace();
	}

}



}


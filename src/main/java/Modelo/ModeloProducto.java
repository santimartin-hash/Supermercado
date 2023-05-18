package Modelo;

import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;




public class ModeloProducto extends Conector{
	PreparedStatement pst;
	ResultSet rs;

	
	public ArrayList<Producto> getProductos() {
		ArrayList<Producto> productos = new ArrayList<Producto>();
		try {
			pst = conexion.prepareStatement("SELECT productos.id, codigo, productos.nombre, cantidad, precio, caducidad, id_seccion, Secciones.Nombre AS NombreSeccion FROM  productos INNER JOIN Secciones on productos.id_seccion = secciones.id");
			pst.executeQuery();
			
			rs = pst.executeQuery();
			while(rs.next()) {
				Producto producto = new Producto();
				producto.setId(rs.getInt("id"));
				producto.setCodigo(rs.getString("codigo"));
				producto.setNombre(rs.getString("Nombre"));
				producto.setCantidad(rs.getInt("cantidad"));
				producto.setPrecio(rs.getDouble("precio"));
				producto.setCaducidad(rs.getDate("caducidad"));
				producto.setId_seccion(rs.getInt("id_seccion"));
				producto.setNombreSeccion(rs.getString("NombreSeccion"));
				productos.add(producto);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return productos;
	}
}

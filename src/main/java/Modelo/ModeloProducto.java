package Modelo;

import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;




public class ModeloProducto extends Conector{
	PreparedStatement pst;
	ResultSet rs;
	public void insertarProducto(Producto producto) {
		
		try {
			pst = conexion.prepareStatement("INSERT INTO productos(id, codigo,nombre, cantidad,precio, caducidad, id_seccion) VALUES (?,?,?,?,?,?,?)");
			
			pst.setInt(1, producto.getId());
			pst.setString(2, producto.getCodigo());
			pst.setString(3, producto.getNombre());
			pst.setInt(4, producto.getCantidad());
			pst.setDouble(5, producto.getPrecio());
			pst.setDate(6, new Date(producto.getCaducidad().getTime()));
			pst.setInt(7, producto.getId_seccion());
			pst.execute();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	}
	
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

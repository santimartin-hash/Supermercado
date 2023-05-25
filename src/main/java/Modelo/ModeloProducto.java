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
	public void modificarProducto(Producto producto) {
	    try {
	        pst = conexion.prepareStatement("UPDATE productos SET codigo=?, nombre=?, cantidad=?, precio=?, caducidad=?, id_seccion=? WHERE id=?");
	        pst.setString(1, producto.getCodigo());
	        pst.setString(2, producto.getNombre());
	        pst.setInt(3, producto.getCantidad());
	        pst.setDouble(4, producto.getPrecio());
	        pst.setDate(5, new Date(producto.getCaducidad().getTime()));
	        pst.setInt(6, producto.getId_seccion());
	        pst.setInt(7, producto.getId());
	        pst.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	
	public ArrayList<Producto> getProductos() {
		
		ArrayList<Producto> productos = new ArrayList<Producto>();
		try {
			pst = conexion.prepareStatement("SELECT productos.id, codigo, productos.nombre, cantidad, precio, caducidad, id_seccion, Secciones.Nombre AS NombreSeccion FROM  productos INNER JOIN Secciones on productos.id_seccion = secciones.id ");
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
	
	
public Producto getProductoId(int id) {
		ArrayList<Producto> productos = new ArrayList<Producto>();
		Producto producto = new Producto();
		try {
			pst = conexion.prepareStatement("SELECT productos.id, codigo, productos.nombre, cantidad, precio, caducidad, id_seccion, Secciones.Nombre AS NombreSeccion FROM  productos INNER JOIN Secciones on productos.id_seccion = secciones.id WHERE productos.id = ? ");
			pst.setInt(1, id);
			
			pst.executeQuery();
			
			rs = pst.executeQuery();
			while(rs.next()) {
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
		return producto;
	}
	
public String ConsultarCodigo(String codigo) throws SQLException {
		
		pst = conexion.prepareStatement("SELECT codigo FROM  productos Where codigo = ? ");
		
		pst.setString(1, codigo);
		
		pst.executeQuery();

		rs = pst.executeQuery();
		
		Producto producto = new Producto();
		
		while(rs.next()) {	
			producto.setCodigo(rs.getString("Codigo"));
		}
		
		return producto.getCodigo();
	}

public void DisminuirStock(int id, int stock) throws SQLException {
	pst = conexion.prepareStatement("UPDATE productos SET cantidad=? WHERE id=?");
	
	pst.setInt(2, id);
	pst.setInt(1, stock);
	pst.execute();

}
public int CountProductosEnSupermercado(int id) throws SQLException {
	pst = conexion.prepareStatement("select COUNT(*) as count from productos_supermercados where id_producto = ? ");
	
	pst.setInt(1, id);
	
	pst.executeQuery();

	rs = pst.executeQuery();
	
	int CountProductoEnSupermercado = 0;
	
	while(rs.next()) {	
		CountProductoEnSupermercado = rs.getInt("count");
	}
	
	return CountProductoEnSupermercado;
}

public void EliminarProductoDeTodosLosSupermercados(int id) throws SQLException {
	pst = conexion.prepareStatement("DELETE FROM `productos_supermercados` WHERE id_producto = ?");
	

	pst.setInt(1, id);
	pst.execute();

}
public void EliminarProducto(int id) throws SQLException {
	pst = conexion.prepareStatement("DELETE FROM `productos` WHERE id = ?");
	

	pst.setInt(1, id);
	pst.execute();

}
}

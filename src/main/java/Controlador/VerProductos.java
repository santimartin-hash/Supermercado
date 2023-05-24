package Controlador;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modelo.ModeloProducto;
import Modelo.Producto;

/**
 * Servlet implementation class VerProductos
 */
@WebServlet("/VerProductos")
public class VerProductos extends HttpServlet implements Comparator<Producto>{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerProductos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ModeloProducto ModeloProducto = new ModeloProducto();
		String cadena = request.getParameter("cadena");
	
		ModeloProducto.conectar();
		ArrayList<Producto> productos = ModeloProducto.getProductos();
		
		if( cadena != null) {
		Iterator<Producto> iterator = productos.iterator();
		while (iterator.hasNext()) {
		    Producto producto = iterator.next();
		    if (!producto.getCodigo().toLowerCase().contains(cadena) && !producto.getNombre().toLowerCase().contains(cadena)) {
		        iterator.remove();
		    }
		}
		}
		

		String PrecioMin = request.getParameter("PrecioMin");
		
		if (PrecioMin != null && !PrecioMin.isEmpty()) {
			double min = Double.MIN_VALUE;
		 min = Double.parseDouble(PrecioMin);
		Iterator<Producto> iterator = productos.iterator();
		while (iterator.hasNext()) {
		    Producto producto = iterator.next();
		    if (producto.getPrecio() < min ) {
		        iterator.remove();
		    }
		}
		}
		
		String PrecioMax = request.getParameter("PrecioMax");
		
		if (PrecioMax != null && !PrecioMax.isEmpty()) {
			double max = Double.MAX_VALUE;
			max = Double.parseDouble(PrecioMax);
		Iterator<Producto> iterator = productos.iterator();
		while (iterator.hasNext()) {
		    Producto producto = iterator.next();
		    if (producto.getPrecio() > max ) {
		        iterator.remove();
		    }
		}
		}
		String Filtro = request.getParameter("Filtro");
		if (Filtro != null )	{
		productos.sort(this);
		}
		request.setAttribute("productos", productos);
		ModeloProducto.cerrar();
		
		request.getRequestDispatcher("VerProducto.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	@Override
	public int compare(Producto o1, Producto o2) {
		Producto producto1 = (Producto) o1;
		Producto producto2 = (Producto) o2;
		return (producto1.getCodigo().compareTo(producto2.getCodigo()));
	}
	
	/*public int compare2(Producto o1, Producto o2) {
		Producto producto1 = (Producto) o1;
		Producto producto2 = (Producto) o2;
		return (producto1.getCodigo().compareTo(producto2.getCodigo()));
	}*/


}

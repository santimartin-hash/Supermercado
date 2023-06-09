package Controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modelo.ModeloProducto;
import Modelo.ModeloSeccion;
import Modelo.ModeloSupermercado;
import Modelo.Producto;
import Modelo.Seccion;
import Modelo.Supermercado;

/**
 * Servlet implementation class ModificarProducto
 */
@WebServlet("/ModificarProducto")
public class ModificarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModificarProducto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ModeloSeccion modeloSeccion = new ModeloSeccion();
		modeloSeccion.conectar();
		try {
			ArrayList<Seccion> secciones = modeloSeccion.getSecciones();
			request.setAttribute("secciones", secciones);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		modeloSeccion.cerrar();
		

		ModeloSupermercado modeloSupermercado = new ModeloSupermercado();
		modeloSupermercado.conectar();

		ArrayList<Supermercado> Supermercados = modeloSupermercado.getSupermercados();
		request.setAttribute("Supermercados", Supermercados);
		modeloSupermercado.cerrar();

		int id = Integer.parseInt(request.getParameter("producto_id"));

		ModeloProducto modeloProducto = new ModeloProducto();
		modeloProducto.conectar();
		Producto producto = modeloProducto.getProductoId(id);
		request.setAttribute("producto", producto);
		try {
			int countProductosEnSupermercado = modeloProducto.CountProductosEnSupermercado(id);
			if (countProductosEnSupermercado > 0) {
				ArrayList<Integer> supermercadosDeProducto = modeloProducto.ListaDeSupermercadosDeProducto(id);

				request.setAttribute("SupermercadosDeProducto", supermercadosDeProducto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		modeloProducto.cerrar();
		
		
		request.getRequestDispatcher("ModificarProducto.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ModeloProducto ModeloProducto = new ModeloProducto();
		ModeloSupermercado modeloSupermercado = new ModeloSupermercado();

		SimpleDateFormat fechaFormato = new SimpleDateFormat("yyyy-MM-dd");
		Producto producto = new Producto();

		int id = Integer.parseInt(request.getParameter("id"));
		String codigo = request.getParameter("codigo");
		String nombre = request.getParameter("nombre");
		int cantidad = Integer.parseInt(request.getParameter("cantidad"));
		Double precio = Double.parseDouble(request.getParameter("precio"));
		String caducidad = request.getParameter("caducidad");
		Date fechaCaducidad;
		int id_seccion = Integer.parseInt(request.getParameter("seccion"));

		String[] id_supermercados = request.getParameterValues("id_supermercado");
		modeloSupermercado.conectar();
		if (id_supermercados != null) {
			modeloSupermercado.eliminarProductoEnSupermercados(id);
			for (String id_supermercado : id_supermercados) {
				modeloSupermercado.insertarProductoEnSupermercado(id, id_supermercado);
			}
		}
		modeloSupermercado.cerrar();
		
		producto.setId(id);
		producto.setCodigo(codigo);
		producto.setNombre(nombre);
		producto.setCantidad(cantidad);
		producto.setPrecio(precio);
		producto.setId_seccion(id_seccion);

		try {
			fechaCaducidad = fechaFormato.parse(caducidad);
			producto.setCaducidad(fechaCaducidad);

			ModeloProducto.conectar();
			Calendar calendar = Calendar.getInstance();
			Date currentDate = calendar.getTime();
			if (precio < 0 || cantidad < 0) {
				request.setAttribute("mensaje", "Precio y Cantidad deben de ser positivos");
				request.getRequestDispatcher("ModificarProducto.jsp").forward(request, response);
			} else if (fechaCaducidad.before(currentDate)) {
				request.setAttribute("mensaje", "Fecha invalida");
				request.getRequestDispatcher("ModificarProducto.jsp").forward(request, response);
			} else if (id_seccion == 0) {
				request.setAttribute("mensaje", "introduce una seccion");
				request.getRequestDispatcher("ModificarProducto.jsp").forward(request, response);
			} else {
				ModeloProducto.modificarProducto(producto);
				ModeloProducto.cerrar();
				request.getRequestDispatcher("VerProductos").forward(request, response);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

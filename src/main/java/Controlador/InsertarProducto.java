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
import Modelo.Producto;
import Modelo.Seccion;

/**
 * Servlet implementation class InsertarProducto
 */
@WebServlet("/InsertarProducto")
public class InsertarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @throws SQLException 
     * @see HttpServlet#HttpServlet()
     */
    public InsertarProducto()  {
        super();
    
      
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
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
       request.getRequestDispatcher("InsertarProducto.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModeloProducto ModeloProducto = new ModeloProducto();

	
		
		
		SimpleDateFormat fechaFormato = new SimpleDateFormat("yyyy-MM-dd");
		Producto producto = new Producto();
		
		int id = Integer.parseInt(request.getParameter("id"));
		String codigo = request.getParameter("codigo");
		String nombre = request.getParameter("nombre");
		int cantidad = Integer.parseInt(request.getParameter("cantidad"));
		Double precio = Double.parseDouble(request.getParameter("precio"));
		String caducidad = request.getParameter("caducidad");
		Date fechaCaducidad;
		int id_seccion =Integer.parseInt( request.getParameter("seccion"));
		
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
			String CodigoBBDD;
			try {
				
				CodigoBBDD = ModeloProducto.ConsultarCodigo(codigo);
				if (codigo.equals(CodigoBBDD)) {
					request.setAttribute("mensaje", "Codigo Duplicado");
					request.getRequestDispatcher("InsertarProducto.jsp").forward(request, response);	
				} else if (precio < 0 || cantidad < 0){
					request.setAttribute("mensaje", "Precio y Cantidad deben de ser positivos");
					request.getRequestDispatcher("InsertarProducto.jsp").forward(request, response);
				} else if (fechaCaducidad.before(currentDate)){
					request.setAttribute("mensaje", "Fecha invalida");
					request.getRequestDispatcher("InsertarProducto.jsp").forward(request, response);
				} else if (id_seccion == 0){
					request.setAttribute("mensaje", "introduce una seccion");
					request.getRequestDispatcher("InsertarProducto.jsp").forward(request, response);
				} else {
				ModeloProducto.insertarProducto(producto);
				ModeloProducto.cerrar();
				request.getRequestDispatcher("VerProductos").forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
}
		
		
	      
	

}

package Controlador;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modelo.ModeloProducto;

/**
 * Servlet implementation class EliminarMultiplev2
 */
@WebServlet("/EliminarMultiplev2")
public class EliminarMultiplev2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarMultiplev2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String[] id_seleccionados = request.getParameterValues("seleccionados");
		ModeloProducto ModeloProducto = new ModeloProducto();
		ModeloProducto.conectar();
		if(id_seleccionados != null) {
		for (String string : id_seleccionados) {
			int id = Integer.parseInt(string);
			try {
				ModeloProducto.EliminarProducto(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
		ModeloProducto.cerrar();
		response.sendRedirect("VerProductos");
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

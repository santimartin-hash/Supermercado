package Controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modelo.ModeloProducto;
import Modelo.Producto;

/**
 * Servlet implementation class EliminarMultiple
 */
@WebServlet("/EliminarMultiple")
public class EliminarMultiple extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarMultiple() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	String codigos = request.getParameter("codigos");
	
	if (codigos != "") {
	
		String[] codigosSeparados = codigos.split(",");
	
		int exixte = 0;
	
		int countCodigos = 0;
	
	
		ModeloProducto modeloproducto = new ModeloProducto();
		modeloproducto.conectar();
		ArrayList<Producto> productos = modeloproducto.getProductos();
		request.setAttribute("productos", productos);
		modeloproducto.cerrar();
	
	for (String codigo : codigosSeparados) {
		countCodigos = countCodigos + 1;
		modeloproducto.conectar();
		try {
			
			int codigoBBDD = modeloproducto.ComprobarSiCodigoExixte(codigo);
			if (codigoBBDD > 0) {
				exixte = exixte + 1;
			} 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		modeloproducto.cerrar();
	}
	
	if (exixte == countCodigos) {
	for (String codigo : codigosSeparados) {
	modeloproducto.conectar();
			try {
				modeloproducto.EliminarProductoCodigo(codigo);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	modeloproducto.cerrar();
	}
	} else {
    request.setAttribute("mensaje", "Algun codigo introducido no existe");
    request.getRequestDispatcher("VerProducto.jsp").forward(request, response);
	return;
	}
	} else {
	response.sendRedirect("VerProductos");
	return;
	}
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

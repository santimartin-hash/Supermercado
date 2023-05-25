package Controlador;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modelo.ModeloProducto;
import Modelo.Producto;

/**
 * Servlet implementation class EliminarEspecial
 */
@WebServlet("/EliminarEspecial")
public class EliminarEspecial extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarEspecial() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("producto_id"));
		
		ModeloProducto modeloProducto = new ModeloProducto();
		modeloProducto.conectar();
		
		
		Producto producto = modeloProducto.getProductoId(id);
		
		int stock = producto.getCantidad();
		
		if (stock > 0) {
			stock = stock - 1;
			try {
				modeloProducto.DisminuirStock(id, stock);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else { 
		int CountProductosEnSupermercado;
		try {
			CountProductosEnSupermercado = modeloProducto.CountProductosEnSupermercado(id);
			if (CountProductosEnSupermercado > 0) {
				modeloProducto.EliminarProductoDeTodosLosSupermercados(id);
			} else {
				modeloProducto.EliminarProducto(id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		response.sendRedirect("VerProductos");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

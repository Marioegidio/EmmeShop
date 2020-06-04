package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.ArrayProdotti;
import model.ProdottoBean;
import model.UserModelDS;

/**
 * Servlet implementation class MinusProduct
 */
@WebServlet("/MinusProduct")
public class MinusProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MinusProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		HttpSession session=request.getSession();
		synchronized(session) {
					UserModelDS model=new UserModelDS();
					
					ArrayProdotti cart=(ArrayProdotti) session.getAttribute("cart");
					
					int prodotto=Integer.parseInt(request.getParameter("prodotto"));
					int quantita=Integer.parseInt(request.getParameter("quant"));
					String categoria=request.getParameter("cat");
					String negozio=request.getParameter("neg");
					 
					ProdottoBean p=null;
					
					try {
						p=model.getProductByShopAndCategory(negozio, categoria, prodotto);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					if(cart==null) {
						cart=new ArrayProdotti();
					}
					ProdottoBean presente=cart.findProduct(p);
					presente.setQuantitaOrdinata(quantita);
					
					
					

					session.setAttribute("cart",cart);					
					//System.out.println(new Gson().toJson(cart));
					response.getWriter().write(new Gson().toJson(cart));
		}
					
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

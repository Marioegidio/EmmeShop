package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.google.gson.Gson;

import model.ArrayProdotti;
import model.ProdottoBean;
import model.UserModelDS;

/**
 * Servlet implementation class AddToCart
 */
@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCart() {
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
						String note=request.getParameter("descrizione");
						String categoria=request.getParameter("cat");
						String negozio=request.getParameter("neg");
						
						ProdottoBean p=null;
						
						try {
							p=model.getProductByShopAndCategory(negozio, categoria, prodotto);
							p.setQuantitaOrdinata(quantita);
							p.setNote(note);
						} catch (SQLException e) {
							e.printStackTrace();
						}
						
						if(cart==null) {
							cart=new ArrayProdotti();
						}
						ProdottoBean presente=cart.findProduct(p);
						
						boolean inserito=true;
						
						if(presente==null)
							cart.add(p);
						else if((presente.getQuantita() - presente.getQuantitaOrdinata()) >= quantita){
							presente.setQuantitaOrdinata(presente.getQuantitaOrdinata()+quantita);
							presente.setNote(presente.getNote()+"\n"+note);
						}
						else inserito=false;
						
						
						/*try { 
							if(model.updateQuantita(negozio, categoria,prodotto, p.getQuantita()-quantita))
								cart.add(p);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}*/
						
						
						
						if(inserito) {
							session.setAttribute("cart",cart);					
							//System.out.println(new Gson().toJson(cart));
							response.getWriter().write(new Gson().toJson(cart));
						}else {
							response.getWriter().write("");
						}
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

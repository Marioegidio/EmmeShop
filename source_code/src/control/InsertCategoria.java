package control;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;

/**
 * Servlet implementation class InsertCategoria
 */
@WebServlet("/InsertCategoria")
public class InsertCategoria extends HttpServlet {
	static UserModelDS model= new UserModelDS();

	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertCategoria() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String address="";
		try {
			HttpSession session=request.getSession();
			session.setAttribute("urlLogoCategoria", "images/favicon.ico");
            
			CategoriaBean categoria=new CategoriaBean();
			NegozioBean neg=(NegozioBean) session.getAttribute("negozioBean");
			String negozio=(String) session.getAttribute("negozioNome");
			
				categoria.setNomeNegozio(request.getParameter("nomeNegozio"));
				categoria.setNomeCategoria(request.getParameter("nomeCategoria"));
				categoria.setDescrizione(request.getParameter("descCategoria"));
				categoria.setPath("images/favicon.ico");
			
			if(!categoria.getNomeNegozio().equals("Some problem")) {
				model.addCategoria(categoria);
				request.getSession().setAttribute("nomeCategoriaImage",categoria.getNomeCategoria());
				
				address="venditore/uploadImageCategoria.jsp";
				response.sendRedirect(address);
			}
			else {
				response.sendRedirect("errore.jsp");
			}
		}
		catch(Exception e) {
			 address="errore.jsp";
			 response.sendRedirect(address);
			System.out.print(e);
		}
		finally {
			
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

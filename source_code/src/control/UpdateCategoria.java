package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CategoriaBean;
import model.NegozioBean;
import model.UserModelDS;

/**
 * Servlet implementation class UpdateCategoria
 */
@WebServlet("/UpdateCategoria")
public class UpdateCategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static UserModelDS model= new UserModelDS();

	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCategoria() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String address="";
		HttpSession session=request.getSession();
		
		CategoriaBean bean=new CategoriaBean();
		bean.setNomeNegozio(request.getParameter("nomeNegozio"));
		bean.setNomeCategoria(request.getParameter("nomeCategoria"));
		bean.setDescrizione(request.getParameter("descCategoria"));
		bean.setPath(request.getParameter("path"));
		session.setAttribute("urlLogoCategoria",bean.getPath());
		
		try 
		{
			session.setAttribute("urlLogoCategoria", bean.getPath());
	       
		if(!bean.getNomeNegozio().equals("Some problem")) {
			model.updateDescrizioneCategoria(bean.getNomeNegozio(), bean.getNomeCategoria(), bean.getDescrizione());
			request.getSession().setAttribute("nomeCategoriaImage",bean.getNomeCategoria());
			
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

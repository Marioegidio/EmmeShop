package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ClienteBean;
import model.ProdottoBean;
import model.UserModelDS;

/**
 * Servlet implementation class UpdateCliente
 */
@WebServlet("/UpdateCliente")
public class UpdateCliente extends HttpServlet {
	static UserModelDS model= new UserModelDS();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		ClienteBean bean=new ClienteBean();
		try
		{	
			bean.setUsername(request.getParameter("username"));
			bean.setPassword(request.getParameter("password"));
			bean.setNome(request.getParameter("fname"));
			bean.setCognome(request.getParameter("lname"));
			bean.setEmail(request.getParameter("email"));
			bean.setSesso(request.getParameter("gender"));
			bean.setTelefono(request.getParameter("phone"));
			bean.setVia(request.getParameter("street"));
			bean.setCitta(request.getParameter("city"));
			bean.setCap(request.getParameter("CAP"));
			
			model.updateCliente(bean);
			
			String address="amministratore/index-amministratore-clienti.jsp";
			response.sendRedirect(address);
	}
	catch(Exception e) {
		  String address="errore.jsp";
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

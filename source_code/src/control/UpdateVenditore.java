package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserModelDS;
import model.VenditoreBean;

/**
 * Servlet implementation class UpdateVenditore
 */
@WebServlet("/UpdateVenditore")
public class UpdateVenditore extends HttpServlet {
	static UserModelDS model= new UserModelDS();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateVenditore() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			VenditoreBean bean=new VenditoreBean();
			
			
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
			
			model.updateVenditore(bean);
			
			//HttpSession session=request.getSession();
			//session.setAttribute("username-venditore", bean.getUsername());
			String address="amministratore/index-amministratore-venditori.jsp";
			
			response.sendRedirect(address);
		}
		catch (SQLException e) {//errore ricarica il form registrazione
			String address="errore.jsp";
			 response.sendRedirect(address);
			System.out.print(e);
			
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

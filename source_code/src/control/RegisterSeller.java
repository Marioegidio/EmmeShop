package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserModelDS;
import model.VenditoreBean;

/**
 * Servlet implementation class RegisterSeller
 */
@WebServlet("/RegisterSeller")
public class RegisterSeller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static UserModelDS model= new UserModelDS();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterSeller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			VenditoreBean seller=new VenditoreBean();
			/*
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			String nome=request.getParameter("fname");
			String cognome=request.getParameter("lname");
			String email=request.getParameter("email");
			String sesso=request.getParameter("gender");
			String telefono=request.getParameter("phone");
			String via=request.getParameter("street");
			String città=request.getParameter("city");
			String cap=request.getParameter("CAP");
			
			//model.addVenditore(username, password, nome, cognome, email, sesso, telefono, via, città, cap);
			*/
			seller.setUsername(request.getParameter("username"));
			seller.setPassword(request.getParameter("password"));
			seller.setNome(request.getParameter("fname"));
			seller.setCognome(request.getParameter("lname"));
			seller.setEmail(request.getParameter("email"));
			seller.setSesso(request.getParameter("gender"));
			seller.setTelefono(request.getParameter("phone"));
			seller.setVia(request.getParameter("street"));
			seller.setCitta(request.getParameter("city"));
			seller.setCap(request.getParameter("CAP"));
			
			model.addVenditore(seller);
			
			HttpSession session=request.getSession();
			session.setAttribute("username-venditore", seller.getUsername());
			String address="./seller/questionNegozio.jsp";//domanda se vuoi creare il tuo negozio subito

			response.sendRedirect(address);
		}
		catch (SQLException e) {//errore ricarica il form registrazione
			System.out.println("Error:" + e.getMessage());
			String address="./seller/venditore-registrazione.jsp";
			response.sendRedirect(address);
			
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

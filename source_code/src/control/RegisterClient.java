package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ClienteBean;
import model.UserModelDS;

/**
 * Servlet implementation class RegisterClient
 */
@WebServlet("/RegisterClient")
public class RegisterClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static UserModelDS model= new UserModelDS();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterClient() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			ClienteBean client=new ClienteBean();
			
			/*versione vecchia
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
			
			model.addCliente(username, password, nome, cognome, email, sesso, telefono, via, città, cap);
			*/
			client.setUsername(request.getParameter("username"));
			client.setPassword(request.getParameter("password"));
			client.setNome(request.getParameter("fname"));
			client.setCognome(request.getParameter("lname"));
			client.setEmail(request.getParameter("email"));
			client.setSesso(request.getParameter("gender"));
			client.setTelefono(request.getParameter("phone"));
			client.setVia(request.getParameter("street"));
			client.setCitta(request.getParameter("city"));
			client.setCap(request.getParameter("CAP"));
			
			model.addCliente(client);
			
			String address="./index.jsp#accedi";
			response.sendRedirect(address);
		}
		catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
			String address="./utilities/erroreInserimento.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
			dispatcher.forward(request, response);
			
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

package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AmministratoreBean;
import model.UserModelDS;

/**
 * Servlet implementation class RegisterAdmin
 */
@WebServlet("/RegisterAdmin")
public class RegisterAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static UserModelDS model= new UserModelDS();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterAdmin() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			AmministratoreBean admin=new AmministratoreBean();
			
			/*/String username=request.getParameter("username");
			String password=request.getParameter("password");
			String nome=request.getParameter("fname");
			String cognome=request.getParameter("lname");
			String email=request.getParameter("email");
			String sesso=request.getParameter("gender");
			String telefono=request.getParameter("phone");
			
			model.addAmministratore(username, password, nome, cognome, email, sesso, telefono);	
			*/
			
			admin.setUsername(request.getParameter("username"));
			admin.setPassword(request.getParameter("password"));
			admin.setNome(request.getParameter("fname"));
			admin.setCognome(request.getParameter("lname"));
			admin.setEmail(request.getParameter("email"));
			admin.setSesso(request.getParameter("gender"));
			admin.setTelefono(request.getParameter("phone"));
			
			model.addAmministratore(admin);
			
			
			String address="/index.html";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
			dispatcher.forward(request, response);
		}
		catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
			String address="/amministratore-registrazione.jsp";
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

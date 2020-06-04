package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AmministratoreBean;
import model.ClienteBean;
import model.NegozioBean;
import model.UserBean;
import model.UserModelDS;
import model.VenditoreBean;

/**
 * Servlet implementation class ProductControl
 */

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static UserModelDS model= new UserModelDS();
	
	public Login() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UserBean utente = null;
		
		try {
				String userN = request.getParameter("username");
				String passN = request.getParameter("password");
				String azione = request.getParameter("azione");
				
				
				if (azione==null || userN==null || passN==null)
					response.sendRedirect("./index.jsp");
				else {
				
				
							if(azione.equals("Cliente")) 
								utente = model.checkLoginClient(userN, passN);
							else if(azione.equals("Venditore")) 
								utente = model.checkLoginSeller(userN, passN);
							else if(azione.equals("Amministratore")) 
								utente = model.checkLoginAdmin(userN, passN);
							
								
							request.getSession().setAttribute("cercato","s");
							request.getSession().setAttribute("username", userN);
							
							
							request.getSession().setAttribute("cliente-loggato","n");
							request.getSession().setAttribute("venditore-loggato","n");
							request.getSession().setAttribute("amministratore-loggato","n");
				
							if(utente==null) {
									if(azione.equals("Cliente")){
										request.getSession().setAttribute("tipo", "cliente");
										request.getSession().setAttribute("nomeCliente", "nessuno");	
										response.sendRedirect("./index.jsp");
									}
									else if(azione.equals("Venditore")) {
										request.getSession().setAttribute("tipo", "venditore");
										NegozioBean negozio=null;
										request.getSession().setAttribute("negozioBean", negozio);	
										request.getSession().setAttribute("negozioNome", "nessuno");						
										response.sendRedirect("./index.jsp");
									}
									else if(azione.equals("Amministratore")) {
										request.getSession().setAttribute("tipo", "amministratore");
										response.sendRedirect("./index.jsp");
									}
									else response.sendRedirect("./index.jsp");
							}
							else {
								request.getSession().setAttribute("username", "");
								
								if(utente instanceof VenditoreBean) {
									VenditoreBean u1=(VenditoreBean) utente;
									
								}
								String address = "";
								
								if(utente instanceof ClienteBean){
									request.getSession().setAttribute("utente", (ClienteBean)utente);
									
									address="./cliente/index-cliente.jsp";
									request.getSession().setAttribute("cliente-loggato","s");
									request.getSession().setAttribute("nomeCliente", ((ClienteBean) utente).getNome());	
								}
								else if(utente instanceof VenditoreBean) {
									request.getSession().setAttribute("utente", utente);
									
									address="./venditore/index-venditore.jsp";	
									NegozioBean negozio=model.getNegozio(utente.getUsername());
									if(negozio!=null) {
										request.getSession().setAttribute("negozioBean", negozio);
									    request.getSession().setAttribute("negozioNome", negozio.getNomeNegozio());		
									}
									request.getSession().setAttribute("venditore-loggato","s");
								}
								else if(utente instanceof AmministratoreBean) {
									request.getSession().setAttribute("utente", utente);
									
									request.getSession().setAttribute("cercato","");
									address="./amministratore/index-amministratore.jsp";				
									request.getSession().setAttribute("amministratore-loggato","s");
								}
			
								response.sendRedirect(address);
							}
				}
		} 
		catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.NegozioBean;
import model.ProdottoBean;
import model.UserModelDS;

/**
 * Servlet implementation class UpdateNegozio
 */
@WebServlet("/UpdateNegozio")
public class UpdateNegozio extends HttpServlet {
	
	static UserModelDS model= new UserModelDS();
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateNegozio() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String address="";
		NegozioBean bean=new NegozioBean();
		
		HttpSession session=request.getSession();
		
		
		try
		{	
			
			bean.setNomeNegozio(request.getParameter("nomeNegozio"));
		//	System.out.print(request.getParameter("nomeNegozio"));
			bean.setUsernameVenditore(request.getParameter("usenameVenditore"));
	//		System.out.print(request.getParameter("usernameVenditore"));
			bean.setDesign(request.getParameter("design"));
//			System.out.print(request.getParameter("design"));
			bean.setColore(request.getParameter("color"));
	//		System.out.print(request.getParameter("color"));
			bean.setPartitaIva(request.getParameter("Piva"));
		//	System.out.print(request.getParameter("Piva"));
			bean.setDataIscrizione(request.getParameter("dataIscrizione"));
	//		System.out.print(request.getParameter("dataIscrizione"));
			bean.setDescrizione(request.getParameter("descrizione"));
//			System.out.print(request.getParameter("descrizione"));
			bean.setVia(request.getParameter("street"));
	//		System.out.print(request.getParameter("street"));
			bean.setCitta(request.getParameter("city"));
		//	System.out.print(request.getParameter("city"));
			bean.setCap(request.getParameter("CAP"));
	//		System.out.print(request.getParameter("CAP"));
			bean.setLogo(request.getParameter("logo"));
//			System.out.print(request.getParameter("logo"));
			
			
			
			
			model.updateNegozio(bean);
			
			
		
			//HttpSession session=request.getSession();
			//session.removeAttribute("username-venditore");
			
			//session.setAttribute("NomeNegozio", request.getParameter("nomeNegozio"));
			//session.setAttribute("urlLogoNegozio", request.getParameter("logo") );
			//address="./seller/uploadImage.jsp";
			//response.sendRedirect(address);
			
			String address2="amministratore/index-amministratore.jsp";
			response.sendRedirect(address2);
	}
	catch(Exception e) {
		String address1="errore.jsp";
		 response.sendRedirect(address1);
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

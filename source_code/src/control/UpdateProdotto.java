package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CategoriaBean;
import model.ProdottoBean;
import model.UserModelDS;

/**
 * Servlet implementation class UpdateProdotto
 */
@WebServlet("/UpdateProdotto")
public class UpdateProdotto extends HttpServlet {
	
	static UserModelDS model= new UserModelDS();
	private static final long serialVersionUID = 1L;
	

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProdotto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String address="";
		HttpSession session=request.getSession();
		
		ProdottoBean bean=new ProdottoBean();
		try
		{	
			bean.setIdProdotto(Integer.parseInt(request.getParameter("id")));
			//System.out.print(request.getParameter("id"));
			bean.setNomeNegozio(request.getParameter("nomeNegozio"));
			//System.out.print(request.getParameter("nomeNegozio"));
			bean.setNomeCategoria(request.getParameter("nomeCategoria"));
			//System.out.print(request.getParameter("nomeCategoria"));
			bean.setNome(request.getParameter("nomeProdotto"));
			//System.out.print(request.getParameter("nomeProdotto"));
			bean.setDescrizione(request.getParameter("descProdotto"));
			//System.out.print(request.getParameter("descProdotto"));
			bean.setPrezzo(Float.parseFloat(request.getParameter("prezzoProdotto")));
			//System.out.print(request.getParameter("prezzoProdotto"));
			bean.setIva(Integer.parseInt(request.getParameter("ivaProdotto")));
			//System.out.print(request.getParameter("ivaProdotto"));
			bean.setQuantita(Integer.parseInt(request.getParameter("qtaProdotto")));
			//System.out.print(request.getParameter("qtaProdotto"));
			bean.setSconto(Integer.parseInt(request.getParameter("scontoProdotto")));
			//System.out.print(request.getParameter("scontoProdotto"));
			bean.setPath(request.getParameter("path"));
			session.setAttribute("urlLogoProdotto",bean.getPath());
			//System.out.print(request.getParameter("path"));
			
			
			if(!bean.getNomeNegozio().equals("Some problem")) {
				
				model.updateProdotto(bean);
				
				session.setAttribute("nomeProdottoImage",bean.getIdProdotto());
				session.setAttribute("nomeProdottoCat",bean.getNomeCategoria());
				
				address="venditore/uploadImageProdotto.jsp";
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

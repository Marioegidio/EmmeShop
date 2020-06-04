package control;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ArrayProdotti;
import model.ClienteBean;
import model.FatturaBean;
import model.ProdottoBean;
import model.RiferimentoBean;
import model.UserModelDS;

/**
 * Servlet implementation class ConfermaOrdine
 */
@WebServlet("/ConfermaOrdine")
public class ConfermaOrdine extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfermaOrdine() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		
		UserModelDS model=new UserModelDS();
		FatturaBean fattura=new FatturaBean();
		RiferimentoBean riferimento=new RiferimentoBean();
		ArrayProdotti cart=(ArrayProdotti) session.getAttribute("cart");
		
			
			ClienteBean utenteLog=(ClienteBean)session.getAttribute("utente");
			Date dt = new Date();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentTime = sdf.format(dt);
			
			fattura.setUsername_Cliente(utenteLog.getUsername());
			fattura.setDataOrdine(currentTime);
			
			fattura.setViaDestinazione(utenteLog.getVia());
			fattura.setCittaDestinazione(utenteLog.getCitta());
			fattura.setCapDestinazione(utenteLog.getCap());
			
			//System.out.println("\n fatt->"+fattura);
			
			try {
				model.addFattura(fattura);
				
				for(ProdottoBean p: cart.getArrayProd()) {
					
					riferimento.setId_prodotto(p.getIdProdotto());
					riferimento.setNome_Negozio(p.getNomeNegozio());
					riferimento.setNome_Categoria(p.getNomeCategoria());
					riferimento.setNumero_Fattura(fattura.getNumFattura());
					riferimento.setNote(p.getNote());
					riferimento.setQtaOrdinata(p.getQuantitaOrdinata());
					riferimento.setSconto(p.getSconto());
					riferimento.setPrezzoUnitario(p.getPrezzo());
					riferimento.setIva(p.getIva());

					//System.out.println("\n\t\t\t\t rif->"+riferimento);

					model.updateQtaById( p.getIdProdotto(), (p.getQuantita()-p.getQuantitaOrdinata()));
					
					model.addRiferimento(riferimento);

					session.removeAttribute("cart");
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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

<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="javax.servlet.*,java.io.*,model.*,java.util.*,com.itextpdf.*,com.itextpdf.text.pdf.*, com.itextpdf.text.Anchor,com.itextpdf.text.BadElementException
	,com.itextpdf.text.BaseColor,com.itextpdf.text.Chapter,com.itextpdf.text.Document,com.itextpdf.text.DocumentException,
	com.itextpdf.text.Element,com.itextpdf.text.Font,com.itextpdf.text.FontFactory,com.itextpdf.text.List,com.itextpdf.text.ListItem,
	com.itextpdf.text.PageSize,com.itextpdf.text.Paragraph,com.itextpdf.text.Phrase,com.itextpdf.text.Section,com.itextpdf.text.pdf.PdfDocument,
	com.itextpdf.text.pdf.PdfPCell,com.itextpdf.text.pdf.PdfPTable,com.itextpdf.text.pdf.PdfWriter,java.text.*"
    pageEncoding="UTF-8"
    session="true"
%>

<%@include file="../WEB-INF/utilities/checkLogCliente.jsp" %>
<%!UserModelDS model=new UserModelDS(); %>
<%
    FatturaBean fattura=null;
    RiferimentoBean rif;
	utenteLog=(ClienteBean)session.getAttribute("utente");
	ClienteBean c=model.getClient(utenteLog.getUsername());
	
	int numeroFattura=Integer.parseInt(request.getParameter("fattura"));
	
	if(utenteLog!=null && utenteLog.getUsername()!=null ){
		fattura= model.getFatturaByUser(utenteLog.getUsername(),numeroFattura);
	
		ArrayRiferimento listaRiferimento=fattura.getListaProdotti();
	
	
	
	
	
	
	
	
	

response.setContentType("application/pdf");
Document document=new Document();
ByteArrayOutputStream buffer= new ByteArrayOutputStream();
PdfWriter.getInstance(document, buffer);
document.open();











PdfPTable table = new PdfPTable(36); //numero di colonne

PdfPCell cell = new PdfPCell(new Phrase("Fattura\n\n"
		                                 +c.getNome()+" "+c.getCognome()+"\n" 
		                                 +c.getVia()+"\n"
		                                 +c.getCap()+" "+c.getCitta()+"\n"));
cell.setColspan(18);
cell.setHorizontalAlignment(Element.ALIGN_LEFT);

table.addCell(cell);
cell = new PdfPCell(new Phrase("Luogo Destinazione\n\n"
                               +c.getNome()+" "+c.getCognome()+"\n"  
		                       +fattura.getViaDestinazione()+"\n"
                               +fattura.getCapDestinazione()+" "+fattura.getCittaDestinazione()));
cell.setColspan(18);
cell.setHorizontalAlignment(Element.ALIGN_LEFT);
table.addCell(cell);

cell = new PdfPCell(new Phrase("n.Fattura\n\n\n"
                                +fattura.getNumFattura()));
cell.setColspan(7);
cell.setHorizontalAlignment(Element.ALIGN_LEFT);
table.addCell(cell);
cell = new PdfPCell(new Phrase("Data Documento\n\n"
		                       +fattura.getDataOrdine()));
cell.setColspan(6);
cell.setHorizontalAlignment(Element.ALIGN_LEFT);
table.addCell(cell);

cell = new PdfPCell(new Phrase("Username Cliente\n\n"
                               +fattura.getUsername_Cliente()));
cell.setColspan(7);
cell.setHorizontalAlignment(Element.ALIGN_LEFT);
table.addCell(cell);

cell = new PdfPCell(new Phrase("Nome Piattaforma\n\n"
                               +"EmmeShop"));
cell.setColspan(6);
cell.setHorizontalAlignment(Element.ALIGN_LEFT);
table.addCell(cell);




//prendere la partits iva del negozio
String Piva ="";
Collection<NegozioBean> negozi= model.getAllNegozio();
Iterator<?> it2 = negozi.iterator();
while(it2.hasNext()) {
	NegozioBean n =(NegozioBean)it2.next();
	if(n.getNomeNegozio().equals(fattura.getNomeNegozio())) {
		Piva = n.getPartitaIva();
	}
}
cell = new PdfPCell(new Phrase("Part.Iva\n\n\n"
                               +"IT09767543986"));
cell.setColspan(10);
cell.setHorizontalAlignment(Element.ALIGN_LEFT);
table.addCell(cell);

cell = new PdfPCell(new Phrase("ID Prodotto"));
cell.setColspan(5);
cell.setHorizontalAlignment(Element.ALIGN_LEFT);
table.addCell(cell);

cell = new PdfPCell(new Phrase("Nome Prodotto"));
cell.setColspan(16);
cell.setHorizontalAlignment(Element.ALIGN_LEFT);
table.addCell(cell);

cell = new PdfPCell(new Phrase("Qta"));
cell.setColspan(3);
cell.setHorizontalAlignment(Element.ALIGN_LEFT);
table.addCell(cell);

cell = new PdfPCell(new Phrase("Prezzo"));
cell.setColspan(4);
cell.setHorizontalAlignment(Element.ALIGN_LEFT);
table.addCell(cell);

cell = new PdfPCell(new Phrase("Sc%"));
cell.setColspan(2);
cell.setHorizontalAlignment(Element.ALIGN_LEFT);
table.addCell(cell);

cell = new PdfPCell(new Phrase("Importo"));
cell.setColspan(4);
cell.setHorizontalAlignment(Element.ALIGN_LEFT);
table.addCell(cell);

cell = new PdfPCell(new Phrase("Iva"));
cell.setColspan(3);
cell.setHorizontalAlignment(Element.ALIGN_LEFT);
table.addCell(cell);

String idProdotto = "";
//creare lista di id prodotti
for(int i=0;i<listaRiferimento.getAllRiferimento().size();i++) {
     rif = listaRiferimento.getAllRiferimento().get(i);
	idProdotto+=rif.getId_prodotto()+"\n";
}


//id prodotto
cell = new PdfPCell(new Phrase(idProdotto));
cell.setColspan(5);
cell.setPaddingBottom(150);
cell.setHorizontalAlignment(Element.ALIGN_LEFT);
table.addCell(cell);





//nome dei prodotti
String nomeProdotto="";
for(int i=0;i<listaRiferimento.getAllRiferimento().size();i++) {
     rif = listaRiferimento.getAllRiferimento().get(i);
     ProdottoBean p=model.getProductById(rif.getId_prodotto());
     nomeProdotto+=p.getNome()+"\n";
	
}

cell = new PdfPCell(new Phrase(nomeProdotto));
cell.setColspan(16);
cell.setPaddingBottom(150);
cell.setHorizontalAlignment(Element.ALIGN_LEFT);
table.addCell(cell);

//quantità 
String qta = "";

for(int i=0;i<listaRiferimento.getAllRiferimento().size();i++) {
	rif = listaRiferimento.getAllRiferimento().get(i);
	qta+=rif.getQtaOrdinata()+"\n";
}

cell = new PdfPCell(new Phrase(qta));
cell.setColspan(3);
cell.setHorizontalAlignment(Element.ALIGN_LEFT);
table.addCell(cell);


//prezzo
String prezzo="";
for(int i=0;i<listaRiferimento.getAllRiferimento().size();i++) {
	rif = listaRiferimento.getAllRiferimento().get(i);
	prezzo+=rif.getPrezzoUnitario()+"\n";
}

cell = new PdfPCell(new Phrase(prezzo));
cell.setColspan(4);
cell.setHorizontalAlignment(Element.ALIGN_LEFT);
table.addCell(cell);


//sconto
String sct ="";
for(int i=0;i<listaRiferimento.getAllRiferimento().size();i++) {
rif= listaRiferimento.getAllRiferimento().get(i);
	sct+=rif.getSconto()+"\n";
}

cell = new PdfPCell(new Phrase(sct));
cell.setColspan(2);
cell.setHorizontalAlignment(Element.ALIGN_LEFT);
table.addCell(cell);

//importo prezzoUnitario*qta
String importo = "";
for(int i=0;i<listaRiferimento.getAllRiferimento().size();i++) {
	rif= listaRiferimento.getAllRiferimento().get(i);
	double prezzoUnitario=rif.getPrezzoUnitario();
	int quantita = rif.getQtaOrdinata();
	double impo=prezzoUnitario*quantita;
	String s = Double.toString(impo);
	importo+=s+"\n";

}


cell = new PdfPCell(new Phrase(importo));
cell.setColspan(4);
cell.setHorizontalAlignment(Element.ALIGN_LEFT);
table.addCell(cell);


//iva
String iva ="";
for(int i=0;i<listaRiferimento.getAllRiferimento().size();i++) {
rif= listaRiferimento.getAllRiferimento().get(i);
	iva+=rif.getIva()+"\n";
}


cell = new PdfPCell(new Phrase(iva));
cell.setColspan(3);
cell.setHorizontalAlignment(Element.ALIGN_LEFT);
table.addCell(cell);

//calcolo sconto e importo totale
double scontoTot = 0; double tot=0;
for(int i=0;i<listaRiferimento.getAllRiferimento().size();i++) {
	rif = listaRiferimento.getAllRiferimento().get(i);
	int quanta = rif.getQtaOrdinata();
	float prezUnitario = rif.getPrezzoUnitario();
	float prezTot = prezUnitario*quanta;
	float scto = rif.getSconto();
	int ivaP= rif.getIva();
	float  ivaCalcolo=((prezUnitario*quanta)+(prezUnitario*quanta*ivaP/100));
	float totale=ivaCalcolo-(ivaCalcolo*scto/100);
   
	tot+=(totale);
}


cell = new PdfPCell(new Phrase("Totale Fattura\n\n"
	                          +new DecimalFormat("##.##").format(tot)+"€\n\n"));
cell.setColspan(36);
cell.setHorizontalAlignment(Element.ALIGN_CENTER);
table.addCell(cell);





document.add(table);

















document.close();
DataOutput output = new DataOutputStream(response.getOutputStream());
byte[] bytes=buffer.toByteArray();
response.setContentLength(bytes.length);
for(int i=0;i<bytes.length;i++){
	output.writeByte(bytes[i]);
}
}
	
%>
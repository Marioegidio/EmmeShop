<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="javax.servlet.*,model.*,java.util.*"
    pageEncoding="UTF-8"
    session="true"
%>
<%!
	String negozio = "";
	String categoria = "";
%>

<%@include file="../WEB-INF/utilities/checkLogCliente.jsp" %>

<%
	negozio = request.getParameter("negozio");
	categoria = request.getParameter("categoria");
	UserModelDS model=new UserModelDS();
	Collection<ProdottoBean>  prodotti= model.getAllProductByShopCategory(negozio, categoria);
	NegozioBean negozioBean= (NegozioBean) model.getNegozioByName(negozio);
%>
<!DOCTYPE html>
<html>
    <head>
    
    	
    
    
        <meta charset='utf-8'>
        <meta http-equiv='X-UA-Compatible' content='IE=edge'>
        <title>EmmeShop</title>
        <link rel="icon" href="../images/favicon.ico" />
        <meta name='viewport' content='width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0'>
        
         <%if(negozioBean.getDesign().equals("left")){ %>
         <link rel='stylesheet' type='text/css' href='../css_<%=negozioBean.getColore() %>/sidenav-nav_left/stile-nav_left.css'>
         <link rel='stylesheet' type='text/css' href='../css_<%=negozioBean.getColore() %>/sidenav-nav_left/sidenav-nav_left.css'>
        <% }else if(negozioBean.getDesign().equals("top")){%>
         <link rel='stylesheet' type='text/css' href='../css_<%=negozioBean.getColore() %>/sidenav-nav_top/stile-nav_top.css'>
         <link rel='stylesheet' type='text/css' href='../css_<%=negozioBean.getColore() %>/sidenav-nav_top/sidenav-nav_top.css'>
        <%} %>
        <link rel='stylesheet' type='text/css' href='../css_<%=negozioBean.getColore() %>/step.css'>
        <link rel='stylesheet' type='text/css' href='../css_<%=negozioBean.getColore() %>/popup.css'>
        <link rel='stylesheet' type='text/css' href='../css_<%=negozioBean.getColore() %>/content.css'>
        
        <link rel='stylesheet' type='text/css' href='../css/header.css'>
        <link rel='stylesheet' type='text/css' href='../css/footer.css'>
        <link rel='stylesheet' type='text/css' href='../css/scrollbar.css'>
        <link rel='stylesheet' type='text/css' href='../css/slideshow.css'>
        <link rel='stylesheet' type='text/css' href='../css/translate.css'>
        <link rel='stylesheet' type='text/css' href='../css/table.css'>
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:300,400,500,700" type="text/css">
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script type="text/javascript" src="../js/top.js"></script>
        <%if(negozioBean.getDesign().equals("left")){ %>
         <script type="text/javascript" src="../js/nav/menu-nav_left.js"></script>
         <% }else if(negozioBean.getDesign().equals("top")){%>
         <script type="text/javascript" src="../js/nav/menu-nav_top.js"></script>
         <%} %>
        <script type="text/javascript" src="../js/scrollToDiv.js"></script>
        <script type="text/javascript" src="../js/translate.js"></script>
        <script type="text/javascript" src="../js/check-chrome.js"></script>
        <script type="text/javascript" src="../js/cart.js"></script>
        <script type="text/javascript" src="http://translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>
        
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
    
    </head>
    <body onresize='resize()'  id='home' >
      
      <div id="alertBox"><br>
       <center> 
        <label id="msg"></label><p>
            <input value="ok" id="btnA" type="button" >
       </center>
      </div>
      
    <div id="back">
    </div>
      
      
      
        <header>
           <%@ include file="../WEB-INF/fragment/headernegozio.jsp" %>
                   <script>
                  	$(".logo").html("<img src=\"../<%= negozioBean.getLogo() %>\" alt='Negozio' title='Logo Negozio'><p></p>");
                  </script>
            <div id='mySidenav' class="sidenav">
              <ul >
                <li onclick="window.open('ordini.jsp','_self')">
                    I miei Ordini
                </li>
                <li onclick="window.open('index-cliente.jsp','_self')">
                    Negozi
                </li>
                 <li onclick="window.open('index-cliente-categorie.jsp?negozio=<%=negozio %>','_self')">
                    Categorie
                </li>
                <li>
                     <a href='../Logout' style='padding:0;text-decoration:none;color:#141414;'>Disconnetti</a>
                </li>
               
              </ul>    
            </div>
        </header>
        <div class="content">
                  
             
                  
                  
                    <h2><a href='index-cliente.jsp' >Negozi</a>/<a href='index-cliente-categorie.jsp?negozio=<%=negozio %>' >Categorie</a>/Prodotti</h2>
                  
                  
                  <div class="wrapper" id="#ContentWrapper">
                  
                  <%
                    	 
                    			
						 if (prodotti != null && prodotti.size() != 0) {
								Iterator<?> it = prodotti.iterator();
								int count=0;
								while (it.hasNext()) {
									count++;
									ProdottoBean bean = (ProdottoBean) it.next();
				    %>
                   
                   
                   <div class="product">
                        <form action='' method='POST'>
                          <div class="img">
                            <img src="../<%= bean.getPath() %>" title="<%= bean.getDescrizione() %>">
                          </div>
                          <div class='nomeProdotto'>
                            <span >
                                <%= bean.getNome() %> <%= bean.getPrezzo() %>€
                            </span>
                          </div>
                          <div class='note'>
                              <span>
                                <textarea id="note<%=count %>" placeholder='Note...'></textarea>
                              </span>
                          </div>
                          <div class='qta'>
                            
                            <div class="quantity">
                              <input type="number" min="0"  id="quantita<%=count %>" max="<%= bean.getQuantita() %>" step="1" value="0">
                            </div>
                            <span>
                              <button type='button' class='acquista-btn' onclick="addToCart('<%= negozio %>','<%=categoria%>',<%= bean.getIdProdotto() %>,<%=bean.getQuantita()%>,<%=count%>)">Carrello</button>
                            </span>
                          </div>
                          
                        </form>
                    </div>
                    
                    
                   
                    <% } }%>
                    
                    
                      
                      
                  </div>
                  
                             
                  
                  
                  
              </div>
        
        <%@ include file="../WEB-INF/fragment/footer.jsp" %>
		
		
		
		
	
   		
    </body>
</html>
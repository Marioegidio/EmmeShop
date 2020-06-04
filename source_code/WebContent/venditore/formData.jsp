<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="javax.servlet.*,model.*,java.util.*"
    pageEncoding="UTF-8"
    session="true"
%>
<%@include file="../WEB-INF/utilities/checkLogVenditore.jsp" %>

<%! VenditoreBean venditore=null; %>

<%

    NegozioBean negozio=null;
    UserModelDS model=new UserModelDS();
	venditore = (VenditoreBean)request.getSession().getAttribute("utente");
	if (venditore!=null){
		session.setAttribute("username-venditore",venditore.getUsername());
		negozio=model.getNegozio(venditore.getUsername());
	}
	if(negozio==null){
		 response.sendRedirect("../seller/registrazione-negozio.jsp");
	}
	Collection<CategoriaBean>  categorie=null;
	if(venditore!=null && negozio!=null)   
		{
		  
		  categorie= model.getAllCategoryBySeller(venditore.getUsername());
		
	
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset='utf-8'>
        <meta http-equiv='X-UA-Compatible' content='IE=edge'>
        <title>EmmeShop</title>
        <link rel="icon" href="../images/favicon.ico" />
        <meta name='viewport' content='width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0'>
        <link rel='stylesheet' type='text/css' href='../css/table2.css'>
        <%if(negozio.getDesign().equals("left")){ %>
         <link rel='stylesheet' type='text/css' href='../css/sidenav-nav_left/stile-nav_left.css'>
         <link rel='stylesheet' type='text/css' href='../css/sidenav-nav_left/sidenav-nav_left.css'>
        <% }else if(negozio.getDesign().equals("top")){%>
         <link rel='stylesheet' type='text/css' href='../css/sidenav-nav_top/stile-nav_top.css'>
         <link rel='stylesheet' type='text/css' href='../css/sidenav-nav_top/sidenav-nav_top.css'>
        <%} %>
        <link rel='stylesheet' type='text/css' href='../css/header.css'>
        <link rel='stylesheet' type='text/css' href='../css/content.css'>
        <link rel='stylesheet' type='text/css' href='../css/footer.css'>
        <link rel='stylesheet' type='text/css' href='../css/scrollbar.css'>
        <link rel='stylesheet' type='text/css' href='../css/slideshow.css'>
        <link rel='stylesheet' type='text/css' href='../css/translate.css'>
        <link rel='stylesheet' type='text/css' href='../css/step.css'>
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:300,400,500,700" type="text/css">
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
         <%if(negozio.getDesign().equals("left")){ %>
         <script type="text/javascript" src="../js/nav/menu-nav_left.js"></script>
         <% }else if(negozio.getDesign().equals("top")){%>
         <script type="text/javascript" src="../js/nav/menu-nav_top.js"></script>
         <%} %>
        <script type="text/javascript" src="../js/top.js"></script>
        <script type="text/javascript" src="../js/scrollToDiv.js"></script>
        <script type="text/javascript" src="../js/translate.js"></script>
        <script type="text/javascript" src="../js/check-chrome.js"></script>
        <script type="text/javascript" src="../js/category.js"></script>
        <script type="text/javascript" src="http://translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>
        
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
   
   
   
    </head>
    <body onresize='resize()'  id='home' >
      
        <header>
           <%@ include file="../WEB-INF/fragment/headerseller.jsp" %>
           <script>
             $(".logo").html("<img src=\"../<%= negozio.getLogo() %>\" alt='<%= negozio.getNomeNegozio() %>' title='Logo <%= negozio.getNomeNegozio() %>'><p></p>");
			</script>
                  
            <div id='mySidenav' class="sidenav">
              <ul >
                
                <li onclick="window.open('index-venditore.jsp','_self')">
                    Il Mio Negozio
                 </li>
                  <li onclick="window.open('ordini.jsp','_self')">
                   Tutti gli Ordini 
                </li>
                <li onclick="window.open('formCliente.jsp','_self')">
                   Ordini per Cliente
                </li>
              </ul>    
            </div>
        </header>
        
        <div id='vendi' style='padding-top:10px;'><br><br>
        <form id="regForm1" name="regForm1" action="./ordiniByData.jsp" method="post" style='margin:0 auto;'> 
        	<h2><center>Ricerca fatture per data</center></h2>
        	
        	<div class="tab1">
        	<br><br><br>
        		<table>
        			<tr>
        				<td>
        					<label for="">Data inizio</label>
        				</td>
        				<td>
        					<input type="date" name="dateFrom" >
        				</td>
        			</tr>
        			<tr>
        				<td>
        					<label for="">Data fine</label>
        				</td>
        				<td>
        					<input type="date" name="dateTo">
        				</td>
        			</tr>
        		</table>
        	
        	<br><br><br>
        	<div class='steptab1' style="overflow:auto;">
                      <div style='text-align:center'> 
			                    <button type="button" class="verifica" id="prevBtn1" onclick="nextPrev(-1)">Indietro</button>
				                <button type="submit" class="verifica" id="nextBtn1" onclick="nextPrev(1)">Avanti</button>
              		  </div>
             </div>
         
             
             <br><br><br>
             <!-- Circles which indicates the steps of the form: -->
             
                </form>
                
            </div>  
            <script src='../js/step1.js'></script>
                 
                  <script type="text/javascript">
                 document.getElementById("nextBtn1").innerHTML = "Ricerca";
              	
              </script>
                 
        </div>
        <% }%>
        <%@ include file="../WEB-INF/fragment/footer.jsp" %>

        
      
        
    </body>
</html>
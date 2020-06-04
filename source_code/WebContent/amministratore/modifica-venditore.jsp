<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="javax.servlet.*,model.*,java.util.*"
    pageEncoding="UTF-8"
    session="true"
%>

<%@include file="../WEB-INF/utilities/checkLogAmministratore.jsp" %>
<%! AmministratoreBean amministratore=null; %>

<%

	String username=request.getParameter("username");
	
	UserModelDS model=new UserModelDS();
	VenditoreBean bean=model.getVenditore(username);
	
	
	
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
        <link rel='stylesheet' type='text/css' href='../css/sidenav-nav_top/stile-nav_top.css'>
        <link rel='stylesheet' type='text/css' href='../css/sidenav-nav_top/sidenav-nav_top.css'>
        <link rel='stylesheet' type='text/css' href='../css/header.css'>
        <link rel='stylesheet' type='text/css' href='../css/content.css'>
        <link rel='stylesheet' type='text/css' href='../css/footer.css'>
        <link rel='stylesheet' type='text/css' href='../css/scrollbar.css'>
        <link rel='stylesheet' type='text/css' href='../css/slideshow.css'>
        <link rel='stylesheet' type='text/css' href='../css/translate.css'>
        <link rel='stylesheet' type='text/css' href='../css/step.css'>
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:300,400,500,700" type="text/css">
        <link rel="stylesheet" href='../css/tooltip.css' type="text/css">
        
        <script type="text/javascript" src="../js/espressioni_regolari.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script type="text/javascript" src="../js/nav/menu-nav_top.js"></script>
        <script type="text/javascript" src="../js/top.js"></script>
        <script type="text/javascript" src="../js/scrollToDiv.js"></script>
        <script type="text/javascript" src="../js/translate.js"></script>
        <script type="text/javascript" src="../js/check-chrome.js"></script>
        <script type="text/javascript" src="http://translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>
        <script type="text/javascript" src="../js/espressioni_regolari.js"></script>
        
        
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
    </head>
    <body onresize='resize()'  id='home' > 
    

            <div id='vendi' style='padding-top:10px;'><br><br>
              	<form id="regForm1" name="regForm1" action="../UpdateVenditore" method="POST"   style='margin:0 auto;'> <!--Fase di realizzazione-->
                  <h2><center>Modifica Venditore</center></h2><!-- Deve comparire in ogni statement -->
                  
                    <div class="tab1"> 
                    	<table>
                    		<tr>
                    				<td>
             			  
						                      <label for="Name:">Nome</label>
						            </td>
						            <td>
						                          <input type="text" name="fname" onblur="isName(document.regForm1.fname)" value="<%=bean.getNome() %>" placeholder="Nome..." >
						            </td>
									<td>
						                       	 	<div class="tooltip"><img class="imageInfo" src="../images/info.png" width="20px" >	
						  								<span class="tooltiptext">Nome formato: solo lettere.<br> Esempio:Max</span>
													</div>
									</td>
								</tr>
								<tr>
									<td>				
											<label for="Name:">Cognome </label>
									</td>
									<td>
						                          <input type="text" name="lname" onblur="isName(document.regForm1.lname)" value="<%= bean.getCognome() %>" placeholder="Cognome..." >
						            </td>
						            <td>
						                   	     <div class="tooltip"><img class="imageInfo" src="../images/info.png" width="20px" >	
						  							<span class="tooltiptext">Cognome formato: solo lettere.<br>Esempio: Super</span>
												</div> 
												
												
												<input type="hidden" name="gender" value="<%=bean.getSesso() %>" > 
									</td>
							         
							    </tr>
							    <tr>
							    	<td>          
						                    <label for="Name:">Email </label>
						            </td>
						            <td>
						                          <input type="email" name="email" onblur="isEmail(document.regForm1.email)" value="<%=bean.getEmail() %>" placeholder="E-mail...">
						            </td>
						            <td>
						                       	 <div class="tooltip"><img class="imageInfo" src="../images/info.png" width="20px" >	
						  								<span class="tooltiptext"> Email formato: <br> una sola @.<br>Esempio: max@gmail.com</span>
													</div> 	
									</td>
								</tr>	
								<tr>
									<td>			
											<label for="Name:">telefono </label>
								    </td>
								    <td>
						                  	      <input type="tel" name="phone" onblur="isPhoneNumber(document.regForm1.phone)" value="<%=bean.getTelefono() %>" placeholder="Telefono...">
						            </td>
						            <td>    
						                    	    <div class="tooltip"><img class="imageInfo" src="../images/info.png" width="20px" >	
						  								<span class="tooltiptext"> Tel formato: 10 cifre.<br>Senza spazi<br>Esempio: 3334567895</span>
													</div>  
									</td>
						        </tr>
						        <tr>
						        		<td>    
						                      <label for="Name:">Strada </label>
						                </td>
						                <td>
						                           <input type="text" name="street" onblur="isStreet(document.regForm1.street)" value="<%= bean.getVia() %>" placeholder="Via*">
						               	</td>
						               	<td>
						                 	       <div class="tooltip"><img class="imageInfo" src="../images/info.png" width="20px" >	
						  								<span class="tooltiptext"> Strada formato: lettere e cifre <br> senza limiti.<br>Esempio: Via Giovanni XVII</span>
													</div> 
										</td>
								</tr>
								<tr>
										<td>					
											<label for="Name:">Citt&agrave;</label>
										</td>
										<td>
						                          <input type="text" name="city" onblur="isStreet(document.regForm1.city)" value="<%= bean.getCitta() %>" placeholder="Citt&agrave*">
						                </td>
						                <td>    
											        <div class="tooltip"><img class="imageInfo" src="../images/info.png" width="20px" >	
						  								<span class="tooltiptext"> Città formato: lettere e cifre <br> senza limiti.<br>Esempio: Casagiove</span>
													</div>  
										</td>
								</tr>
								<tr>
									<td>			
											<label for="Name:">Cap </label>
						             </td>
						             <td>
						                      <input type="text" name="CAP" onblur="isCap(document.regForm1.CAP)" value="<%= bean.getCap() %>" placeholder="CAP*">
						             </td>
						             <td>
						        	                <div class="tooltip"><img class="imageInfo" src="../images/info.png" width="20px" >	
						  								<span class="tooltiptext"> Cap formato:5 cifre<br>Esempio: 55555</span>	
													</div>  
									 </td>
								</tr>
								<tr>
									 <td>		
						                    <label for="Name:">Username</label>
						             </td>
							         <td>
							                     <input type="text" name="username" value="<%= bean.getUsername() %>" placeholder="Username*" readonly>
						             </td>
						             <td>
						                    	    <div class="tooltip"><img class="imageInfo" src="../images/info.png" width="20px" >	
						  								<span class="tooltiptext"> Campo automatico</span>
													</div> 
									</td>
								</tr>
								<tr>
									<td>			
											<label for="Name:">Password</label>
								    </td>
								    <td>
						                          <input type="password" name="password" onblur="isPassword(document.regForm1.password)" value="<%= bean.getPassword() %>" placeholder="Password*">
						            </td>
						            <td>
						                	        <div class="tooltip"><img class="imageInfo" src="../images/info.png" width="20px" >	
						  								<span class="tooltiptext"> Password formato: almeno lettera minuscola e maiuscola, un numero, carattere speciale <br> min 6, max 25.<br>Esempio: suPer9#</span>
													</div>  
									</td>
								</tr>
							</table>          	
							             	
              		  </div>
	             <br><br><br>
	                <div class='steptab1' style="overflow:auto;">
	                  <div style='text-align:center'>
	                  		<button type="button" class="verifica" id="prevBtn1" onclick="nextPrev(-1)">Indietro</button>
							<button type="submit" class="verifica" id="nextBtn1" onclick="nextPrev(1)">Avanti</button>&nbsp;&nbsp;&nbsp;
					  </div>
              
             <!-- Circles which indicates the steps of the form: -->
             
                </form>
                
            </div>  
            <script src='../js/step1.js'></script>
              <script type="text/javascript">
                 document.getElementById("nextBtn1").innerHTML = "Invia Modifiche";
              	
              </script>
        
        <br>   <br>
        </div>
     	<%@ include file='../WEB-INF/fragment/footer.jsp' %>
        <div onclick='topFunction()' id='topbutton' class='gotop'><img src="../images/top.png"></div>
        </div>
              
      
        
    </body>
</html>
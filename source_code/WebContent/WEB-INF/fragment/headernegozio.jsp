<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="javax.servlet.*,model.*,java.util.*"
    pageEncoding="UTF-8"
    session="true"
%>


<div class="header">
                        
    <div class='menu' id='menubar' onclick="openNav();"  >
        <span  id='menu-symbol' class='symbol'><i class="fas fa-bars"></i></span><!--&#9776; -->
        <span  id='menu-x' class='symbol-x'><i class="fas fa-times"></i></span>
    </div>  
    <div class='logo'>
        <img src='<%=getServletContext().getContextPath() %>/images/user.png' alt='Nome Negozio' title='Logo Nome Negozio'> 
        <p></p> 
    </div>
    <div class='cerca'>
    <!-- <form action=''>
            <input type="text" name='cerca' class='text-search' placeholder="cerca...">
    </form> --> 
    </div>
    <div class='cart' onclick="">
        <span><i class="fas fa-shopping-cart"></i></span><span class='number'></span><!--&#128722;-->
    </div>
    
    
    <%
  		ArrayProdotti cart=null;
    	synchronized(session){
     	    cart=(ArrayProdotti)session.getAttribute("cart");	
     	} 
    		
    		if(cart!=null){
				%>
					<script type="text/javascript">  
						$( ".number" ).text("<%= cart.getArrayProd().size() %>");
					</script>
				<%
					}else{
		   		%>     
			   		<script type="text/javascript">  
						$( ".number" ).text("0");
					</script>
		   		<%
					}
		   		%>
		   		
		   		<script>
			   		$( ".cart" ).click(function(){
			   			window.open("cart.jsp","_self");
			   		});
		   		</script>
		  
    
</div>
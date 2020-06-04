<!DOCTYPE html>
<html lang="it-IT">
    <head>
        <meta charset='utf-8'>
        <meta http-equiv='X-UA-Compatible' content='IE=edge'>
        <title>EmmeShop</title>
        <link rel="icon" href="images/favicon.ico" />
        <meta name='viewport' content='width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0,'>
        <link rel='stylesheet' type='text/css'  href='./css/table.css'>
        <link rel='stylesheet' type='text/css'  href='./css/sidenav-nav_left/stile-nav_left.css'>
        <link rel='stylesheet' type='text/css'  href='./css/sidenav-nav_left/sidenav-nav_left.css'>
        <link rel='stylesheet' type='text/css'  href='./css/header.css'>
        <link rel='stylesheet' type='text/css'  href='./css/content.css'>
        <link rel='stylesheet' type='text/css'  href='./css/footer.css'>
        <link rel='stylesheet' type='text/css'  href='./css/scrollbar.css'>
        <link rel='stylesheet' type='text/css'  href='./css/translate.css'>
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:300,400,500,700" type="text/css">
        
       
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script type="text/javascript" src="js/nav/menu-nav_left.js"></script>
        <script type="text/javascript" src="js/top.js"></script>
        <script type="text/javascript" src="js/scrollToDiv.js"></script>
        <script type="text/javascript" src="js/translate.js"></script>
        <script type="text/javascript" src="js/check-chrome.js"></script>
        <script type="text/javascript" src="http://translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>
        

        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">

    </head>
    <body onresize='resize()'>
      
        <header>
          <%@ include file="./WEB-INF/fragment/headernegozio.jsp" %>
            <div id='mySidenav' class="sidenav">
              <ul >
                  <li><i class="fas fa-user"></i><a href="#">Profilo</a></li>
                  <li><i class="fas fa-info-circle"></i><a href="#">Negozio</a></li>
                  <li><i class="fas fa-sign-out-alt"></i> <a href='#'>Logout</a></li>
              </ul>    
            </div>
        </header>
        <div class="content">
                  <div class='select-sort'>
                    	<label for="sort">Ordina per </label>
                      <select name="sort" id="sort" class="">
                        <option class='select-item' value="Nome-asc">Nome </option>
                        <option class='select-item' value="Nome-desc">Nome </option>
                        <option class='select-item' value="Prezzo-asc">Prezzsddsdsdsccdsdco</option>
                        <option class='select-item' value="Prezzo-desc">Prezzo</option>
                      </select>
                  </div>
                  <div class="wrapper" id="#ContentWrapper">
                    <div class="product">
                        <form action='' method='POST'>
                          <div class="img">
                            <img src="images/1.png">
                          </div>
                          <div class='nomeProdotto'>
                            <span >
                                Prodotto 0
                            </span>
                          </div>
                          <div class='note'>
                              <span>
                                <textarea placeholder='Note...'></textarea>
                              </span>
                          </div>
                          <div class='qta'>
                            
                            <div class="quantity">
                              <input type="number" min="1" max="99" step="1" value="1">
                            </div>
                            <span>
                              <button type='button' class='acquista-btn'>Acquista</button>
                            </span>
                          </div>
                          
                        </form>
                    </div>
                    <div class="product">
                        <form action='' method='POST'>
                          <div class="img">
                            <img src="images/1.png">
                          </div>
                          <div class='nomeProdotto'>
                            <span >
                                Prodotto 0
                            </span>
                          </div>
                          <div class='note'>
                              <span>
                                <textarea placeholder='Note...'></textarea>
                              </span>
                          </div>
                          <div class='qta'>
                            
                            <div class="quantity">
                              <input type="number" min="1" max="99" step="1" value="1">
                            </div>
                            <span>
                              <button type='button' class='acquista-btn'>Acquista</button>
                            </span>
                          </div>
                          
                        </form>
                    </div>
                    <div class="product">
                        <form action='' method='POST'>
                          <div class="img">
                            <img src="images/1.png">
                          </div>
                          <div class='nomeProdotto'>
                            <span >
                                Prodotto 0
                            </span>
                          </div>
                          <div class='note'>
                              <span>
                                <textarea placeholder='Note...'></textarea>
                              </span>
                          </div>
                          <div class='qta'>
                            
                            <div class="quantity">
                              <input type="number" min="1" max="99" step="1" value="1">
                            </div>
                            <span>
                              <button type='button' class='acquista-btn'>Acquista</button>
                            </span>
                          </div>
                          
                        </form>
                    </div>
                    <div class="product">
                        <form action='' method='POST'>
                          <div class="img">
                            <img src="images/1.png">
                          </div>
                          <div class='nomeProdotto'>
                            <span >
                                Prodotto 0
                            </span>
                          </div>
                          <div class='note'>
                              <span>
                                <textarea placeholder='Note...'></textarea>
                              </span>
                          </div>
                          <div class='qta'>
                            
                            <div class="quantity">
                              <input type="number" min="1" max="99" step="1" value="1">
                            </div>
                            <span>
                              <button type='button' class='acquista-btn'>Acquista</button>
                            </span>
                          </div>
                          
                        </form>
                    </div>
                    <div class="product">
                        <form action='' method='POST'>
                          <div class="img">
                            <img src="images/3.png">
                          </div>
                          <div class='nomeProdotto'>
                            <span >
                                Prodotto 0
                            </span>
                          </div>
                          <div class='note'>
                              <span>
                                <textarea placeholder='Note...'></textarea>
                              </span>
                          </div>
                          <div class='qta'>
                            
                            <div class="quantity">
                              <input type="number" min="1" max="99" step="1" value="1">
                            </div>
                            <div>
                              <button type='button' class='acquista-btn'>Acquista</button>
                            </div>
                          </div>
                          
                        </form>
                    </div>
                    <div class="product">
                        <form action='' method='POST'>
                          <div class="img">
                            <img src="images/1.png">
                          </div>
                          <div class='nomeProdotto'>
                            <span >
                                Prodotto 0
                            </span>
                          </div>
                          <div class='note'>
                              <span>
                                <textarea placeholder='Note...'></textarea>
                              </span>
                          </div>
                          <div class='qta'>
                            
                            <div class="quantity">
                              <input type="number" min="1" max="99" step="1" value="1">
                            </div>
                            <span>
                              <button type='button' class='acquista-btn'>Acquista</button>
                            </span>
                          </div>
                          
                        </form>
                    </div>
                      
                      
                  </div>
                  
                  <br><br><br><br>
                  <caption>Tabella 1</caption>
                    <table>
                            <thead>
                              <tr>
                                <th>Payment</th>
                                <th>Issue Date</th>
                                <th>Amount</th>
                                <th>Period</th>
                              </tr>
                            </thead>
                            <tbody>
                              <tr>
                                <td data-label="Payment">Payment #1</td>
                                <td data-label="Issue Date">02/01/2015</td>
                                <td data-label="Amount">$2,311</td>
                                <td data-label="Period">01/01/2015 - 01/31/2015</td>
                              </tr>
                              <tr>
                                <td data-label="Payment">Payment #2</td>
                                <td data-label="Issue Date">03/01/2015</td>
                                <td data-label="Amount">$3,211</td>
                                <td data-label="Period">02/01/2015 - 02/28/2015</td>
                              </tr>
                            </tbody>
                          </table>
                          <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
                                      
                  
                  
                  
                  
                    
                          
            
        </div>
        <%@ include file="./WEB-INF/fragment/footer.jsp" %>
        
        <script type="text/javascript" src="js/quantity.js"></script>
        
    </body>
</html>
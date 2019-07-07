<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Auditoria Saída</title>
        <meta charset="UTF-8">        
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link href="css/home.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    </head>
    <body>
        <%
            String usuario = (String) session.getAttribute("usuario");
            if(usuario == null){
                response.sendRedirect("index.jsp");
            }
        %>
      <nav class="navbar navbar-expand-md navbar-dark bg-dark mb-4">
        <a class="navbar-brand" href="home.jsp">
            <img src="./img/logo.png" width="30" height="30" alt="logo oficina">
        </a>
      
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarCollapse">
        <ul class="navbar-nav mr-auto">
            
          <li class="nav-item active">
            <a class="nav-link" href="home.jsp">Home</a>
          </li>
          
          <li class="nav-item active dropdown">
            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-toggle="dropdown" arial-labelledby="navbarDropdown">Listar</a>                  
            <div class="dropdown-menu" arial-labelledby="navbarDropdown">
                <a class="dropdown-item" href="PecaServlet?id=listar">Peças</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="FuncionarioServlet?id=listar">Funcionários</a>                
            </div>
          </li>
          
           <li class="nav-item active dropdown">
            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-toggle="dropdown" arial-labelledby="navbarDropdown">Cadastrar</a>                            
            <div class="dropdown-menu" arial-labelledby="navbarDropdown">
                <a class="dropdown-item" href="cadastroPeca.jsp">Peça</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="cadastroFuncionario.jsp">Funcionário</a>  
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="cadastroUsuario.jsp">Usuário</a>
            </div>
           </li> 
           
          <li class="nav-item active dropdown">
            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-toggle="dropdown" arial-labelledby="navbarDropdown">Auditoria</a>                            
            <div class="dropdown-menu" arial-labelledby="navbarDropdown">
                <a class="dropdown-item" href="auditoria.jsp">Ações realizadas em Peças</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="auditoriaEntrada.jsp">Entrada de Peça</a>
                 <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="auditoriaSaida.jsp">Saída de Peça</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="auditoriaEntradaSaida.jsp">Entrada e Saída de Peça</a>
            </div>
           </li>
           
              <li class="nav-item active">
                <a class="nav-link" href="Entrada.jsp">Entrada</a>
              </li>
              <li class="nav-item active">
                <a class="nav-link" href="Saida.jsp">Saída</a>
              </li>
              
           <li>   
               <div class="container">
                    <button type="submit" class="btn btn-link navbar-btn navbar-danger" data-toggle="modal" data-target="#myModal">Logout</button>
                    <div class="modal fade" id="myModal" role="dialog">
                        <div class="modal-dialog modal-sm">
                          <div class="modal-content">
                            <div class="modal-header">
                              <button type="button" class="close" data-dismiss="modal">&times;</button>
                              <!--h4>Logout</h4-->
                            </div>
                            <div class="modal-body">
                              <p align="center">Deseja realmente deslogar?</p>
                            </div>
                            <div class="modal-footer" align="center">
                                <form action="LogoutServlet" method="post">
                                    <button type="submit" class="btn btn-success" data-dismiss="">Sim</button>
                               </form>
                                <button type="button" class="btn btn-danger" data-dismiss="modal">Não</button>                                
                            </div>
                          </div>
                        </div>
                    </div> 
                </div> 
            </li>
                        
            
            <li>
                <a class="nav-link disabled" href="#">Usuário: <% out.print(usuario);%> </a>
            </li>
        </ul>           
      </div>
    </nav>  

     
    <div align="center">
            <iframe align="center" src="AuditoriaServlet?id=Saida" style="width: 800px; height: 500px;" >
                
            </iframe>
    </div>  
            
            
    <!-- Footer -->
    <footer class="page-footer font-small blue">

      <!-- Copyright -->
        <div class="footer-copyright py-3">
        © 2019 Copyright:
        <a href="https://github.com/Davi151/Oficina">https://github.com/Davi151/Oficina</a>
      </div>
      <!-- Copyright -->

    </footer>
    <!-- Footer -->
     
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>    
    </body>    
</html>
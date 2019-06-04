<%-- 
    Document   : Funcionario
    Created on : 03/06/2019, 04:03:57
    Author     : rande
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Pojo.FuncionarioPojo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="css/peca.css" rel="stylesheet" type="text/css">
        <title>Tabela Funcionário</title>
    </head>
    <body>            
        <%           
            String usuario = (String) session.getAttribute("usuario");
            ArrayList<FuncionarioPojo> listFuncionarioPojo = (ArrayList<FuncionarioPojo>) request.getAttribute("listFuncionarioPojo");                                                                        
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
            </div>
           </li> 
           
            <li>
                <form action="LogoutServlet" method="post">
                    <button type="submit" class="btn btn-link navbar-btn navbar-danger">Logout</button>
                </form>
            </li>
  
            <li>                                    
                <a class="nav-link disabled" href="#">Usuário: <% out.print(usuario);%> </a>
            </li>
            
          </ul>                  
        </div>
      </nav>        
            
    <h3 ALIGN="center">Tabela de Funcionários</h3>    
    <div class="container scrollable">
        <div class="table-responsive">
            <%
            if(usuario == null){                
                response.sendRedirect("index.html");
            }else{                                                              
            %>
            <table class="table table-bordered table-hover table-sm bg-light text-center table-striepd">
                <thead>
                    
                  <tr>
                    <th scope="col">Identificador</th>
                    <th scope="col">Nome</th>
                    <th scope="col">CPF</th>                    
                  </tr>
                </thead>

                <tbody>
                <% for (int i = 0; i < listFuncionarioPojo.size(); i++) { %>
                    <tr>          
                     <% FuncionarioPojo fc = listFuncionarioPojo.get(i); %>
                     <td><%out.print(fc.getF_ID());%></td>
                      <td><%out.print(fc.getF_NOME());%></td>
                      <td><%out.print(fc.getF_CPF());%></td>                      
                    </tr>        
                  <%} %>
                </tbody>
          </table>
          <% } %>
        </div>
    </div>
                      
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script src="./jquery/jquery-3.4.1.min.js"></script>
    <script src="./jquery/jquery.freezeheader.js"></script>
    <script>
        $(document).ready(function () {
            $("table").freezeHeader({ 'height': '300px' });
        });
    </script>
    </body>
</html>

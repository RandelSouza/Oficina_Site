
<%@page import="java.util.Iterator"%>
<%@page import="Pojo.PecaPojo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Dao.PecaDao"%>
<%
    PecaDao pecaDao = new PecaDao();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="css/peca.css" rel="stylesheet" type="text/css">
        <title>Tabela Peça</title>        
    </head>
    <body>
        <%
            String usuario = (String) session.getAttribute("usuario");                        
            
            ArrayList<PecaPojo> listPecaPojo = (ArrayList<PecaPojo>) request.getAttribute("listPecaPojo");                                    
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
            
    <h3 ALIGN="center">Tabela de Peças</h3>    
    <div class="container scrollable">
        <div class="table-responsive">
            <%
            if(usuario == null){
                response.sendRedirect("index.jsp");
            }else{                 
            %>
            
            <table class="table table-bordered table-hover scrollable table-sm bg-light text-center table-striepd">
                <thead>
                  <tr>
                    <th scope="col">Identificador</th>
                    <th scope="col">Nome</th>
                    <th scope="col">Preço</th>
                    <th scope="col">Unidade</th>
                    <th scope="col">Valor Total</th>
                    <th scope="col">Ações</th>
                  </tr>
                </thead>

                <tbody>
                <% for (int i = 0; i < listPecaPojo.size(); i++) { %>
                    <tr>          
                     <% PecaPojo pc = listPecaPojo.get(i); %>
                     <td><%out.print(pc.getP_ID());%></td>
                      <td><%out.print(pc.getP_NOME());%></td>
                      <td><%out.print(pc.getP_PRECO());%></td>
                      <td><%out.print(pc.getP_UNIDADE());%></td>        
                      <td><%out.print(pc.getP_VALOR_TOTAL());%></td>        
                      <td>
                          <style>
                              .mesmo-tamanho{
                                  width: 100%;                           
                              }                                                           
                          </style>
                          <!--Botão editar-->
                          <div class="row" ALIGN="center">                          
                              
                            <div class="col">
                              <button type="button" class="btn btn-xs btn-primary mesmo-tamanho" data-toggle="modal" data-target="#exampleModal" data-whatever="<% out.print(pc.getP_ID()); %>"  data-whatevernome="<% out.print(pc.getP_NOME()); %>" data-whateverpreco="<% out.print(pc.getP_PRECO()); %>" data-whateverunidade="<% out.print(pc.getP_UNIDADE()); %>">
                                Editar
                              </button>
                            </div>
                          <!--Botão editar-->    
                          
                           <div class="col">                               
                               <button type="submit" class="btn btn-xs btn-danger mesmo-tamanho" data-toggle="modal" data-target="#myModalExcluir<% out.print(pc.getP_ID()); %>">Excluir</button>                                    
                                
                               <div class="modal fade" id="myModalExcluir<%out.print(pc.getP_ID()); %>" role="dialog">
                                   <div class="modal-dialog modal-sm">
                                       <div class="modal-content">
                                           <div class="modal-body">
                                               <p align="left">Deseja Realmente Excluir a Peça?</p>
                                               <p align="left">Identificador da Peça: <% out.print(pc.getP_ID()); %>.</p>
                                               <p align="left">Nome da Peça: <% out.print(pc.getP_NOME()); %>.</p>
                                               <p align="left">Preço da Peça: <% out.print(pc.getP_PRECO()); %>.</p>
                                               <p align="left">Unidade da Peça: <% out.print(pc.getP_UNIDADE()); %>.</p>
                                               <p align="left">Valor Total da Peça: <% out.print(pc.getP_VALOR_TOTAL()); %>.</p>
                                           </div>
                                           
                                           <div class="modal-footer" align="center">
                                                <form action="PecaServlet?id=excluir" method="post">
                                                    <input type="hidden" id="identificador" value="<% out.print(pc.getP_ID()); %>" name="identificador">
                                                     <button type="submit" class="btn btn-success" data-dismiss="">Sim</button>
                                                </form>  
                                                    <button type="button" class="btn btn-danger" data-dismiss="modal">Não</button>                                
                                           </div>
                                       </div> 
                                   </div>
                               </div>                                                                                                        
                            </div>                                                              
                          </div>
                      </td>        
                    </tr>                                           
                  <%} %>                                     
                </tbody>
          </table>
          <% } %>
        </div>
    </div>
            
    <!--MODAL TEMPLATE-->
    <div class="modal fade" id="exampleModal"  role="dialog" aria-labelledby="exampleModalLabel">
      <div class="modal-dialog" role="document">
            <div class="modal-content">
              
              <div class="modal-header">
                      <h4 class="modal-title" id="exampleModalLabel">Editar Peça </h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                
              </div>
                
              <div class="modal-body">                      
                  <form method="get" action="">
                      
                      <div class="form-group">
                            <label for="id_peca2" class="control-label">Identificador:</label>
                            <input readonly name="id_peca2" type="text" class="form-control" id="id_peca2">
                      </div>
                      
                  <div class="form-group">
                            <label for="recipient-name" class="control-label">Nome:</label>
                            <input name="recipient-name" type="text" class="form-control" id="recipient-name">
                      </div>
                        
                      <div class="form-group">
                            <label for="preco" class="control-label">Preco:</label>
                            <input type="number" min=".00" step=".01"  name="preco" class="form-control" id="preco">
                      </div>
                        
                      <div class="form-group">
                            <label for="unidade" class="control-label">Unidade:</label>
                            <input readonly name="unidade" class="form-control" id="unidade">
                      </div>
                    
                    <input name="id" type="hidden" class="form-control" id="idnf" value="editar">
                    <input name="id-peca" type="hidden" class="form-control" id="id-peca" value="">
                                                          
                     <button class="btn btn-success">Salvar</button>                     
                     <button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>                                        
                    </form>
              </div>

            </div>
      </div>
    </div>               
       
    <!-- Footer -->
    <footer class="page-footer font-small blue">
      <!-- Copyright -->
        <div class="footer-copyright footer py-3">
        © 2019 Copyright:
        <a href="https://github.com/Davi151/Oficina">https://github.com/Davi151/Oficina</a>
      </div>
      <!-- Copyright -->

    </footer>
    <!-- Footer -->
        
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <!--script src="./jquery/jquery-3.4.1.min.js"></script-->
    <script src="./jquery/jquery.freezeheader.js"></script>   
    <script>
        /*$(document).ready(function () {
            $("table").freezeHeader({ 'height': '300px' });
        });
         */
    </script>
    <script>
       	$('#exampleModal').on('show.bs.modal', function (event) {
		  var button = $(event.relatedTarget) // Button that triggered the modal
		  var recipient = button.data('whatever') // Extract info from data-* attributes
		  var recipientnome = button.data('whatevernome')
		  var recipientpreco = button.data('whateverpreco')
                  var recipientunidade = button.data('whateverunidade')
		  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
		  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
		  var modal = $(this)
		  
                  //modal.find('.modal-title').text('ID ' + recipient)
		  modal.find('#id-peca').val(recipient)
		  modal.find('#recipient-name').val(recipientnome)
		  modal.find('#preco').val(recipientpreco)
                  modal.find('#unidade').val(recipientunidade)
                  modal.find('#id_peca2').val(recipient)
		})
    </script>
    
    </body>
</html>
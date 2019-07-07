/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function enviarCampos(){
   var nome = document.getElementById('recipient-name').value;
   var preco = document.getElementById('preco').value;
   var unidade = document.getElementById('unidade').value;   
   var identificador = document.getElementById('id-peca').value;
   
   window.location.href = "PecaServlet2?id=editar&nome="+nome+"&preco="+preco+"&unidade="+unidade+"&identificador="+identificador+"";
}

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="br.com.helton.entity.Servico" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><% if(request.getAttribute("servico") == null) { %>Cadastrar Serviço<% } else { %>Atualizar Serviço<% } %></title>
</head>
<body>

    <% 
        Servico servico = null;
        Object servicoObj = request.getAttribute("servico");
        if (servicoObj instanceof Servico) {
            servico = (Servico) servicoObj;
        }
    %>

    <h1><% if(servico == null) { %>Cadastrar um novo serviço<% } else { %>Atualizar serviço<% } %></h1>

    <form action="app" method="post">

        <input type="hidden" name="acao" value="<%=(servico != null ? "atualizarServico" : "cadastrarServico")%>">
        
        <input type="hidden" name="id" value="<%=(servico != null ? servico.getId() : "")%>">
        
        Nome: <input name="nome" placeholder="Nome do serviço" type="text" required value="<%=(servico != null ? servico.getNome() : "")%>">
        <br>
        
        Descrição: <input name="descricao" placeholder="Descrição do serviço" type="text" required value="<%=(servico != null ? servico.getDescricao() : "")%>">
        <br>

        Preço: <input name="valor" placeholder="Preço do serviço" type="number" step="0.01" required value="<%=(servico != null ? servico.getValor() : "")%>">
        <br>

        <button>Finalizar</button>
    </form>

</body>
</html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
			<title>Cadastro de Produtos - Alisson</title>
			<link href="css/bootstrap.css" rel="stylesheet">
			<link href="css/login.css" rel="stylesheet">
			<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet">
		</head>
		
		<body>
			<div class="container">
				<p style="text-align: center"><a href="index.jsp">Index</a></p>
				<form action="mvc?logica=InserirProduto" method="post">
					<hr style="border-color: black;">
					<c:if test="${not empty produto}">
						<h2 style="text-align: center;">Alterar Produto: ${produto.descricao}</h2>
						<p><input type="hidden" name="codigo" value="${produto.codigo}" /></p>
					</c:if>
					
					<c:if test="${empty produto}">
						<center><h2>Adicionar Produto:</h2></center>
					</c:if>
					
					<hr style="border-color: black;">
					<div class="form-group">
						<center><p><label for="descricao">Nome: </label><input type="text"
							id="descricao" name="descricao"
							value="${produto.descricao}" placeholder="Nome do Produto"></p></center>
					</div>
					
					<br>
					<div class="form-group">
						<center><p><label for="preco">Preço: </label><input type="text"
							 id="preco" name="preco"
							value="${produto.preco}" placeholder="Preço do Produto"></p></center>
					</div>
					
					<br>
					<div class="selecao">
						<center><label for="preco">Fornecedores: </label></center>
							<c:if test="${not empty produto}">
								<c:set var="verificador" value="1"/> 
								
								<c:forEach var="fornecedor" items="${fornecedores}">
								
									<c:forEach var="fornprod" items="${produto.fornecedores}">
										<c:if test="${fornecedor.codigo==fornprod.codigo}">
											<center><label><input type="checkbox" value="${fornecedor.codigo}" name="fornecedores" checked> 
											${fornecedor.razaoS}</label></center>
											<c:set var="verificador" value="2"/> 
										</c:if>
									</c:forEach>
									
									<c:choose>
										<c:when test="${verificador==1}">
										<center><label><input type="checkbox" value="${fornecedor.codigo}" name="fornecedores"> 
										${fornecedor.razaoS}</label></center>
										</c:when>
										
										<c:when test="${verificador==2}">
											<c:set var="verificador" value="1"/>
										</c:when>
									</c:choose>
		
								</c:forEach>
							
							</c:if>
							
							<c:if test="${empty produto}">
								<c:forEach var="fornecedor" items="${fornecedores}">
									<center><label><input type="checkbox" value="${fornecedor.codigo}" name="fornecedores"> 
									${fornecedor.razaoS}</label></center>
								</c:forEach>
							</c:if>
						
						<!-- 
							<input type="checkbox" value="1" name="fornecedores"> FBI bebidas</label>
							<input type="checkbox" value="2" name="fornecedores"> Carva Fornos LTDA</label>
							
							ou
							
							<select name="fornecedores">
								<option value="1">FBI bebidas</option>
								<option value="2">Carva Fornos LTDA</option>
							</select> 
						-->
						
					</div>
					
					<br>
					<p style="text-align: center;"><button type="submit">Adicionar Produto >></button></p>
					<br>
					<hr style="border-color: black;">
					
				</form>	
				
				<br>
				
				<c:if test="${not empty produtos}">
					<div class="container">
						<h2 style="text-align: center">Lista de Produtos</h2>
						<table class="text-center table table-bordered table-responsive">
						<thead>
								<tr>
									<th class="text-center">Código </th>
									<th class="text-center">Descrição </th>
									<th class="text-center">Preço </th>
									<th class="text-center"><i class="fa fa-cog"></i></th>
								</tr>
							</thead>
							<tbody>
								
								<c:forEach var="produto" items="${produtos}">
									<tr>
										<td>${produto.codigo}</td>
										<td>${produto.descricao}</td>
										<td>${produto.preco}</td>
										
										<td><a class="btn btn-success"
											href="mvc?logica=BuscarProduto&&codigo=${produto.codigo}"
											title="Alterar Produto"><i class="fa fa-refresh"></i></a> <i
											class="fa fa-arrows-h"></i> <a class="btn btn-danger"
											href="mvc?logica=RemoverProduto&&codigo=${produto.codigo}"
											title="Deletar Produto"><i class="fa fa-trash"></i></a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</c:if>
				<br>
				<c:if test="${empty produtos}">
					<h1 style="text-center: center;">Sem produtos cadastrados!</h1>
				</c:if>
			
			</div>
		</body>
</html>
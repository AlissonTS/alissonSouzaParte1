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
						<h2 style="text-align: center;">Adicionar Produto:</h2>
					</c:if>
					
					<hr style="border-color: black;">
					<div class="form-group">
						<p><label for="descricao">Descrição: </label><input type="text"
							id="descricao" name="descricao"
							value="${produto.descricao}" placeholder="Descricao"></p>
					</div>
					
					<div class="form-group">
						<p><label for="preco">Preço: </label><input type="text"
							 id="preco" name="preco"
							value="${produto.preco}" placeholder="Preco">
						</p>
					</div>
					<div class="selecao"><label for="preco">Fornecedores: </label>
						<select name="fornecedores">
							<option value="1">FBI bebidas</option>
							<option value="2">Carva Fornos LTDA</option>
						</select>
					</div>
					
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
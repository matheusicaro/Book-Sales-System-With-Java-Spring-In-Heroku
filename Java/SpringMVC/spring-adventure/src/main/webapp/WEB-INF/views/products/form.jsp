<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Adventure In Spring - GitHub: @matheusicaro</title>

<c:url value="/" var="cssPath" />
<link rel="stylesheet" href="${cssPath}/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="${cssPath}/resources/css/bootstrap-theme.min.css">
<style type="text/css">
body {
	padding-top: 60px;
}
</style>
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${s:mvcUrl('HC#home').build()}">
					HOME </a>
			</div>
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="${s:mvcUrl('PC#productsList').build()}"> Lista de Produtos </a></li>
					<li><a href="${s:mvcUrl('PC#form').build()}"> Cadastro de Produtos </a></li>
				</ul>
			</div>
		</div>
	</nav>

<div class="container">
<h1>Cadastro de Produto</h1>
	<form:form action="${s:mvcUrl('PC#save').build()}" method="POST"
		commandName="product" enctype="multipart/form-data">

		<div class="form-group">
			<label>Título</label>
			<form:input path="title" cssClass="form-control"/>
			<form:errors path="title" />
		</div>
		<div class="form-group">
			<label>Descrição</label>
			<form:textarea path="description" cssClass="form-control"/>
			<form:errors path="description" />

		</div>
		<div class="form-group">
			<label>Páginas</label>
			<form:input path="pages" />
			<form:errors path="pages" />

		</div>

		<div cssClass="form-group">
			<label>Data de Lançamento</label>
			<form:input path="dateLaunch" cssClass="form-control" />
			<form:errors path="dateLaunch" />
		</div>

		<c:forEach items="${typesBook}" var="EnumTypeBook" varStatus="status">
			<div class="form-group">
				<label>${EnumTypeBook}</label>
				<form:input path="prices[${status.index}].value"  cssClass="form-control"/>
				<form:hidden path="prices[${status.index}].type"
					value="${EnumTypeBook}" />
			</div>
		</c:forEach>

		<div class="form-group">
			<label>Sumário</label> <input name="sumaryFile" type="file" class="form-control" />
		</div>

		<button type="submit" class="btn btn-primary">Cadastrar</button>

	</form:form>
	</div>
</body>
</html>
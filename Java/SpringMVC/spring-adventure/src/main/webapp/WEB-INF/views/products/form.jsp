<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Adventure In Spring</title>
</head>
<body>

<form:form action="${s:mvcUrl('PC#save').build()}" method="POST" commandName="product">

        <div>
            <label>Título</label>
            <input type="text" name="title" />
            <form:errors path="title" />
        </div>
        <div>
            <label>Descrição</label>
            <textarea rows="10" cols="20" name="description" ></textarea>
            <form:errors path="description" />
            
        </div>									
        <div>
            <label>Páginas</label>
            <input type="text" name="pages" />
                        <form:errors path="pages" />
            
        </div>
    
	    <c:forEach items="${typesBook}" var="EnumTypeBook" varStatus="status">
		    <div>
		        <label>${EnumTypeBook}</label>
		        <input type="text" name="prices[${status.index}].value">
		        <input type="hidden" name="prices[${status.index}].type" value="${EnumTypeBook}">
		    </div>
		</c:forEach>
    
            <button type="submit">Cadastrar</button>
    
	</form:form>
</body>
</html>
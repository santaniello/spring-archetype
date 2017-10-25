<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<tags:pageTemplate titulo="Home">
	<div class="container">
		 <h3 style="font-weight: bold;">Formulario de Usuários</h3>
		 <br />
<%--     	 <form:form action="${ s:mvcUrl('UC#gravar').build() }" method="POST" commandName="usuario"> --%>
			 <form:form action="${pageContext.request.contextPath}/usuarios" method="POST" commandName="usuario" >	
			  <div class="form-group">
			    <label for="nome">Nome:</label>
			    <form:input path="nome" cssClass="form-control" />
       			<form:errors path="nome" />
			  </div>
			  <div class="form-group">
			    <label for="email">Email:</label>
			    <form:input path="email" cssClass="form-control" />
       			<form:errors path="email" />
			  </div>			  
			  <div class="form-group">
			    <label for="senha">Senha:</label>
			     <form:input type="password" path="senha" cssClass="form-control" />
       			<form:errors path="senha" />
			  </div>			  
			  <button type="submit" class="btn btn-default">Submit</button>
		 </form:form>    	
    </div><!-- /.container -->
</tags:pageTemplate>


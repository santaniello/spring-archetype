<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %> 

<tags:pageTemplate titulo="Erro">
	<!-- 
        Mensagem: ${exception.message}
        <c:forEach items="${exception.stackTrace}" var="stk">
            ${stk}
        </c:forEach>    
    -->
    <div class="container">
      <div class="starter-template">
        <h1>Erro</h1>
      </div>
    </div><!-- /.container -->
</tags:pageTemplate>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="titulo" required="true" %>
<%@ attribute name="bodyClass" required="false" %>
<%@ attribute name="extraScripts" fragment="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
    <title>${titulo}</title>       
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/bootstrap/css/bootstrap.min.css"> 
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">   
</head>
<body class="${bodyClass}">

<%@ include file="/WEB-INF/views/template/cabecalho.jsp" %>

<!-- ************** conteúdo da pagina ****************** -->

<!-- a JSP provê uma tag que permite que no corpo da tag, quando usado, seja incorporado 
     o conteúdo envolvido pela mesma. -->
     
<jsp:doBody />

<!-- Uma necessidade comum em alguns projetos web é que determinados scripts sejam executados 
     em apenas algumas páginas. Imaginenos que ao entrar na página de finalização da compra de
     um produto, queremos que um determinado script seja executado, para isso, usamos os fragmentos
     da JSP.  -->
<jsp:invoke fragment="extraScripts" />

<%@ include file="/WEB-INF/views/template/rodape.jsp" %>

</body>
</html>

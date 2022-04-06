<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="commandfast" tagdir="/WEB-INF/tags" %>
<%@page pageEncoding="UTF-8"%>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<commandfast:layout pageName="wait"> 
    <div class="row">
        <div >
            <h2 class="img-title p-3">Operación realizada con éxito</h2>
            <spring:url value="/resources/images/esperar.png" htmlEscape="true" var="waitPago"/>
            <img style="width: 24%; height: 80%" class="img-responsive p-3" src="${waitPago}"/>
            <h2 class="img-footer mt-2">Su cuenta está en camino</h2>
        </div>
    </div>
</commandfast:layout>
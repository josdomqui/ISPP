<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<petclinic:layout pageName="wait"> 
    <div class="row">
        <div align="center">
            <h2>Operación realizada con éxito</h2>
            <spring:url value="/resources/images/waiting.png" htmlEscape="true" var="waitPago"/>
            <img style="width: 30%; height: 90%" class="img-responsive" src="${waitPago}"/>
            <h2>Su cuenta está en camino</h2>
        </div>
    </div>
</petclinic:layout>
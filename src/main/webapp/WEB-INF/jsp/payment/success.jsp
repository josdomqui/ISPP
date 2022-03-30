<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="successPage"> 
    <div class="row">
        <div align="center">
            <h2>Su pago se ha realizado con �xito</h2>
            <spring:url value="/resources/images/pago.png" htmlEscape="true" var="checkPago"/>
            <img style="width: 30%; height: 90%" class="img-responsive" src="${checkPago}"/>
        </div>
    </div>
</petclinic:layout>
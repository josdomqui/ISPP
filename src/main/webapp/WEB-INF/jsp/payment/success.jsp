<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<petclinic:layout pageName="successPage"> 
    <div class="row">
        <div align="center">
            <h2 class="img-title p-3">Su pago se ha realizado con éxito</h2>
            <spring:url value="/resources/images/pago exitoso.png" htmlEscape="true" var="checkPago"/>
            <img style="width: 25%; height: 85%" class="img-responsive" src="${checkPago}"/>
            <h2 class="img-footer mt-4">¡Buen provecho!</h2>
        </div>
    </div>
</petclinic:layout>
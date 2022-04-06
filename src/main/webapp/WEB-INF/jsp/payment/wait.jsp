<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<petclinic:layout pageName="wait"> 
    <div class="row">
        <div >
            <h2 class="img-title p-3">Operaci�n realizada con �xito</h2>
            <spring:url value="/resources/images/esperar.png" htmlEscape="true" var="waitPago"/>
            <img style="width: 24%; height: 80%" class="img-responsive p-3" src="${waitPago}" alt="Imagen de espera al pago"/>
            <h2 class="img-footer mt-2">Su cuenta est� en camino</h2>
        </div>
    </div>
</petclinic:layout>
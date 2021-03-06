<%@ page trimDirectiveWhitespaces="true" %>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="commandfast" tagdir="/WEB-INF/tags" %>
<%@page pageEncoding="UTF-8"%>

<commandfast:layout pageName="successPage"> 
    <div class="row">
        <div>
            <h2 class="img-title p-3">Su pago se ha realizado con éxito</h2>
            <spring:url value="/resources/images/pago exitoso.png" htmlEscape="true" var="checkPago"/>
            <img style="width: 25%; height: 85%" class="img-responsive" src="${checkPago}" alt="Imagen del pago"/>
            <h2 class="img-footer mt-4 mb-4">!Buen provecho!</h2>
            <spring:url value="/payment/downloadRecipt/{id_comanda}" var="url">
            	<spring:param name="id_comanda" value="${id_comanda}"/>
            </spring:url>
            <a class="btn-pedir" id="submitButton" style="text-decoration: none; background-color: #ffcb74; color: black; font-size: 16px" href="${fn:escapeXml(url)}">
                Descargar recibo
            </a>
        </div>
    </div>
</commandfast:layout>
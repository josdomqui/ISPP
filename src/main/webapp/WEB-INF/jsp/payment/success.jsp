<%@ page trimDirectiveWhitespaces="true" %>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<petclinic:layout pageName="successPage"> 
    <div class="row">
        <div>
            <h2>Su pago se ha realizado con �xito</h2>
            <spring:url value="/resources/images/pago.png" htmlEscape="true" var="checkPago"/>
            <img style="width: 30%; height: 90%" class="img-responsive" src="${checkPago}"/>
            
            <spring:url value="/payment/downloadRecipt/{id_comanda}" var="url">
	                      <spring:param name="id_comanda" value="${id_comanda}"/>
	                    </spring:url>
                        <a class="btn btn-primary btn-block" id="submitButton" href="${fn:escapeXml(url)}">
                            Descargar recibo
                        </a>
        </div>
    </div>
</petclinic:layout>
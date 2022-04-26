<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="commandfast" tagdir="/WEB-INF/tags" %>
<%@ page import="org.springframework.samples.commandfast.plate.Plate" %>

<commandfast:layout pageName="paymentsRestaurantes">

	<div class="container">
		<div class="row">
			<h1 class="col-md-12 p-2">Mesas que solicitan camarero:</h1>
		   	<div class="card-deck">
		   	<c:choose>
			   	<c:when test="${notificaciones.size() == 0}">
			   		<p>No hay notificaciones</p>
			   	</c:when>
			   	<c:otherwise>
			   		<c:forEach items="${notificaciones}" var="noti">
			   		<c:if test="${noti.atendido == 0}">
			   		<div class="col-xs-12 col-md-3">
			    				<div class="card-body mb-3" style="background-color: rgba(158, 172, 168, 0.5); border-radius:10px">
			    					<h2 class="card-title"><c:out value = "Mesa"/></h2>
									<p class="card-text"><c:out value="${noti.numeroMesa}"/></p>
									<spring:url value="/restaurante/notify/clear/{id_notification}" var="url">
						          		<spring:param name="id_notification" value="${noti.id}"/>
									</spring:url>																																		
						   			<a class="btn-pedir" href="${fn:escapeXml(url)}" style="text-decoration: none; color: black; font-size: 16px;">Marcar como atendida</a>
								</div>		    				
			   				</div> 
			   		</c:if>
			   		</c:forEach>
			   	</c:otherwise>
		   	</c:choose>
		   	</div>
		</div>
	</div>
</commandfast:layout>

    					
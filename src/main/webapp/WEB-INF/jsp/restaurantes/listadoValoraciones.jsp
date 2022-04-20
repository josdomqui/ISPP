<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="commandfast" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<commandfast:layout pageName="listado-valoraciones">
	
	<div class="container py-5">
		<h1 class="display-3 fw-bolder" style="color: #ffff; font-size: 30px;"><c:out value = "${restaurante.name}"/></h1>
		<h3 class="display-3 fw-bolder" style="color: #ffff; font-size: 30px;"><c:out value = "Valoraciones"/></h3>
		<c:forEach items="${listaValoraciones}" var="valoraciones">
			<p>Puntuación: <c:out value="${valoraciones.puntuacion}"></c:out></p>
			<p>Opinión: <c:out value="${valoraciones.opinion}"></c:out></p>
			<br>
		</c:forEach>
		<spring:url value="/restaurante/{id2}/valoracion" var="url2">
			<spring:param name="id2" value="${restaurante.id}" />
		</spring:url>
		<p>
			<a type="button" class="buton-detalles-listado"href="${fn:escapeXml(url2)}"
			style="text-decoration: none; color: black; font-size: 16px;">¡Déjanos tu opinión!</a>
		</p>
	</div>
</commandfast:layout>
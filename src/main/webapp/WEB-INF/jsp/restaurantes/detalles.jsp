<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<petclinic:layout pageName="detalles-restaurante">
	
<div class="container py-5">
	<div class="row min-vh-50 align-items-center">
		<div class="row" style="margin-left: 20.1%;">
            <div class="col-md-3">
              <img src="${detallesRestaurante.photo}" class="rounded" alt="..." style="object-fit: cover; height: 100%; width: 100%; float:left; margin:10px;">
            </div>
		<div class="col-md-3" style="margin-left: 5%; margin-top: 5%;">	
			<div class="lc-block mb-4">
				<div editable="rich">
					<h2 class="display-3 fw-bolder" style="color: #ffff; font-size: 30px;"><c:out value = "${detallesRestaurante.name}"/></h2>
					<h1 class="display-3 fw-bolder" style="color: #ffff; font-size: 20px;"><c:out value = "${detallesRestaurante.schedule}"/></h2><br>
					<p><c:out value = "${detallesRestaurante.description}"/></p><br>
				</div>
		
				<div>
					<spring:url value="/restaurante/{id}/detalles/carta" var="menUrl">
							<spring:param name="id" value="${detallesRestaurante.id}"/>
					</spring:url>
					<p><a type="button" class="buton-detalles-listado" href="${fn:escapeXml(menUrl)}" style="text-decoration: none; color: #ffff; font-size: 14px;">Ver carta</a></p>
				</div>																								

			</div><!-- /lc-block -->
		</div><!-- /col -->
	</div>
	</div>
</div>

<div class="container">
	<div class="row align-items-center" style="margin-left: 21.2%;">
		<div class="col-md-8" >
			<div class="lc-block mb-4">
				<div class="ratio ratio-4x3 min-vh-50" lc-helper="gmap-embed">
					<iframe title="Localización del restaurante" src="https://maps.google.com/maps?q=<c:out value = "${detallesRestaurante.address}"/>&amp;t=m&amp;z=8&amp;output=embed&amp;iwloc=near"></iframe>
				</div>
				<div class="ratio-4x3 min-vh-50" style="background: #ffcb74;">
					<h1 ALIGN = center ><c:out value = "${detallesRestaurante.address}"/> &nbsp;&nbsp;&nbsp;<c:out value = "${detallesRestaurante.telephone}"/></h1>
				</div>
			</div><!-- /lc-block -->
		
	</div>
</div>
</petclinic:layout>
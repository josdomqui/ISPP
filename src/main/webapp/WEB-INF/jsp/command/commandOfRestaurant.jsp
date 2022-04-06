<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page import="org.springframework.samples.commandfast.plate.Plate" %>

<petclinic:layout pageName="commandRestaurant">

	<div class="container">
		<div class="row">
			<h1 class="col-md-12 p-2">Comandas:</h1>
			<c:choose>
			<c:when test="${vacio == true}">
				<span style="color: white">No dispone de comandas actualmente</span>
		   	</c:when>
		   	<c:otherwise>
		   		<c:forEach items="${listaComandas}" var="comanda">
		   			<div class="card-deck" style="background-color: white; width: 50%; margin: 10px">
		  				<span style="color: black">Número de la mesa: <c:out value="${comanda.mesa.id}"/></span><br/>
			   			<span style="color: black">Número de comensales: <c:out value="${comanda.costumers}"/></span><br/>
			   			<span style="color: black">Precio total: <c:out value="${comanda.price}"/></span><br/>
			   			<span style="color: black">Platos:</span><br/>
			   				<c:forEach items="${comanda.lines}" var="linea">
			   					<span style="color: black"><c:out value="${linea.plate.name}...........${linea.quantity}"/></span><br/>
			   				</c:forEach>
		   			</div>
		   		</c:forEach>
		   	</c:otherwise>
		   	</c:choose>
		   	
		   	
		</div>
	</div>
 			
</petclinic:layout>
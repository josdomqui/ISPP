<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page import="org.springframework.samples.commandfast.plate.Plate" %>

<petclinic:layout pageName="paymentsRestaurantes">

	<div class="container">
		<div class="row">
			<h1 class="col-md-12 p-2">Mesas con pago pendiente en efectivo</h1>
		   	<div class="card-deck">
		   	<c:choose>
		   		<c:when test="${conEfectivoVacio==true}">
		   		<span style="color: white">No hay cuentas a pagar en efectivo</span>
		   		</c:when>
		   		<c:otherwise>
		  		<c:forEach items="${conEfectivo}" var="payment">
			   				<div class="col-xs-12 col-md-3">
			    				<div class="card-body mb-3" style="background-color: rgba(158, 172, 168, 0.5); border-radius:10px">
			    					<h2 class="card-title"><c:out value = "Mesa"/></h2>
									<p class="card-text"><c:out value="${payment.table.number}"/></p>
									<h2 class="card-title"><c:out value = "Importe"/></h2>
									<p class="card-text"><c:out value="${payment.amount}"/> &euro;</p>
								</div>		    				
			   				</div>  					
		  		</c:forEach>
		  		</c:otherwise>
		  	</c:choose>
		   	</div>
		</div>
		
		<div class="row">   
			<h1 class="col-md-12 p-2">Mesas con pago pendiente con tarjeta</h1>
			
			<div class="card-deck" >
			<c:choose>
				<c:when test="${conTarjetaVacio==true}">
		   		<span style="color: white">No hay cuentas a pagar con tarjeta</span>
		   		</c:when>
		   		<c:otherwise>
		  		<c:forEach items="${conTarjeta}" var="payment">
			   				<div class="col-xs-12 col-md-3">
			    				<div class="card-body mb-3" style="background-color: rgba(158, 172, 168, 0.5); border-radius:10px">
			    					<h2 class="card-title"><c:out value = "Mesa"/></h2>
									<p class="card-text"><c:out value="${payment.table.number}"/></p>
									<h2 class="card-title"><c:out value = "Importe"/></h2>
									<p class="card-text"><c:out value="${payment.amount}"/> &euro;</p>
								</div>
			   				</div>
		  		</c:forEach>
		  		</c:otherwise>
		  	</c:choose>
	 		</div>
	 	</div>
	</div>
</petclinic:layout>

    					
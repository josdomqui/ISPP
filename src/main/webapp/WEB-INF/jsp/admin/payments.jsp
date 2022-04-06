<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="commandfast" tagdir="/WEB-INF/tags" %>
<%@ page import="org.springframework.samples.commandfast.plate.Plate" %>

<commandfast:layout pageName="ticket">

	<div class="container">
		<div class="row">
			<h1 class="col-md-12 p-2">Mesas con pago pendiente en efectivo</h1>
			   
		   	<div class="card-deck">
		  		<c:forEach items="${payments}" var="payment">
			   		<c:if test="${payment.payHere==true}">
			   			<c:if test="${payment.creditCard==false}">
			   				<div class="col-xs-12 col-md-3">
			    				<div class="card-body mb-3" style="background-color: rgba(158, 172, 168, 0.5); border-radius:10px">
			    					<h2 class="card-title"><c:out value = "Mesa"/></h2>
									<p class="card-text"><c:out value="${payment.table.number}"/></p>
									<h2 class="card-title"><c:out value = "Importe"/></h2>
									<p class="card-text"><c:out value="${payment.amount}"/> &euro;</p>
								</div>		    				
			   				</div>
			   			</c:if>
			   		</c:if>   					
		  		</c:forEach>
		   	</div>
		</div>
		
		<div class="row">   
			<h1 class="col-md-12 p-2">Mesas con pago pendiente con tarjeta</h1>
			
			<div class="card-deck" >
		  		<c:forEach items="${payments}" var="payment">
			   		<c:if test="${payment.payHere==true}">
			   			<c:if test="${payment.creditCard==true}">
			   				<div class="col-xs-12 col-md-3">
			    				<div class="card-body mb-3" style="background-color: rgba(158, 172, 168, 0.5); border-radius:10px">
			    					<h2 class="card-title"><c:out value = "Mesa"/></h2>
									<p class="card-text"><c:out value="${payment.table.number}"/></p>
									<h2 class="card-title"><c:out value = "Importe"/></h2>
									<p class="card-text"><c:out value="${payment.amount}"/> &euro;</p>
								</div>		    				
			   				</div>
			   			</c:if>
			   		</c:if>   					
		  		</c:forEach>
	 		</div>
	 	</div>
	</div>
 			
</commandfast:layout>

    					
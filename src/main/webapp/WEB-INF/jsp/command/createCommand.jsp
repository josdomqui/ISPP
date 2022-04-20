<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="commandfast" tagdir="/WEB-INF/tags" %>

<commandfast:layout pageName="commands">
<form:form modelAttribute="command" class="form-horizontal" id="add-command-form">
    <div class="form-group has-feedback">
       	<div class="container-carrito">
    		<div class="card mb-3" style="border: 2px solid; background-color: rgba(158, 172, 168, 0.5); border-radius:10px; ">
    		<h1 class="card-title mt-3 mb-3 text-center"><strong>Nuevo pedido</strong></h1>
    		
    		<div class="row mt-2">
       			<div class="col-xs-3 col-md-4" style="display: flex; align-items: center;">
       				<p class="card-text p-3">Personas:</p>
       			</div>
   				<div class="col-xs-9 col-md-8 mt-3" >
   					<input type="hidden" name="state" value="true" class="input-filtros" style="color: black; font-size: 18px;"/>	
       				<input required type="number" max="4" min="1" name="costumers" class="input-filtros" style="color: black; font-size: 18px;"/>
       			</div>
           	</div>
    		<div class="row mb-3">
	            	<div class="col-xs-4 col-md-4">
	        			<p class="card-text p-3">Numero de mesa:</p>
	            	</div>
	            	<div class="col-xs-10 col-md-7 mt-2 mb-2">
            			<select required name="mesa" class="input-filtros" style=" font-size: 18px;">
            				<c:forEach items="${mesas}" var="mesa">
  								<option value="${mesa.number}"><c:out value = "${mesa.number}"/></option>
  							</c:forEach>
						</select>
	           		</div>
        		</div>
        	
        	<div class="row mb-3">
	            	<div class="col-xs-4 col-md-4">
	        			<p class="card-text p-3">Restaurante:</p>
	            	</div>
	            	<div class="col-xs-10 col-md-7 mt-2 mb-2">
            			<select required name="restaurante" class="input-filtros" style=" font-size: 18px;">
            				<c:forEach items="${restaurantes}" var="restaurante">
  								<option value="${restaurante.id}"><c:out value = "${restaurante.name}"/></option>
  							</c:forEach>
						</select>
	           		</div>
        		</div>
        		
        	</div>
        	
    	</div>
    </div>
    
    <div class="form-group">
    	<div class="button" style="text-align: center">
        	<p><button class="btn-pedir" type="submit" style= "text-decoration: none; color: black; font-size: 16px; padding: 0 20px;">Pedir</button></p>
        </div>
   	</div>
        
</form:form>
</commandfast:layout>

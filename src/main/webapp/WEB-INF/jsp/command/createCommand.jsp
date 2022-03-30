<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="commands">
<form:form modelAttribute="command" class="form-horizontal" id="add-command-form">
    <div class="form-group has-feedback">
       	<div class="container-carrito">
    		<div class="card mb-3" style="border: 2px solid; background-color: rgba(158, 172, 168, 0.5); border-radius:10px; ">
    		<h2 class="card-title mt-3 mb-3 text-center"><strong>Nuevo pedido</strong></h2>
    		
    		<div class="row">
       			<div class="col-xs-2 col-lg-3">
       				<p class="card-text p-3">Personas: </p>
       			</div>
   				<div class="col-xs-6 col-lg-9" style="width: 60%">		
       				<petclinic:inputField label ="" name="costumers"/>
       			</div>
           	</div>
    		<div class="row mb-3">
	            	<div class="col-xs-3 col-lg-4">
	        			<p class="card-text p-3">Número de mesa: </p>
	            	</div>
	            	<div class="col-xs-4 col-lg-8 p-3">
            			<select name="mesa" style="background-color: white; border-radius:4px; height: 80%; width: 30%; ">
            				<c:forEach items="${mesas}" var="mesa">
  								<option value="${mesa.number}"><c:out value = "${mesa.number}"/></option>
  							</c:forEach>
						</select>
	           		</div>
        		</div>
        	</div>
        	
    	</div>
    </div>
    
    <div class="form-group">
    	<div class="button" style="text-align: center">
        	<button class="btn-pedir" type="submit" style= "text-decoration: none; color: #ffff; font-size: 14px;">Pedir</button>
        </div>
   	</div>
        
</form:form>
</petclinic:layout>

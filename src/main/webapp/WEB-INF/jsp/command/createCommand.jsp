<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="commands">
<form:form modelAttribute="command" class="form-horizontal" id="add-command-form">
<<<<<<< HEAD
    <div class="form-group has-feedback">
       	<div class="container-carrito">
    		<div class="card mb-3" style="border: 2px solid; background-color: rgba(158, 172, 168, 0.5); border-radius:10px; ">
    		<h1 class="card-title mt-3 mb-3 text-center"><strong>Nuevo pedido</strong></h1>
    		
    		<div class="row mt-2">
       			<div class="col-xs-2 col-md-3">
       				<p class="card-text p-3">Personas:</p>
       			</div>
   				<div class="col-xs-10 col-md-9" style="width: 60%; margin-left: 5%">		
       				<petclinic:inputField label ="" name="costumers"/>
       			</div>
           	</div>
    		<div class="row mb-3">
	            	<div class="col-xs-5 col-md-5">
	        			<p class="card-text p-3">Número de mesa:</p>
	            	</div>
	            	<div class="col-xs-10 col-md-7 mt-2 mb-2">
            			<select name="mesa" style="background-color: white; border-radius:4px; height: 100%; width: 75px; margin-left: 5%">
=======
    <div class="container mt-5 mb-5" style="position: absolute; width: 30%; left: 50%; top: 30%; transform: translate(-50%, -50%);">
    <h5 class="card-header fs-1" style="padding: 10px;">Nuevo pedido</h5>
    
    <div class="card mb-3" style="height: 150px;">
    
        <div class="form-group has-feedback">
        	<div class="row g-0" style="padding: 25px; background-color: black;">
        		<br/>
        		<br/>
        		<div style="display: flex; margin-left: 50px;">
        			<span>Personas: </span>
        				<div style="width: 50%;">
            				<input required type="number" label="" name="costumers"/>
            			</div>
            	</div>
            	<div style="display: flex; margin-left: 50px;">
        			<span>Numero de mesa: </span>
            			<select required name="mesa" style="background-color: white; border-radius:4px; width: 32.2%; margin-left: 10px; height: 120%">
>>>>>>> origin/CI
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

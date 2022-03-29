<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="commands">
<form:form modelAttribute="command" class="form-horizontal" id="add-command-form">
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
            				<petclinic:inputField label="" name="costumers"/>
            			</div>
            	</div>
            	<div style="display: flex; margin-left: 50px;">
        			<span>Numero de mesa: </span>
            			<select name="mesa" style="background-color: white; border-radius:4px; width: 32.2%; margin-left: 10px; height: 120%">
            				<c:forEach items="${mesas}" var="mesa">
  								<option value="${mesa.number}"><c:out value = "${mesa.number}"/></option>
  							</c:forEach>
						</select>
				
           		</div>
           	
        		
        	</div>
        	</div>
    	</div>
    	<div class="form-group" style="position: absolute; left: 50%; top: 100%; transform: translate(-50%, -50%);">
            		<div class="col-sm-offset-2 col-sm-10">
            			<button class="btn-default" type="submit"><span style="color: white;">Pedir</span></button>
            		</div>
        </div>
    </div>
</form:form>
</petclinic:layout>

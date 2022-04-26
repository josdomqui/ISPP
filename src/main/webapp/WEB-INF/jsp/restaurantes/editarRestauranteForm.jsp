<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="commandfast" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<commandfast:layout pageName="restaurante">
    <div class="container">
    <h2>
        <strong>Editar Restaurante</strong>
    </h2>
    <div class="card mb-3" style="border: 2px solid; background-color: rgba(158, 172, 168, 0.5); border-radius:10px; width: 100%; ">
    <form:form modelAttribute="restaurante" class="form-horizontal" id="add-restaurant-form">
        
        <div class="form-group has-feedback">
			
			<c:if test="${error}"><span style="color: red"><c:out value="Ya existe un usuario con este nombre"/></span><br/></c:if>

			<input type="hidden" name="user.username" value="${restaurante.user.username}">
			<petclinic:inputField label="Contraseña" name="user.password" /> 
						
			<petclinic:inputField label="Nombre" name="name" />    
			           
			<petclinic:inputField label="Dirección de correo" name="email" />  
			              
			<petclinic:inputField label="Telefono" name="telephone" />                
            
			<petclinic:inputField label="Ciudad" name="city" />                
            
			<petclinic:inputField label="Dirección" name="address" />                
            
			<petclinic:inputField label="Descripción" name="description" />                
            
			<petclinic:inputField label="Capacidad del restaurante" name="capacity" /> 
			
			<petclinic:inputField label="Horarios" name="schedule" /> 
     
            <petclinic:selectField label="Tipos de restaurantes" name="type" size="3" names="${listaTipos}"/>
			
			<c:if test="${error_tipos == true}"><span style="color: red"><c:out value="Debe seleccionar algun tipo de restaurante."/></span><br/></c:if>
			
			<br/>
			<br/>

        <button class="buton-detalles-listado" type="submit" style="text-decoration: none; color: black; font-size: 17px;">Confirmar Cambios</button>
         </form:form>
    </div>
    
</div>
</commandfast:layout>
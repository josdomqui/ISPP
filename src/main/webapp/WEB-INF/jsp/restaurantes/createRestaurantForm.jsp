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
        <strong><c:if test="${restaurante['new']}">Nuevo </c:if> Restaurante</strong>
    </h2>
    <div class="card mb-3" style="border: 2px solid; background-color: rgba(158, 172, 168, 0.5); border-radius:10px; width: 100%; ">
    <form:form modelAttribute="restaurante" class="form-horizontal" id="add-restaurant-form">
        
        <div class="form-group has-feedback" style="margin: 20px;">

			<petclinic:inputField label="Usuario" name="user.username"/>
			<input type="hidden" name="valoracionMedia" value="0.0">
			<c:if test="${error}"><span style="color: red"><c:out value="Ya existe un usuario con este nombre"/></span><br/></c:if>
			
			<spring:bind path="user.password">
			 <c:set var="cssGroup" value="form-group ${status.error ? 'has-error' : '' }"/>
    		 <c:set var="valid" value="${not status.error and not empty status.actualValue}"/>
    		 <div class="${cssGroup}">
			<c:if test="${status.error}">
			<label  class="col-sm-2 control-label" style="color:red">Contraseña</label>
			</c:if>
			<c:if test="${!status.error}">
			<label class="col-sm-2 control-label" >Contraseña</label>
			</c:if>
			
			<div class="col-sm-10">
			<input  class="form-control input-filtros" type="password" name="user.password"/> 
			<c:if test="${status.error}">
                <span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
                <span class="help-inline">${status.errorMessage}</span>
            </c:if>
			
			
			</div>
			</div>
			</spring:bind>
			
			
			
			
			<petclinic:inputField label="Nombre" name="name"/>    
			           
			<petclinic:inputField label="Dirección de correo" name="email"/>  
			              
			<petclinic:inputField label="Telefono" name="telephone"/>                
            
			<petclinic:inputField label="Ciudad" name="city"/>                
            
			<petclinic:inputField label="Dirección" name="address"/>                
            
			<petclinic:inputField label="Descripción" name="description"/>                
            
			<petclinic:inputField label="Capacidad del restaurante" name="capacity"/> 
			
			<petclinic:inputField label="Horarios" name="schedule"/> 
     
            <petclinic:selectField label="Tipos de restaurantes" name="type" size="3" names="${listaTipos}"/>
			
			<c:if test="${error_tipos == true}"><span style="color: red"><c:out value="Debe seleccionar algun tipo de restaurante."/></span><br/></c:if>
			
			<br/>
			<br/>
			<span class="input-group" style="margin-left: 5%;">**Si desea añadir una foto contacte con nosotros indicandonos el nombre de restaurante y la foto que desee</span><br/>
        </div>

        <div class="form-group">
            <div class="col-sm-12" style="text-align: center; margin-top: 5px;">
                <c:choose>
                    <c:when test="${restaurante['new']}">
                        <button class="buton-detalles-listado" type="submit" style="text-decoration: none; color: black; font-size: 17px;">Registrar Restaurante</button>
                    </c:when>
                </c:choose>
            </div>
        </div>
         </form:form>
    </div>
    
</div>
</commandfast:layout>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="commandfast" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<commandfast:layout pageName="restaurant">
    <div class="container">
    <h2>
        <strong><c:if test="${restaurant['new']}">Nuevo </c:if> Restaurante</strong>
    </h2>
    <div class="card mb-3" style="border: 2px solid; background-color: rgba(158, 172, 168, 0.5); border-radius:10px; width: 100%; ">
    <form:form modelAttribute="restaurant" class="form-horizontal" id="add-restaurant-form">
        
        <div class="form-group has-feedback">

            <span class="input-group" style="margin-left: 5%;margin-top: 5px;">Usuario: </span><input required type="text" class="form-control" style="width: 90%; margin-left: 5%;" id="username" name="user.username"/><br/>
            <c:if test="${error}"><span style="color: red; margin-left: 5%"><c:out value="Ya existe un usuario con este nombre"/></span><br/></c:if>
            
            <span class="input-group" style="margin-left: 5%;">Contraseña: </span><input required type="password" class="form-control" style="width: 90%; margin-left: 5%;" name="user.password"/><br/>
            
            <span class="input-group" style="margin-left: 5%;">Nombre: </span><input required pattern="^[ÑÁÉÍÓÚA-Z][a-zñáéíóú]+(\s+[ÑÁÉÍÓÚA-Z]?[a-zñáéíóú]+)*$" type="text" class="form-control" style="width: 90%; margin-left: 5%;" minlength="3" maxlength="50" name="name" placeholder="Introduce siguiente formato: José Antonio"/><br/>
           
            <span class="input-group" style="margin-left: 5%;">Direccion de correo: </span><input required type="email" class="form-control" style="width: 90%; margin-left: 5%;" name="email"/><br/>
            
            <span class="input-group" style="margin-left: 5%;">Telefono : </span><input required type="tel" class="form-control" style="width: 90%; margin-left: 5%;" pattern="[0-9]{9}" name="telephone"/><br/>
            
            <span class="input-group" style="margin-left: 5%;">Ciudad: </span><input required pattern="^[ÑÁÉÍÓÚA-Z][a-zñáéíóú]+(\s+[ÑÁÉÍÓÚA-Z]?[a-zñáéíóú]+)*$" type="text" class="form-control" style="width: 90%; margin-left: 5%;" name="city"/><br/>
            
            <span class="input-group" style="margin-left: 5%;">Direccion: </span><input required type="text" class="form-control" style="width: 90%; margin-left: 5%;" name="address"/><br/>
            
            <span class="input-group" style="margin-left: 5%;">Descripcion: </span><input required minlength="25" maxlength="250" class="form-control" style="width: 90%; margin-left: 5%;" name="description"/><br/>
            
            <!--<span class="input-group" style="margin-left: 5%;">Url de la foto: </span><input label="URL Photo" type="text" name="photo" class="form-control" style="width: 90%; margin-left: 5%;"/><br/>-->
            <span class="input-group" style="margin-left: 5%;">Capacidad del restaurante: </span><input required type="number" class="form-control" style="width: 90%; margin-left: 5%;" min="0" max="10000" name="capacity"/><br/>
            <span class="input-group" style="margin-left: 5%;">Horarios: </span><input label="Schedule" pattern="^((([0-1]{1}[0-9]{1}|[1-2]{1}[0-3]{1}):[0-5]{1}[0-9]{1}))-((([0-1]{1}[0-9]{1}|[1-2]{1}[0-3]{1}):[0-5]{1}[0-9]{1})) y ((([0-1]{1}[0-9]{1}|[1-2]{1}[0-3]{1}):[0-5]{1}[0-9]{1}))-((([0-1]{1}[0-9]{1}|[1-2]{1}[0-3]{1}):[0-5]{1}[0-9]{1}))|^((([0-1]{1}[0-9]{1}|[1-2]{1}[0-3]{1}):[0-5]{1}[0-9]{1}))-((([0-1]{1}[0-9]{1}|[1-2]{1}[0-3]{1}):[0-5]{1}[0-9]{1}))" 
            placeholder="hh:mm-hh:mm o hh:mm-hh:mm y hh:mm-hh:mm" required type="text" class="form-control" style="width: 90%; margin-left: 5%;" name="schedule"/><br/>
            <span  class="input-group" style="margin-left: 5%;">Tipos de restaurantes:</span>
            
            <select name="type" multiple style="background-color: white; border-radius:4px; width: 25%; margin-left: 5%; min-width: 300px; height: 120%;">
            	<c:forEach items="${listaTipos}" var="mesa">
  					<option value="${mesa.name()}"><c:out value = "${mesa.name()}"/></option>
  				</c:forEach>
			</select>
			<br/>
			<br/>
			<span class="input-group" style="margin-left: 5%;">**Si desea añadir una foto contacte con nosotros indicandonos el nombre de restaurante y la foto que desee</span><br/>
        </div>

        <div class="form-group">
            <div class="col-sm-12" style="text-align: center; margin-top: 5px;">
                <c:choose>
                    <c:when test="${restaurant['new']}">
                        <button class="buton-detalles-listado" type="submit" style="text-decoration: none; color: #ffff; font-size: 17px;">Registra Pago</button>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </div>
    </form:form>
</div>
</div>
</commandfast:layout>
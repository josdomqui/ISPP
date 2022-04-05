<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="restaurant">
    <h2>
        <c:if test="${restaurant['new']}">Nuevo </c:if> Restaurante
    </h2>
    <form:form modelAttribute="restaurant" class="form-horizontal" id="add-restaurant-form">
        <div class="form-group has-feedback">
        	
            <span>Usuario: </span><input required type="text" id="username" name="user.username"/><br/>
            <c:if test="${error}"><span style="color: red"><c:out value="Ya existe un usuario con este nombre"/></span><br/></c:if>
            <span>Contraseña: </span><input required type="password" name="user.password"/><br/>
            <span>Nombre: </span><input required type="text" minlength="3" maxlength="50" name="name"/><br/>
            <span>Dirección de correo: </span><input required type="email" name="email"/><br/>
            <span>Telefono : </span><input required type="tel"  pattern="[0-9]{9}" name="telephone"/><br/>
            <span>Ciudad: </span><input required type="text" name="city"/><br/>
            <span>Dirección: </span><input required type="text" name="address"/><br/>
            <span>Descripción: </span><input required  type="text" minlength="25" maxlength="250" name="description"/><br/>
            <!-- <span>Foto: </span><input type="text" name="photo"/><br/> -->
            <span>Capacidad del restaurante: </span><input required type="number" min="0" name="capacity"/><br/>
            <span>Horarios: </span><input required type="text" name="schedule"/><br/>
            <span>Tipos de restaurantes:</span>
            
            <select name="type" multiple style="background-color: white; border-radius:4px; width: 32.2%; margin-left: 10px; height: 120%">
            	<c:forEach items="${listaTipos}" var="mesa">
  					<option value="${mesa.name()}"><c:out value = "${mesa.name()}"/></option>
  				</c:forEach>
			</select><br/>
			<br/>
			<span>**Si desea añadir una foto contacte con nosotros indicandonos el nombre de restaurante y la foto que desee</span><br/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${restaurant['new']}">
                        <button style="background-color:#bd8859" btn btn-default" type="submit">Registrar restaurante</button>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>

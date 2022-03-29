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
            <petclinic:inputField label="Usuario" name="user.username"/>
            <petclinic:inputField label="Contraseña" name="user.password"/>
            <petclinic:inputField label="Nombre" name="name"/>
            <petclinic:inputField label="Email" name="email"/>
            <petclinic:inputField label="Teléfono" name="telephone"/>
            <petclinic:inputField label="Ciudad" name="city"/>
            <petclinic:inputField label="Dirección" name="address"/>
            <petclinic:inputField label="Descripción" name="description"/>
            <petclinic:inputField label="Foto" name="photo"/>
            <petclinic:inputField label="Capacidad" name="capacity"/>
            <petclinic:inputField label="Horario" name="schedule"/>
            <select name="type" multiple style="background-color: white; border-radius:4px; width: 32.2%; margin-left: 10px; height: 120%">
            	<c:forEach items="${listaTipos}" var="mesa">
  					<option value="${mesa.name()}"><c:out value = "${mesa.name()}"/></option>
  				</c:forEach>
			</select>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${restaurant['new']}">
                        <button style="background-color:#bd8859" btn btn-default" type="submit">Añadir Restaurante</button>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>

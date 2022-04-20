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

<commandfast:layout pageName="restaurant">
    <div class="container">
    <h2>
        <strong>Valorar este restaurante</strong>
    </h2>
    <h3>Tu valoración es muy valiosa para nuestros clientes. ¡Muchas gracias!</h3>
    <div class="card mb-3" style="border: 2px solid; background-color: rgba(158, 172, 168, 0.5); border-radius:10px; width: 100%; ">
    <form:form modelAttribute="valoracion" class="form-horizontal" id="add-restaurant-form">
		<div class="row" style="margin-top: 10px;">
			<div class="col-xs-4 col-md-4" style="margin-left: 5%;">
				<p class="card-text">Puntuación:</p>
			</div>
			<div class="col-xs-10 col-md-8 mt-2 mb-2"  style="width: 87.5%; margin-left: 16%;">
   		<select required id="puntuacion" name="puntuacion" class="form-control form-select input-filtros" style=" font-size: 18px;border-radius:10px; width: 90%;">
  			<option value="1">1</option>
  			<option value="2">2</option>
  			<option value="3">3</option>
  			<option value="4">4</option>
  			<option value="5">5</option>
		</select>
	</div>
</div>

		<div class="form-group has-feedback" style="width: 95%; margin-left: 0%; margin-top: 25px;">
		<petclinic:inputField label="Opinion:" name="opinion"/>
		<input style="width: 100%;" name="restaurante" value="${restaurante.id}" type="hidden"/>
		</div>
		<div class="form-group">
            <div class="col-sm-12" style="text-align: center;">
			<button class="buton-detalles-listado" type="submit" style="text-decoration: none; color: black; font-size: 17px;">Enviar</button>	
			</div>
		</div>
    </form:form>
</div>
</div>

</commandfast:layout>
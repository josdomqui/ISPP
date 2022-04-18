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
        <strong>Valorar este restaurante</strong>
    </h2>
    <h3>Tu valoración es muy valiosa para nuestros clientes. ¡Muchas gracias!</h3>
    <div class="card mb-3" style="border: 2px solid; background-color: rgba(158, 172, 168, 0.5); border-radius:10px; width: 100%; ">
    <form:form modelAttribute="valoracion" class="form-horizontal" id="add-restaurant-form">
    	<p>Puntuación:</p>
   		<select required name="puntuacion" class="input-filtros" style=" font-size: 18px;">
  			<option value="1">1</option>
  			<option value="2">2</option>
  			<option value="3">3</option>
  			<option value="4">4</option>
  			<option value="5">5</option>
		</select>
   		<span class="input-group" style="margin-left: 5%;">Opinión: </span><input maxlength="500" class="form-control" style="width: 90%; margin-left: 5%;" name="opinion"/><br/>
   		<input name="restaurante" value="${restaurante.id}" type="hidden"/>
    </form:form>
</div>
</div>
</commandfast:layout>
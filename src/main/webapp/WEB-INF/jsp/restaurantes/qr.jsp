<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="commandfast" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<commandfast:layout pageName="products">
    <div class="container">
    <h2>
        <strong>Generador de códigos QR</strong>
    </h2>
    <div class="card mb-3 justify-content-center" style="border: 2px solid; background-color: rgba(158, 172, 168, 0.5); border-radius:10px; width: 100%; ">
    <form method="post" class="form-horizontal" id="qr-form" action="/formAction">
        <div class="form-group has-feedback justify-content-center" style="margin-top: 18px; width: 55%; margin-left:22%;">
        	<label for="numero_mesa" class="form-label">Número de mesa</label>
            <select class= "form-control" id="numero_mesa">
	              <option value="1">1</option>
			      <option value="2">2</option>
			      <option value="3">3</option>
			      <option value="4">4</option>
			      <option value="5">5</option>
            </select>
            <input name="restaurante_id" value="${restaurante_id}" type="hidden"/>
        </div>
        <div class="form-group">
            <div class="col-sm-12" style="text-align: center;">
                 <button class="buton-detalles-listado" type="submit" style="text-decoration: none; color: #ffff; font-size: 17px;">Generar QR</button>
            </div>
        </div>
    </form>
</div>
</div>
</commandfast:layout>
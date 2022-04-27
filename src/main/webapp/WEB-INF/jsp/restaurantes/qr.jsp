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
	    <spring:url value="/restaurante/{id_restaurante}/detalles/qr/descargar" var="url">
		   <spring:param name="id_restaurante" value="${id_restaurante}"/>
		 </spring:url>
    <form method="get" class="form-horizontal" id="qr-form" action="${fn:escapeXml(url)}">
        <div class="form-group has-feedback justify-content-center" style="margin-top: 18px; width: 55%; margin-left:22%;">
        	<label for="numero_mesa" class="form-label">Número de mesa</label>
            <select class= "form-control" name="numero_mesa" style="text-decoration: none; color: black; font-size: 16px;">
	              <option value="1">1</option>
			      <option value="2">2</option>
			      <option value="3">3</option>
			      <option value="4">4</option>
			      <option value="5">5</option>
            </select>
            <input name="restaurante_id" value="${id_restaurante}" type="hidden"/>
        </div>
        <div class="form-group">
            <div class="col-sm-12" style="text-align: center;">
                 <button class="buton-detalles-listado" type="submit" style="text-decoration: none; color: #000000; font-size: 17px;">Generar QR</button>
            </div>
        </div>
    </form>
</div>
</div>
</commandfast:layout>
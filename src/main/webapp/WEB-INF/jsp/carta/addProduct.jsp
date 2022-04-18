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
        <strong><c:if test="${product['new']}">Nuevo </c:if> Plato</strong>
    </h2>
    <div class="card mb-3" style="border: 2px solid; background-color: rgba(158, 172, 168, 0.5); border-radius:10px; width: 100%; ">
    <form:form modelAttribute="product" class="form-horizontal" id="add-product-form">
        <div class="form-group has-feedback" style="margin-top: 18px; width: 95%; margin-left: 0%;">
            <commandfast:inputField label="Nombre:" name="name"/>
            <commandfast:inputField label="Descripción (opcional):" name="description" />
            <commandfast:inputField label="Precio:" name="price"/>
          
            <input label="Product" name="restaurante_id" value="${restaurante_id}" type="hidden" class="input-filtros"/>
        </div>
        <div class="form-group">
            <div class="col-sm-12" style="text-align: center;">
                <c:choose>
                    <c:when test="${product['new']}">
                        <button class="buton-detalles-listado" type="submit" style="text-decoration: none; color: #ffff; font-size: 17px;">Añadir Plato</button>
                    </c:when>
                    <c:otherwise>
                        <button class="buton-detalles-listado" type="submit" style="text-decoration: none; color: #ffff; font-size: 17px;">Editar Plato</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</div>
</div>
</commandfast:layout>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
  
  
<petclinic:layout pageName="carta">
  
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <h2 class="display-2 text-center mt-3 mb-5" editable="inline" style="color: #ffff;"> Menu</h2>
        </div>
      </div>
      <div class="row">
        <c:forEach items="${products}" var="product">
        <div class="col-md-6">
          <div class="lc-block">
            <div class="item-menu">
              <div class="item-menu-content">
                <p class="item-menu-title"><span editable="inline" class=""><c:out value = "${product.name}"/></span> <span editable="inline" class="item-menu-price"><c:out value = "${product.price}"/>€</span></p>
                <p class="item-menu-desc" editable="inline"><c:out value = "${product.description}"/><br></p>
                <sec:authorize access="hasAuthority('restaurant')">
                	<c:if test="${restaurante.user.username==username}">
                                  <spring:url value="/restaurante/{id_restaurante}/{id}/product/edit" var="editProductUrl">
                                    <spring:param name="id_restaurante" value="${restaurante.id}" />
                                    <spring:param name="id" value="${product.id}" />
                                  </spring:url>
                    
                                  <p><a type="button" class="buton-detalles-listado mt-3" href="${fn:escapeXml(editProductUrl)}"
                                    style="text-decoration: none; color: white;">Editar
                                    plato</a></p>
                                </c:if>
                                </sec:authorize>

              </div>
            </div>
            </div>
            </div>
          </c:forEach>
          <div class="col-md-12" style="text-align: center; margin-top: 5px;">
          <sec:authorize access="hasAuthority('restaurant')">
            <c:if test="${restaurante.user.username==username}">
            <spring:url value="/restaurante/{id}/product/new" var="addProductUrl">
              <spring:param name="id" value="${restaurante.id}" />
            </spring:url>

            <p><a type="button" class="buton-detalles-listado"
                href="${fn:escapeXml(addProductUrl)}"
                style="text-decoration: none; color: #ffff; font-size: 22px;">Añadir
                plato a la carta</a></p>
            </c:if>

          </sec:authorize>
        </div>
          </div>
        </div>

</petclinic:layout>

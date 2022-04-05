<%@ page session="false" trimDirectiveWhitespaces="true" %>
  <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
          <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
            <%@page contentType="text/html" %>
              <%@page pageEncoding="UTF-8" %>

                <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

                  <petclinic:layout pageName="vets">
                    <form method="get" action="/restaurante/list/search">
                      <div class="form-container-listado">
                        <div>
                          <label for="inputEmail4" class="form-label label-input-listado"
                            style="font-size: 16px;">Lugar</label>
                          <input type="text" class="form-control input-filtros" value="${place}"name="inputPlace" id="inputPlace"
                            style="font-size: 14px;font-family: 'quicksand', sans-serif;">
                        </div>
                        <div>
                          <label for="inputState" class="form-label label-input-listado">Filtros</label>
                          <select id="inputState" name="inputState" class="form-control form-select input-filtros"
                            style="font-size: 14px; font-family: 'quicksand', sans-serif;">
                            <option selected style="font-size: 14px;font-family: 'quicksand', sans-serif;">Selecciona
                              una opción</option>
                            <c:forEach items="${listaTipos}" var="tipo">
                              <option>${tipo}</option>
                            </c:forEach>
                          </select>
                        </div>
                        <div style="display: flex;
        align-self: flex-end;">
                          <button type="submit" class="btn-default buton-base-listado"><span
                              style="color: rgb(255,255,255);" class="glyphicon glyphicon-search"
                              aria-hidden="true"></span></button>
                        </div>
                      </div>
                    </form>
                    <div class="container mt-3">
                      <c:forEach items="${listaRestaurante}" var="restaurante">
                        <div class="card base-card-listado mb-5 mt-5">
                          <div class="row">
                            <div class="col-3">
                              <img src="${restaurante.photo}" class="rounded" alt="..." style="object-fit: cover;
              height: 100%;
              width: 100%;
              max-width: 200px;
              max-height: 200px;
              margin-left: 12px;
              margin-top: 4px;">
                            </div>
                            <div class="col-9">
                              <div class="card-body d-flex flex-column" style="padding-bottom: 0px;">
                                <h1 class="card-title mb-3" style="color: #ffff;"><strong>
                                    <c:out value="${restaurante.name}" />
                                  </strong></h1>
                                <p class="card-text" style="color: #ffff; font-size: 16px;">
                                  <c:out value="${restaurante.city}" />
                                </p>
                                <p class="card-text" style="color: #ffff; font-size: 18px;">
                                  <c:out value="${restaurante.description}" />
                                </p>
                                <div class="col-12 mb-3">
                                  <c:forEach items="${restaurante.type}" var="tipos">
                                    <span class="badge"
                                      style="padding: 6px; font-size: 10px; background-color: #bb924b; margin-right: 6px;">
                                      <c:out value="${tipos}" />
                                    </span>
                                  </c:forEach>
                                </div>
                              </div>
                              <div class="col-6 mb-3 mt-3">
                                <spring:url value="/restaurante/{id}/detalles" var="url">
                                  <spring:param name="id" value="${restaurante.id}" />
                                </spring:url>
                                <p><a type="button" class="buton-detalles-listado" href="${fn:escapeXml(url)}"
                                    style="text-decoration: none; color: #ffff; font-size: 14px;">Ver detalles</a></p>
                                <sec:authorize access="hasAuthority('restaurant')">
                                	<c:if test="${restaurante.user.username==username}">
                                  <spring:url value="/restaurante/{id}/product/new" var="addProductUrl">
                                    <spring:param name="id" value="${restaurante.id}" />
                                  </spring:url>
                                  <a type="button" class="btn-default" href="${fn:escapeXml(addProductUrl)}"
                                    style="padding: 4px;border: 3px solid #9f6f44; text-decoration: none;">Añadir
                                    plato a la carta</a>
                                    </c:if>
                                </sec:authorize>
                              </div>
                            </div>
                          </div>
                        </div>
                      </c:forEach>
                    </div>
                  </petclinic:layout>
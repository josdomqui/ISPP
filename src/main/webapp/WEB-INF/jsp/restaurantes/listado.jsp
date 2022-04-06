<%@ page session="false" trimDirectiveWhitespaces="true" %>
  <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="commandfast" tagdir="/WEB-INF/tags" %>
          <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
            <%@page contentType="text/html" %>
              <%@page pageEncoding="UTF-8" %>

                <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

                  <commandfast:layout pageName="vets">
                    <form method="get" action="/restaurante/list/search">
                      <div class="form-container-listado">
                        <div>
                          <label for="inputEmail4" class="form-label label-input-listado"
                            style="font-size: 16px;">Lugar</label>
                            <input pattern="^[a-zA-Z]+" oninvalid="setCustomValidity('Introduce sólo caracteres del alfabeto')" type="text" class="form-control input-filtros" value="${place}" name="inputPlace">
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
                        <div class="card base-card-listado mb-5 mt-5" style="width: auto;">
                          <div class="row">

                            <div class="col-3">
                              <img id="imgCardRestaurant" src="${restaurante.photo}" class="rounded" alt="...">
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
                              <div class="col-6 mb-3 mt-3 m-3">
                                <spring:url value="/restaurante/{id}/detalles" var="url">
                                  <spring:param name="id" value="${restaurante.id}" />
                                </spring:url>
                                <p><a type="button" class="buton-detalles-listado" href="${fn:escapeXml(url)}"
                                    style="text-decoration: none; color: #ffff; font-size: 14px;">Ver detalles</a></p>

                              </div>
                            </div>


                          </div>
                        </div>
                      </c:forEach>
                    </div>
                  </commandfast:layout>
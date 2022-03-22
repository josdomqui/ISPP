<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<petclinic:layout pageName="vets">


    <form class="row g-3" method="get" action="/restaurante/list/search">
        <div class="col-md-6">
          <label for="inputEmail4" class="form-label">Lugar</label>
          <input type="text" class="form-control" name= "inputPlace" id="inputPlace">
        </div>
        <!--
        <div class="col-md-6">
            <label for="inputState" class="form-label">Filtros</label>
            <select id="inputState" name= "inputState" class="form-control form-select">

              <c:forEach items="${listaTipos}" var="tipo">
                <option>${tipo}</option>
              </c:forEach>
            </select>
          </div>
          -->
        <div class="col-12">
         <h3><button type="submit" class="btn-default">Buscar</button></h3> 
        </div>
      </form>
      <c:forEach items="${listaRestaurante}" var="restaurante">
      <div class="container mt-5 mb-5">
      <div class="card mb-3">
        <div class="row g-0" style="border: 2px solid #9f6f44">
          <div class="col-md-3">
            <img src="${restaurante.photo}" class="img-fluid rounded-start" alt="..." style="
            height: 200px; width: 500px;"
        >
          </div>
          <div class="col-md-8">
            <div class="card-body d-flex flex-column">
              <h1 class="card-title mb-3" style="color: #bd8859;"><c:out value = "${restaurante.name}"/></h1>
              <p class="card-text"><c:out value = "${restaurante.description}"/></p>
              <p class="card-text"><c:out value = "${restaurante.city}"/></p>
              <div class="col-12 mb-3">
                <c:forEach items="${restaurante.type}" var="tipos">
               <span class="badge" style="padding: 5px; font-size: 10px; background-color: #d17024;"><c:out value = "${tipos}"/></span> 
                </c:forEach>
              </div>
              <div class="col-6 mb-3 mt-3">
              	<h3><a type="button" class="btn-default" href="/" style="padding: 4px;border: 3px solid #9f6f44; text-decoration: none;">Ver carta</a></h3>
              </div>
            </div>
          </div>
        </div>
      </div>
      </div>
      </c:forEach>
</petclinic:layout>

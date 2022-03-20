<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<petclinic:layout pageName="vets">


    <form class="row g-3">
        <div class="col-md-6">
          <label for="inputEmail4" class="form-label">Lugar</label>
          <input type="email" class="form-control" id="inputEmail4">
        </div>
        <div class="col-md-6">
            <label for="inputState" class="form-label">Filtros</label>
            <select id="inputState" class="form-control form-select">
              <option selected>Choose...</option>
              <option>...</option>
            </select>
          </div>
        <div class="col-12">
          <button type="submit" class="btn btn-primary">Buscar</button>
        </div>
      </form>
      <c:forEach items="${listaRestaurante}" var="restaurante">
      <div class="container mt-5 mb-5">
      <div class="card mb-3">
        <div class="row g-0">
          <div class="col-md-3">
            <img src="${restaurante.photo}" class="img-fluid rounded-start" alt="...">
          </div>
          <div class="col-md-8">
            <div class="card-body d-flex flex-column">
              <h1 class="card-title mb-3"><c:out value = "${restaurante.name}"/></h1>
              <p class="card-text"><c:out value = "${restaurante.description}"/></p>
              <div class="col-12 mb-3">
                  <!-- aquí va un foreach-->
                 <span class="badge bg-dark">Filtro</span>
                 <span class="badge bg-dark">Filtro</span>
                <span class="badge bg-dark">Filtro</span>
              </div>

              <div class="col-6">
                <a type="button" class="btn btn-dark mt-3" href="/">Ver detalles</a>
              </div>
            </div>
          </div>
        </div>
      </div>
      </div>
      </c:forEach>
</petclinic:layout>

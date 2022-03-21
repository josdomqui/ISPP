<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<petclinic:layout pageName="vets">


    <form class="row g-3" method="post" action="/restaurante/list">
        <div class="col-md-6">
          <label for="inputEmail4" class="form-label">Lugar</label>
          <input type="text" class="form-control" id="inputPlace">
        </div>
        <div class="col-md-6">
            <label for="inputState" class="form-label">Filtros</label>
            <select id="inputState" class="form-control form-select">

              <c:forEach items="${listaTipos}" var="tipo">
                <option>${tipo}</option>
              </c:forEach>
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
                  
                <span class="badge bg-dark"><c:out value = "${restaurante.type}"/></span>
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

 <!--   <div class="container mt-5 mb-5">
        <div class="card">
            <div class="row g-2">
                <div class="col-md-4">
                    <div class="p-3 text-center border-end"> <img class="rounded-circle" width="120" src="https://i.imgur.com/rvQ3LAt.jpg" />
                        <h4 class="mt-2">Marry Longer</h4> <span class="d-flex justify-content-center align-items-center"><small class="margin-right">Location</small><i class="ml-1 fa fa-info-circle text-success"></i></span> <span class="address-content">Zone 5, Southeast, US</span>
                        <div class="content-aboutme mt-3 p-3">
                            <h1>About Me</h1> <span>Lorem ipsum dolor sit amet, consectetur adipiscing elit, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</span>
                        </div>
                    </div>
                </div>
                <div class="col-md-8">
                    <div class="p-3">
                        <div class="d-flex justify-content-between align-items-center"> <span class="fw-bold text-success">Portfolio</span> <i class="fa fa-search-plus text-danger"></i> </div>
                        <div class="d-flex gap-2 mt-2">
                            <div> <img class="rounded img-fluid" width="100" src="https://i.imgur.com/EEguU02.jpg"> </div>
                            <div> <img class="rounded img-fluid" width="100" src="https://i.imgur.com/Uv2Yqzo.jpg"> </div>
                            <div> <img class="rounded img-fluid" width="100" src="https://i.imgur.com/xbTAITF.jpg"> </div>
                            <div> <img class="rounded img-fluid" width="100" src="https://i.imgur.com/EEguU02.jpg"> </div>
                            <div> <img class="rounded img-fluid" width="100" src="https://i.imgur.com/xbTAITF.jpg"> </div>
                            <div> <img class="rounded img-fluid" width="100" src="https://i.imgur.com/rvQ3LAt.jpg"> </div>
                        </div>
                        <div class="d-flex gap-2 mt-2">
                            <div> <img class="rounded img-fluid" width="100" src="https://i.imgur.com/EEguU02.jpg"> </div>
                            <div> <img class="rounded img-fluid" width="100" src="https://i.imgur.com/rvQ3LAt.jpg"> </div>
                            <div> <img class="rounded img-fluid" width="100" src="https://i.imgur.com/EEguU02.jpg"> </div>
                            <div> <img class="rounded img-fluid" width="100" src="https://i.imgur.com/rvQ3LAt.jpg"> </div>
                            <div> <img class="rounded img-fluid" width="100" src="https://i.imgur.com/rvQ3LAt.jpg"> </div>
                            <div> <img class="rounded img-fluid" width="100" src="https://i.imgur.com/EEguU02.jpg"> </div>
                        </div>
                        <div class="portfolio-content mt-3">
                            <p> Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. </p>
                        </div>
                        <div class="mt-2">
                            <h5>Area of Expertise</h5>
                            <div class=""> <span class="badge bg-danger mb-1">Vegetable</span> <span class="badge bg-danger mb-1">Herbs</span> <span class="badge bg-danger mb-1">Dry Climate</span> <span class="badge bg-danger mb-1">Flowers</span> <span class="badge bg-danger mb-1">Fruits</span> <span class="badge bg-danger mb-1">Dry Fruits</span> <span class="badge bg-danger mb-1">Vegetable</span> <span class="badge bg-danger mb-1">Herbs</span> <span class="badge bg-danger mb-1">Dry Climate</span> <span class="badge bg-danger mb-1">Flowers</span> <span class="badge bg-danger mb-1">Fruits</span> <span class="badge bg-danger mb-1">Dry Fruits</span> </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>-->
</petclinic:layout>

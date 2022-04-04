<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="menu">
	   
    <div class="container">
    	<div class="row">
    		<div class="col-3 offset-1"><h1><strong>Menu</strong></h1></div>
    		<div class="col-7 text-right">
			<spring:url value="/carta/{id_comanda}/ticket" var="url">
          		<spring:param name="id_comanda" value="${id_commanda}"/>
			</spring:url>																																		
   			<a class="btn-pedir" href="${fn:escapeXml(url)}" style="text-decoration: none; color: #ffff; font-size: 14px;">Finalizar pedido</a>
   			</div>
    	</div>
        
    <div class="card-deck">
    <c:forEach items="${platos}" var="listaPlatos"> 
        <div class="card d-inline-block" style="border: 2px solid; background-color: rgba(158, 172, 168, 0.5); border-radius:10px; min-height: 200px; max-height: 230px">
       		<div class="row">
	           	<div class="col-5">
	       			<img class="card-image" src="${listaPlatos.image}">
	       		</div>
	       		<div class="col-6 mt-3">
             		<h3 class="card-title text-left" style="width: 100px;"><c:out value="${listaPlatos.cost}"/> &euro;</h3>
             		<h2 class="card-title text-left mb-4"><strong><c:out value="${listaPlatos.name}"/></strong></h2>
             		 <form:form modelAttribute="line" class="form-horizontal" id="add-line-form">
						<div style="position: relative; left: 10%;" class="form-group has-feedback text-left">
							<input label="Plate" name="plate" value="${listaPlatos.id}" type="hidden"/>
							<c:if test="${!lines.isEmpty()}">
							<c:forEach items="${lines}" var="linea">
							<c:if test="${linea.plate.id == listaPlatos.id}">
							<p style="font-size: 18px">Cantidad: ${linea.quantity}</p>
							</c:if>
							</c:forEach>
							</c:if>
							<input label="Cantidad" style="color: black; width: 80px; height: 30px;  border-radius: 10px;" name="quantity" type="number" max="50" min="0"/>
							<input label="Comanda" name="command" value="${id_commanda}" type="hidden"/>
							<button style="margin-left: 10px" class="btn-pedir" type="submit"><span>Pedir</span></button>
	       				</div>
   					</form:form>
	       		</div>
          	</div>
        </div>
      </c:forEach>
    </div>
  </div>
  	
</petclinic:layout>

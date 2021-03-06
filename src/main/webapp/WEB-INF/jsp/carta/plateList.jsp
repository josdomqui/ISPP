<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="commandfast" tagdir="/WEB-INF/tags" %>

<commandfast:layout pageName="menu">

	 <c:if test="${message}">
	 ${message}
	 </c:if>
    <div class="container">
    	<div class="row">
    		<div class="col-3 offset-1"><h1><strong>Menu</strong></h1></div>
    		<div class="col-7 text-right">
    		<spring:url value="/restaurante/notify/{id_comanda}/{id_restaurante}" var="url">
          		<spring:param name="id_comanda" value="${id_commanda}"/>
          		<spring:param name="id_restaurante" value="${id_restaurante}"/>
			</spring:url>																																		
   			<a class="btn-pedir" href="${fn:escapeXml(url)}" style="text-decoration: none; color: black; font-size: 16px; margin-left: 2%;">Solicitar camarero</a>
    		<c:if test="${!platos.isEmpty()}">
			<spring:url value="/carta/{id_comanda}/{id_restaurante}/ticket" var="url">
          		<spring:param name="id_comanda" value="${id_commanda}"/>
          		<spring:param name="id_restaurante" value="${id_restaurante}"/>
			</spring:url>																																		
   			<a class="btn-pedir" href="${fn:escapeXml(url)}" style="text-decoration: none; color: black; font-size: 16px;">Finalizar pedido</a>
   			</c:if>
   			</div>
   			
    	</div>
    <c:if test="${platos.isEmpty()}">
				<h2><strong style="margin-bottom: 50%; margin-top: 50%; margin-left: 20%; margin-right: 50%"><c:out value="No se han registrado platos."/></strong></h2>
   			</c:if>
    <div class="card-deck">
    <c:forEach items="${platos}" var="listaPlatos"> 
        <div class="card d-inline-block" style="border: 2px solid; background-color: rgba(158, 172, 168, 0.5); border-radius:10px; min-height: 200px; max-height: 230px">
       		<div class="row">
	           	<div class="col-5">
	       			<img class="card-image" src="${listaPlatos.image}" alt="Imagen de ${listaPlatos}">
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

							<input label="Cantidad" required style="color: black; width: 80px; height: 35px;  border-radius: 10px; font-size: 14px" name="quantity" type="number" max="50" min="1"/>
							<input label="Comanda" name="command" value="${id_commanda}" type="hidden"/>
							<button style="margin-left: 10px; color: black; font-size: 16px" class="btn-pedir" type="submit"> Pedir </button>
	       				</div>
   					</form:form>
	       		</div>
          	</div>
        </div>
      </c:forEach>
    </div>
  </div>
  	
</commandfast:layout>

<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page import="org.springframework.samples.commandfast.plate.Plate" %>

<petclinic:layout pageName="ticket">
    <h2>Finalizar pedido</h2>
    <table class="table table-striped table-bordered">
    <thead>
    <tr>
    	<th><span>Plato</span></th>
    	<th><span>Coste Individual</span></th>
    	<th><span>Cantidad</span></th>
    	<th><span>Coste total</span></th>
    </tr>
    </thead>
    <tbody>
    		<c:forEach items="${lista_res}" var="plato">
    			<tr>
    					<td>
    						<span><c:out value="${plato.name}"/></span>
    					</td>
    					<td>
    						<span><c:out value="${plato.cost} $"/></span>
    					</td>
    					<td>
    					<c:forEach items="${lista_linea}" var="linea">
    						<c:if test="${linea.plate.id == plato.id}">
    							<span><c:out value="${linea.quantity}"/></span>
    							<td>
    								<c:out value="${plato.cost*linea.quantity}"/>
    							</td>
    						</c:if>
    					</c:forEach>
    					</td>
    					
				</tr>
    		</c:forEach>
    	</tbody>
    </table>
    			<td><span><c:out value="${suma} $"/></span></td>
    			
   	<spring:url value="/payment/{id_comanda}" var="url">
    <spring:param name="id_comanda" value="${id_commanda}"/>
    </spring:url>
    <a class="btn btn-default" href="${fn:escapeXml(url)}"><span>Pagar online</span></a>
     
    <spring:url value="/payment/cash/{id_comanda}" var="url2">
    <spring:param name="id_comanda" value="${id_commanda}"/>
    </spring:url>
    <a class="btn btn-default" href="${fn:escapeXml(url2)}"><span>Pagar en efectivo</span></a>
     
     <spring:url value="/payment/creditCard/{id_comanda}" var="url3">
     <spring:param name="id_comanda" value="${id_commanda}"/>
     </spring:url>
     <a class="btn btn-default" href="${fn:escapeXml(url3)}"><span>Pagar con tarjeta</span></a>
     
</petclinic:layout>






<!--
<c:out value="${listaPlatos.name}"/></td>
						<td><c:out value="${listaPlatos.cost} $"/></td>
						<td>
						<form:form modelAttribute="line" class="form-horizontal" id="add-line-form">
						<div class="form-group has-feedback">
							<input label="Plate" name="plate" value="${listaPlatos.id}" type="hidden"/>
							<input label="Cantidad" name="quantity"/>
							<input label="Comanda" name="command" value="${id_commanda}"/>
        				</div>
        				<div class="form-group">
            					<div class="col-sm-offset-2 col-sm-10">
                
                  				<button class="btn btn-default" type="submit">Pedir</button>
                
            			</div>
        				</div>
    					</form:form>
-->
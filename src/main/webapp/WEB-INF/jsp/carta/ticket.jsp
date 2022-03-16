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
    	<th>Plato</th>
    	<th>Coste Individual</th>
    	<th>Cantidad</th>
    	<th>Coste total</th>
    	
    	
    	<tbody>
    		<c:forEach items="${lista_res}" var="plato">
    			<tr>
    					<td>
    						<c:out value="${plato.name}"/>
    					</td>
    					<td>
    						<c:out value="${plato.cost} $"/>
    					</td>
    					<td>
    					<c:forEach items="${lista_linea}" var="linea">
    						<c:if test="${linea.plate.id == plato.id}">
    							<c:out value="${linea.quantity}"/>
    							<td>
    							<c:out value=""/>
    						</td>
    						</c:if>
    						
    					</c:forEach>
    					</td>
    					
				</tr>
    		</c:forEach>
    		<tr>
    			<td></td>
    			<td></td>
    			<td></td>
    			<td><c:out value="${suma} $"/></td>
    		</tr>
    	</tbody>
    </table>
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
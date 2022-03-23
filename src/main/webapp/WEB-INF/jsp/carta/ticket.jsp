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
    
  <div id="register">
  <div id="ticket">
    <h1>¡Muchas gracias!</h1>
    <table>
      
      <tbody id="entries">
        
      </tbody>
      <tfoot style="font-size: 15px;">
      	<c:forEach items="${lista_res}" var="plato">
        <tr>
          <th><c:out value="${plato.name} "/>x
          <c:forEach items="${lista_linea}" var="linea">
    		<c:if test="${linea.plate.id == plato.id}">
    			<c:out value="${linea.quantity}"/>
    		</c:if>
    					</c:forEach>
          </th>
          <th id="total">$<c:out value="${plato.cost}"/></th>
        </tr>
        </c:forEach>
        <tr>
          <th>Total</th>
          <th id="total"><span><c:out value="$ ${suma}"/></span></th>
        </tr>
      </tfoot>
    </table>
  </div>
</div>
<!--

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
    			<td><span><c:out value="${suma} $"/></span></td>
    		</tr>
    	</tbody>
    </table>
 -->
</petclinic:layout>


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
    <h1>Muchas gracias!</h1>
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
</br>
        <h3>Si pagais entre  <c:out value="${comensales}"/> sale a:
        <c:out value= "${division}"/> cada uno.</h3>
</br>
    			
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


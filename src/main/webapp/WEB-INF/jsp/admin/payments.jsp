<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page import="org.springframework.samples.commandfast.plate.Plate" %>

<petclinic:layout pageName="ticket">
    <h2>Mesas que quieren pagar aquí</h2>
    <table class="table table-striped table-bordered">
    <thead>
    <tr>
    	<th><span>Mesa</span></th>
    	<th><span>Importe</span></th>
    	<th><span>Datáfono</span></th>
    </tr>
    </thead>
    <tbody>
    		<c:forEach items="${payments}" var="payment">
    			<tr>
    					<td>
    						<span><c:out value="${payment.table.number}"/></span>
    					</td>
    					<td>
    						<span><c:out value="${payment.amount} $"/></span>
    					</td>
    					<c:if test="${payment.creditCard==true}">
    						<td>
    						<c:out value="Sí"/>
    						</td>
    					</c:if>
    					
     					<c:if test="${payment.creditCard==false}">
    						<td>
    						<c:out value="No"/>
    						</td>
    					</c:if>   					
				</tr>
    		</c:forEach>
    	</tbody>
    </table>   			
     
</petclinic:layout>

    					
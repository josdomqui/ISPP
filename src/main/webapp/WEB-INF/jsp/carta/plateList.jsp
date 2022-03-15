<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="menu">
    <h2>Menu</h2>
    <table class="table table-striped table-bordered">
            <tbody>
                <c:forEach items="${platos}" var="listaPlatos">
                	<tr>
						<td><c:out value="${listaPlatos.name}"/></td>
						<td><c:out value="${listaPlatos.cost} $"/></td>
						<td><a href="">Anadir</a></td>
					</tr>
    			</c:forEach>
           </tbody>
    </table>
</petclinic:layout>

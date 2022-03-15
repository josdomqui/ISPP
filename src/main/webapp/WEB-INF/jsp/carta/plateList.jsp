<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="menu">
    <h2>Menu</h2>
    <table class="table table-striped table-bordered">
            <tbody>
                <c:forEach items="${platos}" var="listaPlatos">
                	<tr>
						<td><c:out value="${listaPlatos.name}"/></td>
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
    					</td>
				</tr>
    			</c:forEach>
           </tbody>
    </table>
</petclinic:layout>

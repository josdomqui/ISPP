<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="commands">
    <h2>
       	Nuevo Pedido
    </h2>
    <form:form modelAttribute="command" class="form-horizontal" id="add-command-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Nombre" name="name"/>
            <petclinic:inputField label="Mesa" name="mesa"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                
                  	<button class="btn btn-default" type="submit">Pedir</button>
                
            </div>
        </div>
    </form:form>
</petclinic:layout>

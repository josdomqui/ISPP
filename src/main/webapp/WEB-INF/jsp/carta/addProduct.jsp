<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="products">
    <h2>
        <c:if test="${product['new']}">New </c:if> Product
    </h2>
    <form:form modelAttribute="product" class="form-horizontal" id="add-product-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="name" name="name"/>
            <petclinic:inputField label="description" name="description"/>
            <petclinic:inputField label="price" name="price"/>
            <input label="Product" name="restaurante_id" value="${restaurante_id}" type="hidden"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                
            <button class="btn btn-default" type="submit">Add Product</button>
                    
            </div>
        </div>
    </form:form>
</petclinic:layout>
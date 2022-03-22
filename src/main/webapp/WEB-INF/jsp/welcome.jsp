<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<petclinic:layout pageName="home"> 
    <div class="row">
        <div align="center">
            <spring:url value="/resources/images/Imagen1.png" htmlEscape="true" var="petsImage"/>
            <img style="width: 30%; height: 90%" class="img-responsive" src="${petsImage}"/>
            <h2>Bienvenidos</h2>
        </div>
    </div>
</petclinic:layout>

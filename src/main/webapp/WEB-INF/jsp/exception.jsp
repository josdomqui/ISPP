<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="commandfast" tagdir="/WEB-INF/tags" %>

<commandfast:layout pageName="error">

    <spring:url value="/resources/images/error-icon.png" var="error"/>
    <img src="${error}" alt="Error image"/>


    <h2>Disculpe las molestisas, ha ocurrido un error inesperado</h2>

    <p>${exception.message}</p>

</commandfast:layout>

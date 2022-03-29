<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
  
  
<petclinic:layout pageName="carta">
  
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <h2 class="display-2 text-center mt-3 mb-5" editable="inline" style="color: #ffff;"> Menu</h2>
        </div>
      </div>
      <div class="row">
        <c:forEach items="${products}" var="product">
        <div class="col-md-6">
          
          <div class="lc-block">
            <div class="item-menu">
              <div class="item-menu-content">
                <p class="item-menu-title"><span editable="inline" class=""><c:out value = "${product.name}"/></span> <span editable="inline" class="item-menu-price"><c:out value = "${product.price}"/>€</span></p>
                <p class="item-menu-desc" editable="inline"><c:out value = "${product.description}"/><br></p>
              </div>
            </div>
            </div>
            </div>
          </c:forEach>
          </div>
        </div>

</petclinic:layout>

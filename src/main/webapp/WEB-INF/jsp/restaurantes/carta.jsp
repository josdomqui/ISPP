<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<style>

  .item-menu {
    display: flex;
    align-items: center;
    margin-bottom: 45px
  }
  
  .item-menu .item-menu-thumb {
    margin-right: 30px
  }
  
  .item-menu .item-menu-thumb img {
    border-radius:50 {
      f7afd7ef68c0ce0f8f7d8548daa5f51fc4d2b9ba280e72bff84a187d699adf06
    }
  }
  
  .item-menu-content {
    -webkit-box-flex: 1;
    -webkit-flex: 1;
    -ms-flex: 1;
    flex: 1
  }
  
  .item-menu-title {
    font-size: 24px;
    color: #2d2d2d;
    margin-bottom: 5px;
    line-height: normal;
    display: -webkit-box;
    display: -webkit-flex;
    display: -ms-flexbox;
    display: flex;
    -webkit-box-pack: justify;
    -webkit-justify-content: space-between;
    -ms-flex-pack: justify;
    justify-content: space-between
  }
  
   
  
  .item-menu-title::after {
    content: "";
    border-bottom: 2px dotted #e1e1e1;
    width: auto;
    -webkit-box-flex: 1;
    -webkit-flex: 1 1 auto;
    -ms-flex: 1 1 auto;
    flex: 1 1 auto;
    position: relative;
    top: -3px;
    margin-left: 3px
  }
  
  .item-menu-price {
    margin-left: auto;
    padding-left: 5px;
    -webkit-box-ordinal-group: 4;
    -webkit-order: 3;
    -ms-flex-order: 3;
    order: 3;
    display: inline-block
  }
  
  .item-menu-desc {
    font-style: italic;
    margin-bottom: 0;
    line-height: normal
  }
  
   
  </style>
  
  
<petclinic:layout pageName="carta">
  
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <h2 class="display-2 text-center mt-3 mb-5" editable="inline" style="color: #bd8859;"> Menu</h2>
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

<%@ page session="false" trimDirectiveWhitespaces="true" %>
  <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
          <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
            <%@ taglib prefix="commandfast" tagdir="/WEB-INF/tags" %>
              <%@ page import="org.springframework.samples.commandfast.plate.Plate" %>

                <commandfast:layout pageName="ticket">
                  <div class="ticket-container">
                    <div class="ticket-title-container">
                      <h2>Finalizar pedido</h2>
                    </div>
                    <div class=".ticket-card-container">
                      <div class="ticket-card">
                        <table class="table-borderless" style="
                        width: 100%;
                    ">
                          <tbody style="
                          width: 100%;
                      ">
                            <c:forEach items="${lista_res}" var="plato">
                              <tr>
                                <th>
                                  <p style="font-size: 14px; margin-bottom: 0px;">
                                    <c:out value="${plato.name} " />x
                                    <c:forEach items="${lista_linea}" var="linea">
                                      <c:if test="${linea.plate.id == plato.id}">
                                        <c:out value="${linea.quantity}" />
                                      </c:if>
                                    </c:forEach>
                                  </p>
                                </th>
                                <th id="total">
                                  <p style="font-size: 14px; margin-bottom: 0px; text-align: end;">
                                    <c:out value="${plato.cost}" /> &euro;
                                  </p>
                                </th>
                              </tr>
                            </c:forEach>
                          </tbody>
                          <tfoot>
                            <tr>
                              <th><span><strong>Total</strong></span></th>
                              <th id="total" style="text-align: end;"><span><strong>
                                    <c:out value="${suma}"/> &euro;
                                  </strong>
                                </span></th>
                            </tr>
                          </tfoot>

                        </table>
                      </div>
                    </div>
                  </div>

                  </br>
                  <h3>Si pagais entre
                    <c:out value="${comensales}" /> sale a:
                    <c:out value="${suma/comensales}"/> &euro; cada uno.
                  </h3>
                  </br>

                  </div>
                  <div class="ticket-buttons-container">

                    <spring:url value="/payment/{id_comanda}" var="url">
                      <spring:param name="id_comanda" value="${id_commanda}" />
                    </spring:url>
                    <a class="buton-detalles-listado" href="${fn:escapeXml(url)}" style="text-decoration: none;"><span
                        style="font-size: 14px;">Pagar online</span></a>

                    <spring:url value="/payment/cash/{id_comanda}" var="url2">
                      <spring:param name="id_comanda" value="${id_commanda}" />
                    </spring:url>
                    <a class="buton-detalles-listado" href="${fn:escapeXml(url2)}" style="text-decoration: none;"><span
                        style="font-size: 14px;">Pagar en efectivo</span></a>

                    <spring:url value="/payment/creditCard/{id_comanda}" var="url3">
                      <spring:param name="id_comanda" value="${id_commanda}" />
                    </spring:url>
                    <a class="buton-detalles-listado" href="${fn:escapeXml(url3)}" style="text-decoration: none;"><span
                        style="font-size: 14px;">Pagar con tarjeta</span></a>
                  </div>

                </commandfast:layout>
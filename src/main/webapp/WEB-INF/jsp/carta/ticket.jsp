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
                  <!-- 
                  <h3>Si pagais entre
                    <c:out value="${comensales}" /> sale a:
                    <c:out value="${suma/comensales}"/> &euro; cada uno.
                  </h3>
                  -->
                  <input type="hidden" id="num-comensales" value="${comensales}" />
                  <input type="hidden" id="sumaTotal" value="${suma}" />
                  <c:forEach begin="1" end="${comensales}" var="n">
                  
                  <h3>Comensal <c:out value="${n}"/></h3>
                  <input id="pagar-<c:out value="${n}"/>" onchange="calculateDivision()" value="<c:out value="${suma/comensales}"/>" class="input-filtros" style="color: black; font-size: 18px;"/>
                  
                  </c:forEach>
                  <input id="divPago" value="" />
                  </br>
					<script>
						window.onload = function inicio() {
							calculateDivision();
						}
						
						function calculateDivision(){
								var numComensales = document.getElementById("num-comensales").value;
								var i = 1;
								var res = "";
								var precioTotal = 0;
								while(i <= numComensales){
									res += document.getElementById("pagar-"+i).value + "-";
									precioTotal += +document.getElementById("pagar-"+i).value;
									i++;
								}
								var redondeo = Math.round(precioTotal * 100) / 100;
								console.log(redondeo);
								console.log(res);
								
								//comprobar que la suma de las n partes es igual al dinero total
								var total = +document.getElementById("sumaTotal").value;
								if(Math.abs(redondeo - total) > 0.01){
									alert("Por favor, reparta los importes de tal manera que la suma sea igual al importe total del pedido.")
								}
								document.getElementById("divPago").value = res;
						}
					</script
                  </div>
                  <div class="ticket-buttons-container">

                    <spring:url value="/payment/{id_comanda}" var="url">
                      <spring:param name="id_comanda" value="${id_commanda}" />
                    </spring:url>
                    <a class="buton-detalles-listado" href="${fn:escapeXml(url)}" style="text-decoration: none;"><span
                        style="font-size: 16px; color: black">Pagar online</span></a>

                    <spring:url value="/payment/cash/{id_comanda}" var="url2">
                      <spring:param name="id_comanda" value="${id_commanda}" />
                    </spring:url>
                    <a class="buton-detalles-listado" onclick="return confirm('�Est� seguro que quiere pagar con este m�todo de pago? No podr� usar otro m�todo si aceptas.')" href="${fn:escapeXml(url2)}" style="text-decoration: none;"><span
                        style="font-size: 16px; color: black">Pagar en efectivo</span></a>

                    <spring:url value="/payment/creditCard/{id_comanda}" var="url3">
                      <spring:param name="id_comanda" value="${id_commanda}" />
                    </spring:url>
                    <a class="buton-detalles-listado" onclick="return confirm('�Est� seguro que quiere pagar con este m�todo de pago? No podr� usar otro m�todo si aceptas.')"  href="${fn:escapeXml(url3)}" style="text-decoration: none;"><span
                        style="font-size: 16px; color: black">Pagar con tarjeta</span></a>
                  </div>

                </commandfast:layout>
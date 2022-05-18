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
                  <input type="hidden" id="division" value="${division}" />
                  <div class="ticket-buttons-container">

                    <spring:url value="/payment/{id_comanda}" var="url">
                      <spring:param name="id_comanda" value="${id_commanda}" />
                    </spring:url>
                    <a id="pagar-online" class="buton-detalles-listado" href="${fn:escapeXml(url)}" style="text-decoration: none;"><span
                        style="font-size: 16px; color: black">Pagar online</span></a>

                    <spring:url value="/payment/cash/{id_comanda}" var="url2">
                      <spring:param name="id_comanda" value="${id_commanda}" />
                    </spring:url>
                    <a id="pagar-efectivo" class="buton-detalles-listado" onclick="return confirm('¿Estas seguro que quiere pagar con este metodo de pago? No podras usar otro metodo si aceptas.')" href="${fn:escapeXml(url2)}" style="text-decoration: none;"><span
                        style="font-size: 16px; color: black">Pagar en efectivo</span></a>

                    <spring:url value="/payment/creditCard/{id_comanda}" var="url3">
                      <spring:param name="id_comanda" value="${id_commanda}" />
                    </spring:url>
                    <a id="pagar-tarjeta" class="buton-detalles-listado" onclick="return confirm('¿Estas seguro que quiere pagar con este metodo de pago? No podras usar otro metodo si aceptas.')"  href="${fn:escapeXml(url3)}" style="text-decoration: none;"><span
                        style="font-size: 16px; color: black">Pagar con tarjeta</span></a>
                  </div>
                  <br>
                  <div id="error-division" class="alert alert-danger" style="font-size: large;">Por favor, reparta los importes de los comensales de tal manera que la suma sea igual al importe total del pedido.</div>
                        <br>
                        <br>
                        <h1>En total sale a ${division} por comensal</h1>
                        <br>
                        <h2>Si desea organizar de otra forma el pago rellene los siguientes campos hasta que el importe sea el corecto:</h2>
                        <br>
                  <c:forEach begin="1" end="${comensales}" var="n">
                  <div class="container-pago">
	                  <h3>Comensal <c:out value="${n}"/></h3>
	                  <input id="pagar-<c:out value="${n}"/>" onchange="calculateDivision()" value="<c:out value="${division}"/>" class="input-pago" style="color: black; font-size: 18px;"/>

                  </div>
                  </c:forEach>
				<div class="container-precio">
                  <h2  class="texto-pago">Diferencia de precio:</h2>
                  <h2 id="sumaDivision"> </h2> 
                  
            
                  <input type= hidden id="divPago" value="" />
                </div>
              
              
              <a  class="buton-detalles-listado" onclick="defaultDivision();" style="text-decoration: none;"><span
                        style="font-size: 16px; color: black">Dividir pago</span></a>
					<script>
						window.onload = function inicio() {
							calculateDivision();
						}
						
						function defaultDivision(){
							var numComensales = document.getElementById("num-comensales").value;
							var division = document.getElementById("division").value;
							var i = 1;
							while(i <= numComensales){
								document.getElementById("pagar-"+i).value = division;
								i++;
							}
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
								console.log(Math.abs(redondeo - total));
								if(Math.abs(redondeo - total) > 0.02){
									document.getElementById("error-division").style.display = "block";
									document.getElementById("pagar-online").style = "text-decoration: none; pointer-events: none; background-color: gray;";
									document.getElementById("pagar-efectivo").style = "text-decoration: none; pointer-events: none; background-color: gray;";
									document.getElementById("pagar-tarjeta").style = "text-decoration: none; pointer-events: none; background-color: gray;";
									document.getElementById("sumaDivision").innerHTML = ((precioTotal-total).toFixed(2)) + "&euro;";
								}else{
									document.getElementById("error-division").style.display = "none";
									document.getElementById("pagar-online").style = "text-decoration: none;";
									document.getElementById("pagar-efectivo").style = "text-decoration: none;";
									document.getElementById("pagar-tarjeta").style = "text-decoration: none;";
									document.getElementById("sumaDivision").innerHTML = 0 + "&euro;";
								}
								console.log("falta: " + (precioTotal-total));
								document.getElementById("divPago").value = res;
						}
					</script>
                  </div>
                 

                </commandfast:layout>
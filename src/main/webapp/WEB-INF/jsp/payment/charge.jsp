<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="owners">

<head>
    <!--Bootstrap 4 CSS-->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <!--Bootstrap 4 JavaScript-->
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

    <!--Stripe JavaScript Library-->
    <script src="https://js.stripe.com/v3/"></script>
</head>
<body class="bg pt-5" style="padding-top: 0rem !important">
<!--hero section-->
<section class="py-5">
    <div class="container">
        <div class="row">
            <div class="col-lg-6 col-md-8 col-12 my-auto mx-auto">
                <h1>
                    Gesti�n de pagos Stripe
                </h1>
                <p class="lead mb-4">
                    Por favor, complete el siguiente formulario para realizar el pago.
                </p>
                <div class="card mb-4" style="background-color: rgba(158, 172, 168, 0.5)">
                    <div class="card-body">
                        <h5>Pedido CommandFast</h5>
                        <p><c:out value="${price}"/> &euro;</p>
                    </div>
                </div>
                <form action="#" id="payment-form" method="post">
                    <!--  <input id="api-key" type="hidden" th:value="${stripePublicKey}">-->
                    <input id="api-key" type="hidden" value="${stripePublicKey}"/>
                    <div class="form-group">
                        <label class="font-weight-medium" for="card-element">
                            Introduce su tarjeta de cr�dito/d�bito
                        </label>
                        <div class="w-100" id="card-element">
                            <!-- A Stripe Element will be inserted here. -->
                        </div>
                    </div>
                    <div class="form-group">
                        <input class="form-control" id="email" name="email"
                               placeholder="Email" type="email" required>
                    </div>
                    
                    <!-- Used to display Element errors. -->
                    <div class="text-danger w-100" id="card-errors" role="alert"></div>
                    <div class="form-group pt-2">
	                    <spring:url value="/payment/successPage/{id_comanda}" var="url">
	                      <spring:param name="id_comanda" value="${id_comanda}"/>
	                    </spring:url>
                        <a class="btn btn-block" id="submitButton" style="background-color: #ffcb74; color: #ffff" href="${fn:escapeXml(url)}">
                            Finalizar pago
                        </a>
                        <div class="small text-muted mt-2">
                            Pay securely with Stripe. By clicking the button above, you agree
                            to our <a target="_blank" href="#">Terms of Service</a>,
                            <a target="_blank" href="#">Privacy</a> and
                            <a target="_blank" href="#">Refund</a> policies.

                        </div>
                    </div>

                </form>
            </div>
        </div>
    </div>
</section>

<!--custom javascript for handling subscription-->
<script>
    $(function () {
        var API_KEY = $('#api-key').val();
        // Create a Stripe client.
        var stripe = Stripe(API_KEY);

        // Create an instance of Elements.
        var elements = stripe.elements();
        
        var style = {
        	base: {
       		    color: "#ffffff",
      		}
       	};

        // Create an instance of the card Element.
        var card = elements.create("card", { style: style });

        // Add an instance of the card Element into the `card-element` <div>.
        card.mount('#card-element');

        // Handle real-time validation errors from the card Element.
        card.addEventListener('change', function (event) {
            var displayError = document.getElementById('card-errors');
            if (event.error) {
                displayError.textContent = event.error.message;
            } else {
                displayError.textContent = '';
            }
        });

        // Handle form submission.
        var form = document.getElementById('payment-form');
        form.addEventListener('submit', function (event) {
            event.preventDefault();
            // handle payment
            handlePayments();
        });

        //handle card submission
        function handlePayments() {
            stripe.createToken(card).then(function (result) {
                if (result.error) {
                    // Inform the user if there was an error.
                    var errorElement = document.getElementById('card-errors');
                    errorElement.textContent = result.error.message;
                } else {
                    // Send the token to your server.
                    var token = result.token.id;
                    var email = $('#email').val();
                    $.get(
                        "/welcome",
                        {email: email, token: token},
                        function (data) {
                            alert(data.details);
                        }, 'json');
                }
            });
        }
    });
</script>

</body>
</html>

</petclinic:layout>
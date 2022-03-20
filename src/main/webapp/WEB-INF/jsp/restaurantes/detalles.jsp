
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<petclinic:layout pageName="detalles-restaurante">
<div class="container py-5">
	<div class="row min-vh-50 align-items-center">
		<div class="col-md-12 text-center">
			<div class="lc-block mb-4">
				<div editable="rich">
					<h2 class="display-3 fw-bolder">Nombre restaurante</h2>
					<p>Lorem ipsum dolor sit amet consectetur adipiscing elit sed do<br>&nbsp;eiusmod tempor incididunt ut labore et dolore magna aliqua.&nbsp;</p>

				</div>
			</div><!-- /lc-block -->
			<div class="lc-block border-top col-md-6 offset-md-3">
				<div editable="rich">
					<h2 class="fw-bolder"><br></h2>
				</div>
			</div>
			<div class="lc-block mb-4">
				<div editable="rich">
					<h2 class="fw-bolder" style="
                    display: flex;
                    justify-content
                : center;
                    width: 100%;
                ">Working Hours</h2>
				</div>
			</div>
			<div class="lc-block">
				<div editable="rich">
					<p>Monday to Friday: 07:00 – 19:45</p>
					<p>Saturday to Sunday: 11:00 – 17:00</p>
				</div>
			</div><!-- /lc-block -->
		</div><!-- /col -->
	</div>
</div>

<div class="container">
	<div class="row align-items-center">
		<div class="col-md-6">
			<div class="lc-block mb-4">
				<div class="ratio ratio-4x3 min-vh-50" lc-helper="gmap-embed">
					<iframe src="https://maps.google.com/maps?q=London%2C%20UK&amp;t=m&amp;z=8&amp;output=embed&amp;iwloc=near"></iframe>
				</div>
			</div><!-- /lc-block -->
		</div><!-- /col -->
		<div class="col-md-6 px-5">
			<div class="lc-block mb-4">
				<div editable="rich">
					<h2 class="display-6 fw-bolder">Find us!<p></p>
						<p></p>
					</h2>
				</div>
			</div>
			<div class="lc-block mb-4">
				<div editable="rich">
					<p class="lead">121 Burton Avenue Memphis</p>
					<p class="lead">901-773-4045</p>
					<p class="lead">mail@mydomain.com</p>

				</div>
			</div>
			<div class="lc-block mb-4">
				<div editable="rich">
					<p>Dolor amet consectetur adipisci modi exceur sante madama tempora incidunt labore magnam voluptatem enim minima.</p>
				</div>
			</div><!-- /lc-block -->
		</div><!-- /col -->
	</div>
</div>
</petclinic:layout>
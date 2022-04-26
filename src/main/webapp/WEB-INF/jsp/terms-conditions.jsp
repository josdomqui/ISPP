<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="commandfast" tagdir="/WEB-INF/tags" %>

<commandfast:layout pageName="terms">
	
	<div class="terms">
		<h1>Seguridad y transparencia como principio</h1>
		<br>
			<p>Nuestra aplicacion usa en todo momento el cifrado https para todas las comunicaciones realizadas a traves de la misma, asegurando
			de esta forma que nadie pueda interceptar dicha informacion de forma fraudulenta.</p>
			<p>Además cuenta con un correo electronico de atención al cliente mediante el cual puede contactar con nosotros ante cualquier duda,
			asi como para solicitar cualquier derecho <a href='https://www.aepd.es/es/derechos-y-deberes/conoce-tus-derechos'>ARCO</a> de los detallados en los documentos posteriores de "Terminos y Condiciones de Uso" y
			"Políticas de Privacidad", o desde el que será infomado en caso de cualquier falla de seguridad o filtrado de datos. 
			Siendo el correo electronico <i>commandfastispp@gmail.com</i></p>
		<br>
		
		<h1>Datos recogidos del usuario</h1>
		 <br>
			<p>En caso de que se registre en nuestra aplicacion, los datos que serán recogidos en nuestra base de datos serán los correspondientes 
			al formulario de registro. Estos datos seran enviados en un informe en caso de solicitarlo el cliente, concretamente estos son: </p>
			<br/>
	</div>
	<div class='container'>
		<div class='row'>
			<div class='col-lg-6'>
			<ul>
				<li><p>Usuario</p></li>
				<li><p>Contraseña</p></li>
				<li><p>Nombre</p></li>
				<li><p>Dirección de correo</p></li>
				<li><p>Teléfono</p></li>
				<li><p>Ciudad</p></li>
				<li><p>Dirección</p></li>
			</ul>
			<br/>
			<iframe id="terminos_de_uso" src="https://docs.google.com/viewer?srcid=1Flu9tm41yOyE8Uqvs0ivOcInwUT3GWhK&pid=explorer&efh=false&a=v&chrome=false&embedded=true" 
			    	width="100%" height="680px" ></iframe>
			</div>
			<div class='col-lg-6'>
			<ul>
			<li><p>Descripción</p></li>
				<li><p>Capacidad del restaurante</p></li>
				<li><p>Horarios</p></li>
				<li><p>Tipo de restaurante</p></li>
				<li><p>Caja y Ventas</p></li>
				<li><p>Cartas</p></li>
			</ul>
			<br/>
			<br/>
			<br/>
			<iframe id="politicas" src="https://docs.google.com/viewer?srcid=1r6f4BnDnw6zRmoA-urYeAaYLYua_qiT1&pid=explorer&efh=false&a=v&chrome=false&embedded=true" 
			    	width="100%" height="680px" ></iframe>
			</div>  	
		</div>
	</div>

</commandfast:layout>

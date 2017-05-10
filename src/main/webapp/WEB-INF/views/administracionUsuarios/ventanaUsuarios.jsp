<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ventana Usuarios</title>
<script src="/${initParam.appname}/webjars/jquery/3.2.0/jquery.min.js"></script>
<script src="/${initParam.appname}/webjars/datatables/1.10.13/js/jquery.dataTables.min.js"></script>
<script src="/${initParam.appname}/webjars/sweetalert2/6.4.2/dist/sweetalert2.min.js"></script>
<link rel="stylesheet" type="text/css" href="/${initParam.appname}/webjars/datatables/1.10.13/css/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css" href="/${initParam.appname}/webjars/sweetalert2/6.4.2/dist/sweetalert2.min.css">
<script>
	<%--'Spring security' para poder hacer solicitudes ajax --%>
	var token = "${_csrf.token}";
	var header = "${_csrf.headerName}";
	$(document).ajaxSend(function(e, xhr, options) {
	    xhr.setRequestHeader(header, token);
	});
	<%-- --%>
	
	$(document).ready(function () {
		var table = $("#datatable").DataTable();
		
		var panelCrear = $("#panelCrear").detach().html();
		
		$("#crear").click(function() {
			swal({
				html: panelCrear
			}).then(function() {
				$.ajax({
					type : "POST",
					url : "/${initParam.appname}/administracionUsuarios/crearUsuario.do",
					data : $("#formCrear").serialize(),
					success : function(data) {
						table.row.add([
							data.idUsuario, data.nombre, data.activo, data.usuarioRols
						]).draw();
					},
					error : function(e) {
						console.log("ERROR: ", e);
					}
				});
			});
		});
		 
	    $('#datatable tbody').on('click', 'tr', function () {
	        if ( $(this).hasClass('selected') ) {
	            $(this).removeClass('selected');
	        }
	        else {
	            table.$('tr.selected').removeClass('selected');
	            $(this).addClass('selected');
	        }
	    } );
	 
	    var panelActualizar = $("#panelActualizar");
	    $("#panelActualizar").detach();
	    
	    $('#editar').click( function () {
	    	var rowSelected = table.row('.selected').data();
	    	if (rowSelected) {
	    		panelActualizar.find("input[name='idUsuario']").attr("value", rowSelected[0]);
	    		panelActualizar.find("input[name='nombre']").attr("value", rowSelected[1]);
	    		panelActualizar.find("input[name='activo']").attr("value", rowSelected[2]);
		    	swal({
		    		html: panelActualizar.html()
		    	}).then(function() {
			    	$.ajax({
						type : "POST",
						url : "/${initParam.appname}/administracionUsuarios/actualizarUsuario.do",
						data : $("#formActualizar").serialize(), //tambien se envía el password
						success : function(data) {
							if (data) {
								table.row('.selected').data([
									data.idUsuario, data.nombre, data.activo, data.usuarioRols
								]).draw();
								alert("ok");
							} else
								alert("No se ha podido actulaizar la información");
							
						},
						error : function(e) {
							console.log("ERROR: ", e);
						}
					});	    		
		    	});
	    	}
	    });
		
	});
</script>
</head>
<body>
	Hello ventan Usuarios<br/>
	<table id="datatable" class="display">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nombre</th>
				<th>Activo</th>
				<th>Roles</th>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach items="${usuarios}" var="usuario">
				<tr>
					<td>${usuario.idUsuario}</td>
					<td>${usuario.nombre}</td>
					<td>${usuario.activo}</td>
					<td>
						<c:forEach items="${usuario.usuarioRols}" var="usuarioRol">
							&nbsp;&nbsp;${usuarioRol.rol}<br/> 
						</c:forEach>
					</td>
				</tr> 
			</c:forEach>
		</tbody>
	</table>
	
	<button id="crear">Nuevo</button>
	<button id="editar">Actualizar</button>
	<button id="eliminar">Eliminar</button>
	
	<div id="panelCrear" style="display: none">
		<form id="formCrear" method="POST">
			ID <input type="text" name="idUsuario" value="0"/><br/>
			Nombre usuario <input type="text" name="nombre" value=""/><br/>
			Password <input type="password" name="password" value=""/><br/>
			Activo <input type="text" name="activo" value=""/><br/>
			Roles  <input disabled type="text" value="'por hacer'"/><br/>
		</form>
	</div>
	
	<div id="panelActualizar" style="display: none">
		<form id="formActualizar" method="POST">
			ID <input type="text" name="idUsuario" value="0"/><br/>
			Nombre usuario <input type="text" name="nombre" value=""/><br/>
			Password <input type="password" name="password" value=""/><br/>
			Activo <input type="text" name="activo" value=""/><br/>
			Roles  <input disabled type="text" value="'por hacer'"/><br/>
		</form>
	</div>
</body>
</html>
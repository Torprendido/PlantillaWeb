<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Hello world!</h1>

	<P>Hello: ${usuario}.</P>
	<a href="/${initParam.appname}/administracionUsuarios/ventanaUsuarios.do">Administracion de usuarios</a>
</body>
</html>

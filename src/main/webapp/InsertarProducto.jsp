<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Insertar Producto</h1>
  
  <div class="container mt-5">
  
  <form method="post" action="InsertarProducto" id="form_insertar_producto">
   <div class="form-group">
      <label>Id:</label>
      <input type="text" name="id" class="form-control" required /><br/>
    </div>
      <div class="form-group">
      <label>Codigo:</label>
      <input type="text" name="codigo" class="form-control" required /><br/>
    </div>
    <div class="form-group">
      <label>Nombre:</label>
      <input type="text" name="nombre" class="form-control" required /><br/>
    </div>
    <div class="form-group">
      <label>Cantidad:</label>
      <input type="text" name="cantidad" class="form-control" required /><br/>
    </div>
    <div class="form-group">
      <label>Precio:</label>
      <input type="text" name="precio" class="form-control" required /><br/>
    </div>
      <div class="form-group">
      <label>Caducidad:</label>
      <input type="date" name="caducidad" class="form-control" required /><br/>
    </div>
 <select class="form-control" id="seccion" name="seccion"  required>
    <option value="">--Selecciona una Seccion--</option>
    <c:forEach items="${secciones}" var="seccion">
      <option value="${seccion.id}">${seccion.nombre}</option>
    </c:forEach>
  </select>
    <input type="submit" value="Continuar" class="btn btn-primary mt-4" name="continuar_insertar_seccion"/>
  </form>
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.min.js"></script>
	
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-3i9b9rC0vT8W1QzYj0R+G4a/4NF/4t2ZGLtVnRZSjFxsHfoNtFrqRHtM3zIn5xIFc5KB5ZXIa5b5JZ9mQeQG8Q==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
   <c:if test="${not empty mensaje}">
  <div class="alert alert-danger">${mensaje}</div>
</c:if>
<h1>Insertar Producto</h1>
  
  <div class="container mt-5">
  
  <form method="post" action="InsertarProducto" id="form_insertar_producto" >
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
 <select class="form-control" id="seccion" name="seccion"  >
    <option value="0">--Selecciona una Seccion--</option>
    <c:forEach items="${secciones}" var="seccion">
      <option value="${seccion.id}">${seccion.nombre}</option>
    </c:forEach>
  </select>
  <br>
  <p>Supermercados: </p>
   <c:forEach items="${Supermercados}" var="supermercado">
    <div class="form-check">
  <input class="form-check-input" type="checkbox" value="${supermercado.id}" id="${supermercado.id}" name="id_supermercado">
  <label class="form-check-label" for="${supermercado.id}">
${supermercado.nombre}
  </label>
</div>
</c:forEach>
    <input type="submit" value="Continuar" class="btn btn-primary mt-4" name="continuar_insertar_seccion"/>
  </form>
</div>
</body>
</html>
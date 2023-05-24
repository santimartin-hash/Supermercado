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
<h1>Modificar Producto</h1>
  
  <div class="container mt-5">
  
  <form method="post" action="ModificarProducto" id="form_modificar_producto" >
   <div class="form-group">
      <label>Id:</label>
      <input type="text" name="id" class="form-control" value="${producto.id}" required /><br/>
    </div>
      <div class="form-group">
      <label>Codigo:</label>
      <input type="text" name="codigo" class="form-control" value="${producto.codigo}" required /><br/>
    </div>
    <div class="form-group">
      <label>Nombre:</label>
      <input type="text" name="nombre" class="form-control" value="${producto.nombre}" required /><br/>
    </div>
    <div class="form-group">
      <label>Cantidad:</label>
      <input type="text" name="cantidad" class="form-control" value="${producto.cantidad}" required /><br/>
    </div>
    <div class="form-group">
      <label>Precio:</label>
      <input type="text" name="precio" class="form-control"  value="${producto.precio}" required /><br/>
    </div>
      <div class="form-group">
      <label>Caducidad:</label>
      <input type="date" name="caducidad" class="form-control"  value="${producto.caducidad}" required /><br/>
    </div>
 <div class="form-group">
      <label>id seccion:</label>
      <input type="text" name="seccion" class="form-control"  value="${producto.id_seccion}" required /><br/>
    </div>
    <input type="submit" value="Continuar" class="btn btn-primary mt-4" name="continuar_insertar_seccion"/>
  </form>
</div>
</body>
</html>
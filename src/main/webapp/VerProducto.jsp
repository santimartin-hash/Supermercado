<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
   <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css">
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.min.js"></script>
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-3i9b9rC0vT8W1QzYj0R+G4a/4NF/4t2ZGLtVnRZSjFxsHfoNtFrqRHtM3zIn5xIFc5KB5ZXIa5b5JZ9mQeQG8Q==" crossorigin="anonymous" referrerpolicy="no-referrer"/>
</head>
<body>

  <div class="container">
   <div class="d-flex justify-content-end mt-2">
      <form class="form-inline d-flex">
        <input class="form-control mr-2" type="search" placeholder="Introduce Codigo o Nombre" aria-label="cadena" name="cadena" autocomplete="off">
        <button class="btn btn-outline-success" type="submit">Buscar</button>
      </form>
    </div>
     <div class="d-flex justify-content-end mt-2">
      <form class="form-inline d-flex">
        <input class="form-control mr-2" type="number" step="0.1" placeholder="Precio minimo" aria-label="PrecioMin" name="PrecioMin" autocomplete="off">
        <input class="form-control mr-2" type="number" step="0.1" placeholder="Precio maximo" aria-label="PrecioMax" name="PrecioMax" autocomplete="off">
        <button class="btn btn-outline-success" type="submit">Filtrar</button>
      </form>
    </div>
<a href="VerProductos?Filtro=1" class="btn btn-secondary">Codigo Ascendente</a>
<a href="VerProductos?Filtro=2" class="btn btn-secondary">Codigo Descendente</a>
     <h3 class="mt-5">Productos:</h3>
    <div class="table-container mt-5">
      <table class="table table-striped table-dark table-responsive-md mt-5">
        <thead>
          <tr>
            <th>id</th>
            <th>codigo</th>
            <th>Nombre</th>
            <th>Cantidad</th>
            <th>Precio</th>
            <th>Caducidad</th>
            <th>NombreSeccion</th>
            <th><a href="InsertarProducto" class="btn btn-primary" style="margin-left: 40px;">InsertarProducto</a></th>
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${productos}" var="producto" varStatus="loop">                  
            <tr>
              <td>${producto.id}</td>
              <td>${producto.codigo}</td>
              <td>${producto.nombre}</td>
              <td>${producto.cantidad}</td>
              <td>${producto.precio}</td>
              <td>${producto.caducidad}</td>
              <td>${producto.nombreSeccion}</td>
              <td><a href="ModificarProducto?producto_id=${producto.id}" class="btn btn-secondary" style="margin-left: 40px;">Modificar</a></td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</body>
</html>

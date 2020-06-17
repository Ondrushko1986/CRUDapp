
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Preproject1</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
          integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
          crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
          integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
          crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
          integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
          crossorigin="anonymous"></script>
</head>
<body>

<div class="container mt-3">
  <h2>User list</h2>
  <p>CRUD Example</p>
  <button type="button" class="btn btn-primary" onclick="window.location='/userAdd'">Create User</button>
</div>

<jsp:useBean id="userList" type="java.util.List<model.User>" scope="request"/>

<div class="container mt-3">
  <table class="table table-bordered">
    <thead>
    <tr>
      <th scope="col" style="width: 6%">ID</th>
      <th scope="col">Login</th>
      <th scope="col">Name</th>
      <th scope="col" style="width: 15%">Action</th>
    </tr>
    </thead>
    <tbody id="table">

    <c:forEach items="${userList}" var="user">
      <tr>
        <th scope="row">${user.id}</th>
        <td>${user.login}</td>
        <td>${user.name}</td>
        <td>
          <button type="button" class="btn btn-outline-primary" onclick="location.href='/userEdit?id=${user.id}'">Edit</button>
          <button type="button" class="btn btn-outline-danger" onclick="location.href='/userDelete?id=${user.id}'">Delete</button>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>

</body>
</html>
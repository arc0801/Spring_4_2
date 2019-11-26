<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member Login</title>
<c:import url="../layout/bootStrap.jsp" />
</head>
<body>
<c:import url="../layout/nav.jsp" />


<div class="container">
  <h2>ID SEARCH</h2>
  <form action="./memberSearchId" method="post">
  
    <div class="form-group">
      <label for="phone">PHONE:</label>
      <input type="number" class="form-control" id="phone" placeholder="Enter phone" name="phone">
    </div>
    
    <div class="form-group">
      <label for="email">EMAIL:</label>
      <input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
    </div>
    
    
    <button type="submit" class="btn btn-default">아이디 찾기</button>
    
  </form>
</div>




</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/jquery.validate.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/jquery.validate.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/additional-methods.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/additional-methods.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<nav class="navbar navbar-inverse">
    <div>
      <ul class="nav navbar-nav">
      	<li><a><b>Welcome ${user} !</a></li></b><br><br>
      </ul>
      </div>
      <button class= "col-sm-2 col-sm-offset-8" type="submit" value = "submit" class= "button btn-primary form-control">Sign out</button>
</nav>
      
      
<form class="navbar-form">${message}</form>
</body>
<body>

<form class= "text-center" action="WelcomeOutput.jsp"  method="post">
<div class= "col-sm-4 col-sm-offset-2">
<button type="submit" value = "submit" class= "button btn-primary form-control">Search Restaurants</button>
</div>
</form>
<br>
<br>
<form class= "text-center" action="userEdits.jsp"  method="post">
<div class= "col-sm-4 col-sm-offset-2">
<button type="submit" value = "submit" class= "button btn-primary form-control">Edit User Information</button>
</div>
</form>
</body>
</html>
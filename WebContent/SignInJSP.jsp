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
</head>
<body style="background-color:#FFCCFF">
<nav class="navbar navbar-inverse">
    <div class="col-sm-6 col-sm-offset-5">
      <ul class="nav navbar-nav">
      	<li><a><b>Sign In Below</a></li></b><br><br>
      </ul>
    </div>
</nav>
<br><br><br>
<div class ="col-sm-4 col-sm-offset-4">
	<p> ${invalid_user}<p>
	<form class= "text-center" action="SignIn"  method="post">
		Email:<br>
		<input type="text" class="form-control" placeholder= "ex. join@domain.com" name="email" required> </input><br>
		<br>
		Zip Code:<br>
		<input type="password" class="form-control" placeholder="ex. 12345" name="password" required> </input><br>
		<br>
		<button type="submit"  value = "submit" class= "button btn-primary form-control">Sign In</button>
	</form>
</div>
</body>
</html>
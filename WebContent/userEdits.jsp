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
      	<li><a><b>Edit User Information</a></li></b><br><br>
      </ul>
    </div>
</nav>
Please enter the information that you would like altered. <br>
Leave field(s) blank to keep your old information.
<br>
<br>
<div class ="class=col-sm-2 col-sm-2 col-sm-offset-0">
<form class= "text-center" action="WelcomeOutputs"  method="post">
<br>
Email:<br>
<input type="text" placeholder= "ex. join@domain.com" name="email" required> </input><br>
<br>
Review Number:<br>
<input type="number" placeholder="ex. 1" name="review"> </input><br>
<br>
<button type="submit" value = "submit" class= "button btn-primary form-control">Update</button>
</form>
</div>
</body>
</html>
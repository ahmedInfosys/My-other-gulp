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
<title>Find People</title>
<style>h1{background: black; text:white}</style>
</head>
<body>
<nav class="navbar navbar-inverse">
    <div>
      <ul class="nav navbar-nav">
      	<li><a><b>Welcome to GULP!</a></li></b><br><br>
      	<li><a><em>Search and review the world best restaurants and bars!</a></li></em>
      </ul>
  <div class="col-sm-2 col-sm-offset-10">
            <div class="account-wall">
                <form action= "SignInJSP.jsp" class="form-signin">
                <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
                </form>
            </div>
            
              <div class="account-wall">
                <form action= "SignUp" class="form-signin">
                <button class="btn btn-lg btn-primary btn-block" style="background-color:#CC66FF" type="submit">Create Account</button>
               </form>
            </div>
      
        </div>
  </nav>
  


  
	<div class ="panel panel-primary col-sm-4 col-sm-offset-4">
		<div class ="panel-heading"> <span class="glyphicon glyphicon-search"></span> Restaurant Search</div>
		<div class ="panel-body">
			<form action ="Welcome" method="POST">
				<div class="form-group">
					<label for="query">  Search for restaurant name: </label>
					<input type="text" class="form-control" placeholder="enter search here" name="query"/>
				<br>
				<div class="form-group">
					<button type="submit" value = "submit" class= "button btn-primary form-control">Submit</button>
				</div>
			</form>
		</div>
	
	</div>
</body>
</html>
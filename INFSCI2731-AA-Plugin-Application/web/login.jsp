<%-- 
    Document   : Login Module
    Created on : Apr 4, 2016, 11:10:07 AM
    Author     : Siddharth
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Login Page</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
<link href="/stylesheets/carousel.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<div class="jumbotron">
			<div class="form-group"></div>
			<form class="form-horizontal login" method="post"
				action="authenticate">

				<div class="form-group">

					<label class="col-sm-2 control-label">User Name</label>

					<div class="col-sm-4">
						<input type="text" class="form-control" id="username"
							name="username" required placeholder="Enter Username Name">
					</div>
				</div>

				<div class="form-group">
					<label for="password" class="col-sm-2 control-label">Password</label>

					<div class="col-sm-4">
						<input type="password" class="form-control" id="password"
							name="password" required placeholder="Enter Password">
					</div>
				</div>
				<div class="form-group col-sm-2 control-label">
					<button type="submit" class="btn btn-default">Submit</button>
				</div>
                                
			</form>
			<form class="form-horizontal login">
				<div class="form-group col-sm-2 control-label">
				<a type="submit" class="btn btn-default" href="forgotpassword">Forgot Password</a>
				</div>
			</form>
                       
		</div>
                <p>New user? <a href="signup.jsp">Sign up here</a> 
	</div>
	<!-- FOOTER -->
	<div class="container marketing">

		<footer>
			<p>
				&copy; 2016 E-Commerce Security &middot; <a href="#">Privacy</a>
				&middot; <a href="#">Terms</a>
			</p>
		</footer>

	</div>
	<!-- /.container -->

	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script
		src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
</body>
</html>
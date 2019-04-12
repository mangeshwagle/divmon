<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<!-- <link rel="icon" href="../../../../favicon.ico"> -->

<title>Login | DivMon</title>

<!-- Bootstrap core CSS -->
<link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!--  jQuery -->
<script src="/jquery/jquery-3.3.1.min.js" type="text/javascript"></script>

<!-- Custom styles for this template -->
<link href="/css/login.css" rel="stylesheet">

<!-- Custom Scripts -->
<script src="/js/login.js" type="text/javascript"></script>

</head>

<body class="text-center">
<%@include file="/menu/home_menu.jsp" %>
	<form class="form-signin" onsubmit="login()">
		<h1 class="h3 mb-3 font-weight-normal">Login</h1>
		
		<label for="email" class="sr-only">Email address</label>
		<input type="email" id="email" class="form-control" placeholder="Email address" required autofocus>
		
		<label for="password" class="sr-only">Password</label>
		<input type="password" id="password" class="form-control" placeholder="Password" required>
		
		<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
		<p class="mt-5 mb-3 text-muted">&copy; DivMon 2019-2020</p>
	</form>
</body>
</html>

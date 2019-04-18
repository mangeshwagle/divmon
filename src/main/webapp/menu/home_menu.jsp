<style>
body
{
	padding-top: 4.5rem;
}
</style>
<script type="text/javascript">
$(document).ready(function() {
	// get current URL path and assign 'active' class
	var pathname = window.location.pathname;
	$('.navbar-nav > li > a[href="'+pathname+'"]').parent().addClass('active');
	var user = JSON.parse(sessionStorage.getItem("user"));
	if(user != null)
		window.location.href = "profile.jsp";
})
</script>
<div id="header">
	<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
		<a class="navbar-brand" href="index.jsp">DivMon</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarCollapse" aria-controls="navbarCollapse"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarCollapse">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link" href="/index.jsp">Home
						<span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="/register.jsp">Register</a></li>
				<li class="nav-item"><a class="nav-link" href="/login.jsp">Login</a></li>
			</ul>
		</div>
	</nav>
</div>
<script src="/bootstrap/js/bootstrap.min.js"></script>
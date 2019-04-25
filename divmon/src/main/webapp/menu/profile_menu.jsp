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
	if(user == null)
		window.location.href = "login.jsp";
	document.getElementById("username").innerHTML = "DivMon &nbsp;&nbsp;| &nbsp;&nbsp;" + user.name;
})
function logout()
{
	sessionStorage.clear();
	window.location.href = "login.jsp";
}
</script>
<div id="header">
	<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
		<a class="navbar-brand" href="index.jsp" id="username">DivMon</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarCollapse" aria-controls="navbarCollapse"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarCollapse">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link" href="/profile.jsp">Profile
						<span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="/friends.jsp">Friends</a></li>
				<li class="nav-item"><a class="nav-link" href="/groups.jsp">Groups</a></li>
				<li class="nav-item"><a class="nav-link" href="/transactions.jsp">Transactions</a></li>
			</ul>
			<ul class="navbar-nav px-3">
				<li class="nav-item text-nowrap"><a class="nav-link" href="#" onclick="logout()">Logout</a></li>
			</ul>
		</div>
	</nav>
</div>
<script src="/bootstrap/js/bootstrap.min.js"></script>
function login()
{
	var email = document.getElementById("email").value;
	var password = document.getElementById("password").value;

	var user = JSON.stringify({
		"email" : email,
		"password" : password
	});
	$.ajax
	({
		type : 'POST',
		url : "http://localhost:8055/login",
		contentType : "application/json",
		data : user,
		async: false,
	    cache: false,
		statusCode:
		{
			200:	function(user)
					{
						sessionStorage.setItem("user", JSON.stringify(user));
					},
			204:	function()
					{
						alert("Incorrect email or password!");
					}
		}
	});
}
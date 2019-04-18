function validatePassword()
{
	var password = document.getElementById('password');
	var confirm_password = document.getElementById('confirm_password');
	if (password.value != confirm_password.value)
	{
		confirm_password.setCustomValidity("Passwords Don't Match");
	}
	else
	{
		confirm_password.setCustomValidity("");
	}
}

function register() 
{
	var fullname = document.getElementById("fullname").value;
	var email = document.getElementById("email").value;
	var password = document.getElementById("password").value;

	var user = JSON.stringify({
		"name"		: fullname,
		"email"		: email,
		"password"	: password
	});
	$.ajax
	({
		type : 'POST',
		url : "http://localhost:8055/register",
		contentType : "application/json",
		data : user,
		async: false,
	    cache: false,
		error : function(jqXHR, exception) {
			alert("Unable to register");
		},
		success : function() {
		}
	});
}
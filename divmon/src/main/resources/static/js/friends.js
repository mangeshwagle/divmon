var user = JSON.parse(sessionStorage.getItem("user"));
$(document).ready(function() {
	showFriends();
})

function showFriends()
{
	var api = "http://localhost:8055/showfriends/" + user.id;
	var friendList = "";
	$.get(api , function(data, status) {
	    for(var i = 0; i < data.length; i++)
	    {
	    	friendList += 	'<div class="media text-muted pt-3">' +
					    	'<div class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">' +
					    	'<div class="d-flex justify-content-between align-items-center w-100">' +
					    	'<strong class="text-gray-dark">' + data[i].name + '</strong>' +
					    	'<a href="#">' + data[i].id + '</a>' +
					    	'</div>' +
					    	'<span class="d-block">' + data[i].email + '</span>' +
					    	'</div>' +
					    	'</div>';
	    }
	    $("#friendList").html(friendList);
	});
}

function addFriend()
{
	var id = user.id;
	var friendEmail = document.getElementById("friendEmail").value;
	var friendship = 	{
							"id" : user.id,
							"email" : friendEmail
						};
	$.ajax
	({
		type : 'POST',
		url : "http://localhost:8055/addfriend",
		data : friendship,
		async: false,
	    cache: false,
		statusCode:
		{
			200:	function()
					{
						showFriends();
					},
			204:	function()
					{
						alert("Incorrect Email ID");
					}
		}
	});
}
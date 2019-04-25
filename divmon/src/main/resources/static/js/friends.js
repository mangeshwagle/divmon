var user = JSON.parse(sessionStorage.getItem("user"));
$(document).ready(function() {
	showFriends();
})

var friends = {};

sessionStorage.removeItem("friend");

function showFriends()
{
	var api = "http://localhost:8055/showfriendswithtotal/" + user.id;
	var friendList = "";
	$.get(api , function(data, status) {
		friends = data;
	    for(var i = 0; i < data.length; i++)
	    {
	    	var oweString = "";
	    	var buttonColor = "btn-primary";
	    	
	    	if (data[i].total < 0)
	    	{
	    		data[i].total *= -1;
	    		oweString = "owes you";
	    		buttonColor = "btn-success";
	    	}
	    	else if (data[i].total > 0)
	    	{
	    		oweString = "is owed";
	    		buttonColor = "btn-danger";
	    	}
	    	
	    	friendList += 	'<div class="media text-muted pt-3">' +
					    	'<div class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">' +
					    	'<div class="d-flex justify-content-between align-items-center w-100">' +
					    	'<strong class="text-gray-dark">' + data[i].user.name + '</strong>' +
					    	'<strong class="text-gray-dark">' + oweString + '</strong>' +
					    	'<strong class="text-gray-dark">₹' + data[i].total + '</strong>' +
					    	'<button class="btn ' + buttonColor + '" onclick=friendTransaction(' + i + ')>Manage Transactions</button>' +
					    	'</div>' +
					    	'<span class="d-block">' + data[i].user.email + '</span>' +
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
function friendTransaction(id)
{
	sessionStorage.setItem("friend", JSON.stringify(friends[id].user));
	window.location.href = "friendtransaction.jsp";
}
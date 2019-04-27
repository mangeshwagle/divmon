var group = JSON.parse(sessionStorage.getItem("group"));

if(group == null)
	window.location.href = "groups.jsp";

$(document).ready(function() {
	showFriends();
})

var friends = {};

function showFriends()
{
	var api = "http://localhost:8055/getusersofgroup/" + group.id;
	var friendList = "";
	$.get(api , function(data, status) {
		friends = data;
	    for(var i = 0; i < data.length; i++)
	    {
	    	
	    	friendList += 	'<div class="media text-muted pt-3">' +
					    	'<div class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">' +
					    	'<div class="d-flex justify-content-between align-items-center w-100">' +
					    	'<strong class="text-gray-dark">' + data[i].name + '</strong>' +
					    	'<button class="btn btn-danger" onclick="remove(' + i + ')">Remove</button>' +
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
	var friendEmail = document.getElementById("friendEmail").value;
	var groupUser = 	{
							"groupId" : group.id,
							"email" : friendEmail
						};
	$.ajax
	({
		type : "POST",
		url : "http://localhost:8055/addusertogroup",
		data : groupUser,
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
function remove(id)
{
	var api = "http://localhost:8055/removeuserfromgroup/" + group.id + "/" + friends[id].id;
	$.get(api, function(data, status)
	{
		showFriends();
	});
}
var user = JSON.parse(sessionStorage.getItem("user"));
$(document).ready(function() {
	showGroups();
})

var groups = {};

sessionStorage.removeItem("group");

function showGroups()
{
	var api = "http://localhost:8055/getgroupssofuser/" + user.id;
	var groupList = "";
	$.get(api , function(data, status) {
		groups = data;
	    for(var i = 0; i < data.length; i++)
	    {	
	    	groupList += 	'<div class="media text-muted pt-3">' +
					    	'<div class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">' +
					    	'<div class="d-flex justify-content-between align-items-center w-100">' +
					    	'<strong class="text-gray-dark">' + data[i].name + '</strong>' +
					    	'<button class="btn btn-outline-success" onclick=manageGroup(' + i + ')>Manage Group</button>' +
					    	'</div>' +
					    	'<span class="d-block">' + /*data[i].user.email + */'</span>' +
					    	'</div>' +
					    	'</div>';
	    }
	    $("#groupList").html(groupList);
	});
}

function createGroup()
{
	var userSet = [];
	userSet.push(user);
	var groupName = document.getElementById("groupName").value;
	var group =	JSON.stringify	({
									"name" : groupName,
									"userSet" : userSet
								});
	alert(group);
	$.ajax
	({
		type : 'POST',
		url : "http://localhost:8055/creategroup",
		data : group,
		async: false,
	    cache: false,
		statusCode:
		{
			200:	function()
					{
						showGroups();
					},
			204:	function()
					{
						alert("Name could not be assigned");
					}
		}
	});
}
function manageGroup(id)
{
	sessionStorage.setItem("group", JSON.stringify(group[id]));
	window.location.href = "friendtransaction.jsp";
}
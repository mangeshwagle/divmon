var user = JSON.parse(sessionStorage.getItem("user"));
$(document).ready(function() {
	showGroups();
})

var groups = {};

sessionStorage.removeItem("group");

function showGroups()
{
	var api = "http://localhost:8055/getgroupsofuser/" + user.id;
	var groupList = "";
	$.get(api, function(data, status) {
		groups = data;
	    for(var i = 0; i < data.length; i++)
	    {	
	    	var userSize = groupUserCount(data[i].id);
	    	groupList += 	'<div class="media text-muted pt-3">' +
					    	'<div class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">' +
					    	'<div class="d-flex justify-content-between align-items-center w-100">' +
					    	'<strong class="text-gray-dark">' + data[i].name + '</strong>' +
					    	'<button class="btn btn-outline-success" onclick="groupTransaction(' + i + ')">Manage Transactions</button>' +
					    	'<button class="btn btn-outline-info" onclick="manageGroup(' + i + ')">Manage Group</button>' +
					    	'</div>' +
					    	'<span class="d-block">Members: ' + userSize + '</span>' +
					    	'</div>' +
					    	'</div>';
	    }
	    $("#groupList").html(groupList);
	});
}

function groupUserCount(id)
{
	var api = "http://localhost:8055/getusercount/" + id;
	var count = 0;
	$.ajax
	({
		type : "GET",
		url : api,
		data : user,
		async: false,
	    cache: false,
		error : function(jqXHR, exception) {
		},
		success : function(data) {
			count = data;
		}
	});
	return count;
}

function createGroup()
{
	var groupName = document.getElementById("groupName").value;
	var api = "http://localhost:8055/creategroup/" + groupName + "/" + user.id;
	$.get(api , function(data, status)
	{
		showGroups();
	});
}

function groupTransaction(id)
{
	sessionStorage.setItem("group", JSON.stringify(groups[id]));
	window.location.href = "grouptransaction.jsp";
}

function manageGroup(id)
{
	sessionStorage.setItem("group", JSON.stringify(groups[id]));
	window.location.href = "groupmanage.jsp";
}

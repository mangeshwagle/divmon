var user = JSON.parse(sessionStorage.getItem("user"));
var group = JSON.parse(sessionStorage.getItem("group"));
//Fix friend indexing
var friend = [];
if(group == null)
	window.location.href = "groups.jsp";

$(document).ready(function()
{
	var api = "http://localhost:8055/getusersofgroup/" + group.id;
	$.ajax
	({
		type : "GET",
		url : api,
		async: false,
	    cache: false,
		error : function(jqXHR, exception) {
		},
		success : function(data) {
			friend = data;
			showTransactions();
			var paidby = "";
			for(var i = 0; i < friend.length; i++)
			{
				if(friend[i].id == user.id)
					paidby += '<option value="' + friend[i].id + '" selected>Paid by Me</option>';
				else
					paidby += '<option value="' + friend[i].id + '">Paid by ' + friend[i].name + '</option>';
			}
			$("#paidby").append(paidby);
		}
	});
})

function showTransactions()
{
	var api = "http://localhost:8055/usergrouptransactionlist/" + user.id + "/" + group.id;
	var transactionList = "";
	$.get(api, function(data, status) {
	    for(var i = 0; i < data.length; i++)
	    {
	    	var d = new Date(data[i].date);
	    	var date = d.toDateString();
	    	var time = d.toLocaleTimeString();
	    	var buttonColor = "btn-primary";
	    	var buttonText = "Settle: ₹";
	    	var owe = "";
	    	console.log(friend);
	    	if(data[i].paid == true)
	    	{
	    		buttonText = "Settled: ₹";
	    		buttonColor = "disabled";
	    		buttonColor += (data[i].lenderId == user.id) ? " btn-outline-success" : " btn-outline-danger";
	    		owe = (data[i].lenderId == user.id) ? (friendName(data[i].borrowerId) + " owed you") : ("You owed " + friendName(data[i].lenderId));
	    	}
	    	else if(data[i].lenderId == user.id)
	    	{
	    		owe = friendName(data[i].borrowerId) + " owes you";
	    		buttonColor = "btn-success";
	    	}
	    	else
	    	{
	    		owe = "You owe " + friendName(data[i].lenderId);
	    		buttonColor = "btn-danger";
	    	}
	    	
	    	transactionList += 	'<div class="media text-muted pt-3">' +
					    		'<div class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">' +
					    		'<div class="d-flex justify-content-between align-items-center w-100">' +
					    		'<strong class="text-gray-dark">' + data[i].description + '</strong>' +
					    		'<strong class="text-gray-dark">' + date + '</strong>' +
					    		'<strong class="text-gray-dark">' + time + '</strong>' +
					    		'<strong class="text-gray-dark">' + owe + '</strong>' +
					    		'<button class="btn ' + buttonColor + '" onclick="settleTransaction(' + data[i].id + ')">' + buttonText + data[i].share + '</button>' +
					    		'</div>' +
					    		'<span class="d-block">Total: ₹' + data[i].amount + '</span>' +
					    		'</div>' +
					    		'</div>';
	    }
    	$("#transactionList").html(transactionList);
	});
}

function addTransaction()
{
	var paidby = document.getElementById("paidby");
	var lenderId = parseInt(paidby.options[paidby.selectedIndex].value);
	var description = document.getElementById("description").value;
	var groupId = group.id;
	var date = new Date();
	var amount = parseFloat(document.getElementById("amount").value);
	
	var transaction = 	JSON.stringify
							({
								"lenderId"		: lenderId,
								"groupId"		: groupId,
								"description"	: description,
								"date"			: date,
								"amount"		: amount
							});
	console.log(transaction);
	$.ajax
	({
		type : "POST",
		url : "http://localhost:8055/addgrouptransaction",
		contentType : "application/json",
		data : transaction,
		async: false,
	    cache: false,
		error : function(jqXHR, exception) {
			alert("Inccorect Details");
		},
		success : function() {
			showTransactions();
		}
	});
}

function friendName(id)
{
	for (var i = 0; i < friend.length; i++)
	{
		console.log(friend[i].name);
		if (friend[i].id == id)
			return friend[i].name;
	}
	return null;
}

function settleTransaction(id)
{
	var api = "http://localhost:8055/settlegrouptransaction/" + id;
	$.get(api, function(data, status)
	{
		showTransactions();
	});
}

function settleAllTransactions()
{
	var api = "http://localhost:8055/settleallgrouptransactions/" + user.id + "/" + group.id;
	$.get(api, function(data, status)
	{
		showTransactions();
	});
}
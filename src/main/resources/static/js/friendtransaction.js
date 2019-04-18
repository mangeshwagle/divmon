var user = JSON.parse(sessionStorage.getItem("user"));
var friend = JSON.parse(sessionStorage.getItem("friend"));

if(friend == null)
	window.location.href = "friends.jsp";

$(document).ready(function() {
	showTransactions();
	var paidby = '<option value="1">Paid by ' + friend.name + '</option>';
	$("#paidby").append(paidby);
})

function showTransactions()
{
	var api = "http://localhost:8055/transactionlist/" + user.id + "/" + friend.id;
	var transactionList = "";
	$.get(api , function(data, status) {
	    for(var i = 0; i < data.length; i++)
	    {
	    	var d = new Date(data[i].date);
	    	var date = d.toDateString();
	    	var time = d.toLocaleTimeString();
	    	var owe = "";
	    	if(data[i].lenderId == user.id)
	    		owe = friend.name + " owes you";
	    	else
	    		owe = "You owe " + friend.name;
	    	transactionList += 	'<div class="media text-muted pt-3">' +
					    		'<div class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">' +
					    		'<div class="d-flex justify-content-between align-items-center w-100">' +
					    		'<strong class="text-gray-dark">' + data[i].description + '</strong>' +
					    		'<strong class="text-gray-dark">' + date + '</strong>' +
					    		'<strong class="text-gray-dark">' + time + '</strong>' +
					    		'<strong class="text-gray-dark">' + owe + '</strong>' +
					    		'<a href="#">' + data[i].share + '</a>' +
					    		'</div>' +
					    		'<span class="d-block">' + data[i].amount + '</span>' +
					    		'</div>' +
					    		'</div>';
	    }
    	$("#transactionList").html(transactionList);
	});
}

function addTransaction()
{
	var paidby = document.getElementById("paidby");
	paidby = paidby.options[paidby.selectedIndex].value;
	var lenderId;
	var borrowerId;
	var description = document.getElementById("description").value;
	var amount = document.getElementById("amount").value;
	var share = document.getElementById("share").value;
	if(paidby == 0)
	{
		lenderId = user.id;
		borrowerId = friend.id;
	}
	else
	{
		borrowerId = user.id;
		lenderId = friend.id;
	}
	
	var transaction = 	JSON.stringify
							({
								"lenderId" 		: lenderId,
								"borrowerId"	: borrowerId,
								"description"	: description,
								"amount"		: amount,
								"share"			: share
							});
	$.ajax
	({
		type : 'POST',
		url : "http://localhost:8055/transaction",
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
function calcShareAmount()
{
	var amount = document.getElementById("amount").value;
	var percent = document.getElementById("sharepercent").value;
	var share = document.getElementById("share");
	share.value = amount * percent / 100;
}
function calcSharePercent()
{
	var amount = document.getElementById("amount").value;
	var percent = document.getElementById("sharepercent");
	var share = document.getElementById("share").value;
	percent.value = (amount - share) * 100 / amount;
}
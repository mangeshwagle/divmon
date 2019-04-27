<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<link rel="shortcut icon" href="/images/favicon.ico">
<link rel="icon" type="image/png" href="/images/favicon.png">

<title>Group Transactions| DivMon</title>

<!-- Bootstrap core CSS -->
<link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!--  jQuery -->
<script src="/jquery/jquery-3.3.1.min.js" type="text/javascript"></script>

<!-- Custom styles for this template -->
<link href="/css/pagetiles.css" rel="stylesheet">

<!-- Custom Scripts -->
<script src="/js/grouptransaction.js" type="text/javascript"></script>

</head>
<body>
<%@include file="/menu/profile_menu.jsp" %>
	<div role="main" class="container">
      <div class="my-3 p-3 bg-white rounded box-shadow">
      <div class="media text-muted pt-3">
          <img data-src="holder.js/32x32?theme=thumb&bg=007bff&fg=007bff&size=1" alt="" class="mr-2 rounded">
          <div class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
            <div class="d-flex justify-content-between align-items-center w-100">
				<h6>Description</h6>
				<h6>Date</h6>
				<h6>Time</h6>
				<h6>Paid By</h6>	
				<h6>Share</h6>				
            </div>
          </div>
        </div>
      <div id="transactionList"></div>
      </div>
       <div class="media text-muted pt-3">
          <div class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray w-100">
            <div class="d-flex justify-content-center align-items-center w-50" style="margin-left:25%;">
              <button class="btn btn-warning btn-block" onclick="settleAllTransactions()">Settle All Transactions</button>
            </div>
          </div>
        </div>
      <div class="media text-muted pt-3">
          <div class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
            <div class="d-flex justify-content-between align-items-center w-100">
              <select class="form-control divmonForm" id="paidby"></select>
              <input class="form-control divmonForm" type="text" placeholder="Description" aria-label="description" id="description">
              <input class="form-control divmonForm" type="number" placeholder="Amount" aria-label="amount" id="amount">
              <button class="btn btn-primary" onclick="addTransaction()">Add</button>
            </div>
          </div>
        </div>
    </div>
</body>
</html>
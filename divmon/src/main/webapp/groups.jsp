<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<link rel="shortcut icon" href="/images/favicon.ico">
<link rel="icon" type="image/png" href="/images/favicon.png">

<title>Groups | DivMon</title>

<!-- Bootstrap core CSS -->
<link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!--  jQuery -->
<script src="/jquery/jquery-3.3.1.min.js" type="text/javascript"></script>

<!-- Custom styles for this template -->
<link href="/css/pagetiles.css" rel="stylesheet">

<!-- Custom Scripts -->
<script src="/js/groups.js" type="text/javascript"></script>

</head>
<body>
<%@include file="/menu/profile_menu.jsp" %>
	<div role="main" class="container">
      <div class="my-3 p-3 bg-white rounded box-shadow" id="groupList">
      </div>
      <div class="media text-muted pt-3">
          <div class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
            <div class="d-flex justify-content-between align-items-center w-100">
              <input class="form-control divmonForm" type="text" placeholder="Enter Group Name" aria-label="groupName" id="groupName">
              <button class="btn btn-primary" onclick="createGroup()">Create Group</button>
            </div>
          </div>
        </div>
    </div>
</body>
</html>
<%-- 
    Document   : header
    Created on : 29-04-2019, 10:06:18
    Author     : Martin Frederiksen
--%>

<%@page import="data.models.RoleEnum"%>
<%@page import="data.models.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    User u = (User) session.getAttribute("user");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <base href="${pageContext.request.contextPath}/" />

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="css/styles.css">
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="js/main.js"></script>
        <script src="js/ajax.js"></script>
        <title>Fog</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            
            <%if (u != null && (u.getRole().equals(RoleEnum.ADMIN) || u.getRole().equals(RoleEnum.EMPLOYEE))) {%>
            <a class="navbar-brand" href=FrontController?command=requestList></a>
            <%}else if (u != null && u.getRole().equals(RoleEnum.CUSTOMER)) {%>
            <a class="navbar-brand" href=FrontController?command=request></a>
            <%}else{%>
            <a class="navbar-brand" href=#></a>
            <%}%>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <%if (u != null && u.getRole().equals(RoleEnum.ADMIN)) {%>
                    <li class="nav-item active">
                        <a class="nav-link" href="FrontController?command=requestList">Requests<span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="FrontController?command=showUsers">Users</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="FrontController?command=orders">Orders</a>
                    </li>
                    <%}%>

                    <%if (u != null && u.getRole().equals(RoleEnum.EMPLOYEE)) {%>
                    <li class="nav-item active">
                        <a class="nav-link" href="FrontController?command=request">Request<span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="FrontController?command=requestList">Requests</a>
                    </li>
                    <%}%>

                    <%if (u != null && u.getRole().equals(RoleEnum.CUSTOMER)) {%>
                    <li class="nav-item active">
                        <a class="nav-link" href="FrontController?command=request">Request<span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="FrontController?command=orders">Orders</a>
                    </li>
                    <%}%>

                    <%if (u != null) {%>
                    <li class="nav-item">
                        <a class="nav-link" href="FrontController?command=logout"><i class="fas fa-sign-out-alt"></i> Logout</a>
                    </li>
                    <%}%>
                </ul>
            </div>
        </nav>
        <div class="main">

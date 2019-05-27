<%-- 
    Document   : index
    Created on : 29-04-2019, 10:12:29
    Author     : Martin Frederiksen
--%>

<%@include file="header.jsp" %>

<form class ="greyBox login" method="POST" id="ajaxForm">
    <div id="errorBox" class="alert alert-danger" role="alert">
        <p id="message"></p>
    </div>
    <div id="successBox" class="alert alert-success" role="alert"></div>
    <div class="form-group">
        <label>Email</label>
        <input type="email" class="form-control" id="exampleInputEmail1" name="email"  placeholder="Ex. email@somewhere.dk">
    </div>
    <div class="form-group">
        <label>Password</label>
        <input type="password" class="form-control" id="exampleInputPassword1" name="password" placeholder="*********">
    </div>
    <button type="submit" class="btn btn-dark" formaction="FrontController?command=login">Log ind</button>
    <button type="submit" class="btn btn-dark" formaction="FrontController?command=register">Registrer</button>
    <br>

    <a href="FrontController?command=request" class="btn btn-primary requestButton" id="requestButton">Lav carport forespørgsel</a>
</form>

<%@include file = "footer.jsp" %>

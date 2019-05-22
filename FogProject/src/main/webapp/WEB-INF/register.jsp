<%-- 
    Document   : register
    Created on : 22-05-2019, 14:26:24
    Author     : Martin Frederiksen
--%>

<%@include file = "header.jsp" %>


<form class="greyBox" method="POST" id="ajaxForm">
    <div id="errorBox" class="alert alert-danger" role="alert">
        <p id="message"></p>
        <a class="btn btn-dark" href="FrontController?command=login">Gå til login</a>
    </div>
    <div id="successBox" class="alert alert-success" role="alert"></div>

    <thead class="thead-dark">
    <h2>Customer Information</h2>
    <hr />
    <div class="form-group">
        <label>Name</label>
        <input type="text" required="" class="form-control" name="name">
    </div>
    <div class="form-group">
        <label>Address</label>
        <input type="text" required="" class="form-control" name="address">
    </div>
    <div class="form-group">
        <label>ZIP & City</label>
        <input type="text" required="" class="form-control" name="zipCity">
    </div>
    <div class="form-group">
        <label>Phone</label>
        <input type="text" required="" class="form-control" name="phone">
    </div>
    <div class="form-group">
        <label>Email</label>
        <input type="email" required="" class="form-control" name="email">
    </div>
    <div class="form-group">
        <label>Password</label>
        <input type="password" required="" class="form-control" name="password">
    </div>
    <div class="form-group">
        <label>Note</label>
        <input type="text" class="form-control" name="note">
    </div>
    <button type="submit" class="btn btn-dark" formaction="FrontController?command=register">Opret bruger</button>
</form>


<%@include file = "footer.jsp" %>
<%-- 
    Document   : index
    Created on : 29-04-2019, 10:12:29
    Author     : Martin Frederiksen
--%>

<%@include file="header.jsp" %>

<form class ="greyBox login" method="POST">
  <div class="form-group">
      <!-- aria-describedby="userHelp" -->
      <input type="text" class="form-control" id="exampleInputEmail1" name="email"  placeholder="Enter username or email">
  </div>
  <div class="form-group">
      <input type="password" class="form-control" id="exampleInputPassword1" name="password" placeholder="Password">
  </div>
  <button type="submit" class="btn btn-dark" formaction="FrontController?command=login">Login</button>
  <button type="submit" class="btn btn-dark">Register</button>
  <br>
  
  <a href="FrontController?command=request" > I just want to request a carport </a>
</form>

<%@include file = "footer.jsp" %>

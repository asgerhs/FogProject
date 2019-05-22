<%-- 
    Document   : request
    Created on : May 1, 2019, 1:00:40 PM
    Author     : Andreas Vikke
--%>

<%@page import="data.models.Request"%>
<%@page import="java.util.ArrayList"%>
<%@page import="data.models.Material"%>
<%@include file="header.jsp" %>
<%
    ArrayList<Material> mats = (ArrayList<Material>) session.getAttribute("mats");
    Request r = null;
    int length = 0;
    int width = 0;
    int angle = 0;
    int shedLength = 0;
    int shedWidth = 0;
    if ((Request) session.getAttribute("request") != null) {
        r = (Request) session.getAttribute("request");
        length = r.getLength();
        width = r.getWidth();
        angle = r.getAngle();
        shedLength = r.getShedLength();
        shedWidth = r.getShedWidth();
    }
    User user = (User) session.getAttribute("user");
%>

<form class="greyBox" method="POST" id="ajaxForm">
    <div id="errorBox" class="alert alert-danger" role="alert">
        <p id="message"></p>
        <a class="btn btn-dark" href="FrontController?command=login">Gå til login</a>
    </div>
    <div id="successBox" class="alert alert-success" role="alert"></div>

    <thead class="thead-dark">
    <h2>Carport Information</h2>
    <hr />
    <div class="form-group">
        <label>Carport Width</label>
        <input type="number" required="" class="form-control" name="width" min="240" max="750" placeholder="Min. 240 - Max. 750" value="<%=width%>">
    </div>
    <div class="form-group">
        <label>Carport Length</label>
        <input type="number" required="" class="form-control" name="length" min="240" max="780" placeholder="Min. 240 - Max. 780" value="<%=length%>">
    </div>
    <div class="form-group">
        <label>Roof Type</label>
        <select class="custom-select" required="" name="roof">
            <% if (mats != null)
                    for (Material m : mats) {%>
            <option value="<%=m.getRef()%>"><%=m.getName()%></option>
            <% }%>
        </select>
    </div>
    <div class="form-group">
        <label>Angle</label>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <div class="input-group-text">
                    <input type="checkbox" name="angleCheck" <%=(angle > 0) ? "checked" : ""%> id="angleToggle">
                </div>
            </div>
            <input type="number" id="angleToggleNumber" disabled name="angle" class="form-control" value="<%=angle%>">
        </div>
    </div>

    <br /><br />
    <h2>Shed Information</h2>
    <hr />
    <div class="form-check">
        <input class="form-check-input" type="checkbox" <%=(shedLength > 0 && shedWidth > 0) ? "checked" : ""%> value="" name="shed" id="shedCheckbox">
        <label class="form-check-label">
            Shed
        </label>
    </div>
    <div class="form-group shedToggle" id="shedToggleWidth">
        <label>Shed Width</label>
        <input type="number" required="" class="form-control" name="shedWidth" min="0" max="750" placeholder="Min. 240 - Max. 750" value="<%=shedWidth%>">
    </div>
    <div class="form-group shedToggle" id="shedToggleLength">
        <label>Shed Length</label>
        <input type="number" required="" class="form-control" name="shedLength" min="0" max="780" placeholder="Min. 240 - Max. 780" value="<%=shedLength%>">
    </div>

    <br /><br />
    <h2>Customer Information</h2>
    <hr />
    <% if (user == null) { %>
    <div class="form-group">
        <label>Name</label>
        <input type="text" required="" class="form-control" name="name" value="test">
    </div>
    <div class="form-group">
        <label>Address</label>
        <input type="text" required="" class="form-control" name="address" value="test">
    </div>
    <div class="form-group">
        <label>ZIP & City</label>
        <input type="text" required="" class="form-control" name="zipCity" value="test">
    </div>
    <div class="form-group">
        <label>Phone</label>
        <input type="phone" required="" class="form-control" name="phone" value="test">
    </div>
    <div class="form-group">
        <label>Email</label>
        <input type="email" required="" class="form-control" name="email" value="test@test">
    </div>
    <div class="form-group">
        <label>Password</label>
        <input type="password" required="" class="form-control" name="password">
    </div>
    <% } else {%>
    <div class="form-group">
        <label>Logged in as <%= user.getName()%> with email <%= user.getEmail()%></label>
        <input type="hidden" name="loggedin" value="true">
        <input type="hidden" name="email" value="<%= user.getEmail()%>">
    </div>
    <% }%>
    <div class="form-group">
        <label>Note</label>
        <input type="text" class="form-control" name="note" value="test">
        <%if (r != null) {%>
    </div>
    <input type="hidden" name="requestId" value="<%=r.getId()%>">
    <button type="submit" class="btn btn-dark" formaction="FrontController?command=request&submit=true">Updates request</button>
    <%} else {%>
</div>
<button type="submit" class="btn btn-dark" formaction="FrontController?command=request&submit=true">Send request</button>
<%}%>
</form>

<%@include file="footer.jsp" %>
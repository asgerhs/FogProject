<%-- 
    Document   : request
    Created on : May 1, 2019, 1:00:40 PM
    Author     : Andreas Vikke
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="data.models.Material"%>
<%@include file="header.jsp" %>
<%
    ArrayList<Material> mats = (ArrayList<Material>)session.getAttribute("mats");
%>

<form method="POST">
    <h2>Carport Information</h2>
    <hr />
    <div class="form-group">
        <label>Carport Width</label>
        <input type="number" required="" class="form-control" name="width" min="240" max="750" placeholder="Min. 240 - Max. 750">
    </div>
    <div class="form-group">
        <label>Carport Length</label>
        <input type="number" required="" class="form-control" name="length" min="240" max="780" placeholder="Min. 240 - Max. 780">
    </div>
    <div class="form-group">
        <label>Roof Type</label>
        <select class="custom-select" required="" name="roof">
            <% if(mats != null)
                for(Material m : mats) { %>
                <option value="<%=m.getRef() %>"><%=m.getName() %></option>
            <% } %>
          </select>
    </div>
    
    <br /><br />
    <h2>Shed Information</h2>
    <hr />
    <div class="form-group">
        <label>Shed Width</label>
        <input type="number" required="" class="form-control" name="shedWidth" min="240" max="750" placeholder="Min. 240 - Max. 750">
    </div>
    <div class="form-group">
        <label>Shed Length</label>
        <input type="number" required="" class="form-control" name="shedLength" min="240" max="780" placeholder="Min. 240 - Max. 780">
    </div>
     
    <br /><br />
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
        <input type="phone" required="" class="form-control" name="phone">
    </div>
    <div class="form-group">
        <label>Email</label>
        <input type="email" required="" class="form-control" name="email">
    </div>
    <div class="form-group">
        <label>Note</label>
        <input type="text" required="" class="form-control" name="note">
    </div>
    
    <button type="submit" class="btn btn-primary" formaction="FrontController?command=request&submit=true">Send request</button>
</form>

<%@include file="footer.jsp" %>
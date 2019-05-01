<%-- 
    Document   : index
    Created on : 29-04-2019, 10:12:29
    Author     : Martin Frederiksen
--%>

<%@include file="header.jsp" %>

<form id="showPartsForm" name="showParts" method="POST">
    <div class="form-group"> 
        <button type="submit" class="btn btn-primary" formaction="FrontController?command=showParts">Generate partslist</button>
    </div>
</form>

<form>
    <div class="form-group">
        <label>Carport Width</label>
        <input type="number" class="form-control" name="width" placeholder="Min. 240 - Max. 750">
    </div>
    <div class="form-group">
        <label>Carport Length</label>
        <input type="number" class="form-control" name="length" placeholder="Min. 240 - Max. 780">
    </div>
    <div class="form-group">
        <label>Roof Type</label>
        <input type="number" class="form-control" name="roof" placeholder="Min. 240 - Max. 750">
    </div>
    
    <div class="form-group">
        <label>Shed Width</label>
        <input type="number" class="form-control" name="shedWidth" placeholder="Min. 240 - Max. 750">
    </div>
    <div class="form-group">
        <label>Shed Length</label>
        <input type="number" class="form-control" name="shedLength" placeholder="Min. 240 - Max. 780">
    </div>
    
    <div class="form-group">
        <label>Name</label>
        <input type="text" class="form-control" name="name">
    </div>
    <div class="form-group">
        <label>Address</label>
        <input type="text" class="form-control" name="address">
    </div>
    <div class="form-group">
        <label>ZIP & City</label>
        <input type="text" class="form-control" name="zipCity">
    </div>
    <div class="form-group">
        <label>Phone</label>
        <input type="phone" class="form-control" name="phone">
    </div>
    <div class="form-group">
        <label>Email</label>
        <input type="email" class="form-control" name="email">
    </div>
    <div class="form-group">
        <label>Note</label>
        <input type="text" class="form-control" name="note">
    </div>
</form>

<%@include file = "footer.jsp" %>

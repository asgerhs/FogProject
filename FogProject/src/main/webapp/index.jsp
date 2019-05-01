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
<%@include file = "footer.jsp" %>

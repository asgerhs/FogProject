<%-- 
    Document   : parts
    Created on : 29-04-2019, 10:34:43
    Author     : Martin Frederiksen
--%>

<%@page import="data.models.Material"%>
<%@page import="data.models.Part"%>
<%@page import="java.util.ArrayList"%>
<%@page import="data.models.PartList"%>
<%@include file = "header.jsp" %>

<%
    ArrayList<Part> woodParts = (ArrayList<Part>) session.getAttribute("woodParts");
    ArrayList<Part> miscParts = (ArrayList<Part>) session.getAttribute("miscParts");
    String topViewSVG = (String) session.getAttribute("topViewSVG");
    String sideViewSVG = (String) session.getAttribute("sideViewSVG");
%>

<div class="greyBox">
    <table class="table">
        <thead class="thead-dark">
            <tr>
                <th scope="col">Træ & Tagplader</th>
                <th scope="col">Længde</th>
                <th scope="col">Antal</th>
                <th scope="col">Enhed</th>
                <th scope="col">Beskrivelse</th>
            </tr>
        </thead>
        <tbody>
            <% for (Part p : woodParts) {
                    Material m = p.getMaterial();
            %>
            <tr>
                <td><%=m.getName()%></td>
                <td><%=m.getLength() / 10%></td>
                <td><%=p.getQty()%></td>
                <td><%=m.getUnit()%></td>
                <td><%=p.getDescription()%></td>
            </tr>
            <%}%>
        </tbody>
    </table>
</div>
<div class="greyBox">
    <table class="table">
        <thead class="thead-dark">
            <tr>
                <th scope="col">Miscellaneous</th>
                <th scope="col">Længde</th>
                <th scope="col">Antal</th>
                <th scope="col">Enhed</th>
                <th scope="col">Beskrivelse</th>
            </tr>
            <% for (Part p : miscParts) {
                    Material m = p.getMaterial();
            %>
        <tbody>
            <tr>
                <td><%=m.getName()%></td>
                <td></td>
                <td><%=p.getQty()%></td>
                <td><%=m.getUnit()%></td>
                <td><%=p.getDescription()%></td>
            </tr>
            <%}%>
        </tbody>
        </thead>
    </table>
</div>
            
<div class="greyBox">
    <h1>Top View</h1>
    <%= topViewSVG %>
</div>

<div class="greyBox">
    <h1>Top View</h1>
    <%= sideViewSVG %>
</div>
    
<%@include file = "footer.jsp" %>
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
    int woodPrice = 0;
    int miscPrice = 0;
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
                <th scope="col">Pris</th>
            </tr>
        </thead>
        <tbody>
            <% for (Part p : woodParts) {
                    Material m = p.getMaterial();
                    woodPrice += p.getPrice();
            %>
            <tr>
                <td><%=m.getName()%></td>
                <td><%=m.getLength() / 10%></td>
                <td><%=p.getQty()%></td>
                <td><%=m.getUnit()%></td>
                <td><%=p.getDescription()%></td>
                <td><%=(p.getPrice()/100) + " DKK"%></td>
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
                <th scope="col">Pris</th>
            </tr>
            <% for (Part p : miscParts) {
                    Material m = p.getMaterial();
                    miscPrice+=p.getPrice();
            %>
        <tbody>
            <tr>
                <td><%=m.getName()%></td>
                <td></td>
                <td><%=p.getQty()%></td>
                <td><%=m.getUnit()%></td>
                <td><%=p.getDescription()%></td>
                <td><%=p.getPrice()/100 + " DKK"%>
            </tr>
            <%}%>
        </tbody>
    </table>
</div>
            
<div class="greyBox">
    <table class="table">
        <thead class="thead-dark">
            <tr>
                <th scope="col">Tabel</th>
                <th scope="col">Pris</th>
            </tr>
        <tbody>
            <tr>
                <td>Træ & tagplader</td>
                <td><%=woodPrice/100 + " DKK"%></td>
            </tr>
            <tr>
                <td>Miscellaneous</td>
                <td><%=miscPrice/100 + " DKK"%></td>
            </tr>
            <tr>
                <td><b>I alt</b></td>
                <td><b><%=(woodPrice + miscPrice)/100 + " DKK"%></b></td>
            </tr>
        </tbody>
    </table>
</div>
            
<div class="greyBox">
    <h1>Top View</h1>
    <%= topViewSVG %>
</div>

<div class="greyBox">
    <h1>Side View</h1>
    <%= sideViewSVG %>
</div>
    
<%@include file = "footer.jsp" %>
<%-- 
    Document   : order
    Created on : 14-05-2019, 11:51:24
    Author     : Martin Frederiksen
--%>
<%@page import="data.models.Order"%>
<%@page import="java.util.List"%>
<%@include file="header.jsp" %>

<%
    List<Order> orders = (List<Order>) session.getAttribute("orders");
%>

<div class="greyBox">
    <table class="table">
        <thead class="thead-dark">
            <tr>
                <th scope="col">Order id</th>
                <th scope="col">Email</th>
                <th scope="col">Carport brede</th>
                <th scope="col">Carport Længde</th>
                <th scope="col">Skur brede</th>
                <th scope="col">Skur Længde</th>
                <th scope="col">Tag</th>
                <th scope="col">Vinkel</th>
                <th scope="col">Styklise med tegning</th>
            </tr>
        </thead>
        <tbody>
            <% for (Order o : orders) {
            %>
            <tr>
                <td><%=o.getId()%></td>
                <td><%=o.getRequest().getUser().getEmail()%></td>
                <td><%=o.getRequest().getWidth()%></td>
                <td><%=o.getRequest().getLength()%></td>
                <td><%=o.getRequest().getShedWidth()%></td>
                <td><%=o.getRequest().getShedLength()%></td>
                <td><%=o.getRequest().getRoof()%></td>
                <td><%=o.getRequest().getAngle()%></td>
                <td>
                    <form id="requestForm" method="POST">
                        <input type="hidden" name="orderId" value="<%=o.getId()%>">
                        <button type="submit" class="btn btn-dark" formaction="FrontController?command=showParts">Vis</button>
                    </form>   
                </td>
            </tr>
            <%}%>
        </tbody>
    </table>
</div>

<%@include file="footer.jsp" %>
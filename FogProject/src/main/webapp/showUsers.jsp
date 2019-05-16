<%-- 
    Document   : showUsers
    Created on : 15-05-2019, 16:43:53
    Author     : Martin Frederiksen
--%>

<%@page import="java.util.List"%>
<%@include file = "header.jsp" %>

<%
    List<User> users = (List<User>) session.getAttribute("users");
%>

<div class="greyBox">
    <table class="table">
        <thead class="thead-dark">
            <tr>
                <th scope="col">Username</th>
                <th scope="col">Email</th>
                <th scope="col">Role</th>
            </tr>
        </thead>
        <tbody>
            <% for (User user : users) {
                    
            %>
            <tr>
                <td><%=user.getName()%></td>
                <td><%=user.getEmail()%></td>
                <td><%=user.getRole()%></td>
            </tr>
            <%}%>
        </tbody>
    </table>
</div>

<%@include file = "footer.jsp" %>
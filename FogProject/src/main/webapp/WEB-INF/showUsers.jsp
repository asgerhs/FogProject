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
                <th scope="col">Brugernavn</th>
                <th scope="col">Email</th>
                <th scope="col">Rolle</th>
                <th scope="col">Fjern bruger</th>
            </tr>
        </thead>
        <tbody>
            <% for (User user : users) { %>
            <tr>
                <td><%=user.getName()%></td>
                <td><%=user.getEmail()%></td>
                <td>
                    <form action="FrontController?command=updateRole" method="POST">
                        <input type="hidden" name="email" value="<%=user.getEmail()%>" />
                        <select class="custom-select" onchange="this.form.submit()" required="" name="role" id="roleSelector">
                            <% for(RoleEnum role : RoleEnum.values()) { %>
                                <option value="<%=role%>" <%=user.getRole() == role ? "selected" : "" %>><%=role%></option>
                            <% } %>
                        </select>
                    </form>
                </td>
                <td>
                    <form id="requestForm" method="POST">
                        <input type="hidden" name="userId" value="<%=user.getEmail()%>">
                        <button type="submit" class="btn btn-dark" formaction="FrontController?command=showUsers">Fjern</button>
                    </form> 
                </td>
            </tr>
            <%}%>
        </tbody>
    </table>
</div>

<%@include file = "footer.jsp" %>
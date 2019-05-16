<%-- 
    Document   : requestList
    Created on : 06-05-2019, 11:57:36
    Author     : William Sehested Huusfeldt
--%>

<%@page import="java.util.List"%>
<%@page import="logic.facades.RequestFacade"%>
<%@page import="data.models.Request"%>
<%@page import="java.util.ArrayList"%>  
<%@include file = "header.jsp" %>

<%
    ArrayList<Request> requests = (ArrayList<Request>) session.getAttribute("requestList");
%>
<div class="greyBox">
    <table class="table">
        <thead class="thead-dark">
            <tr>
                <th scope="col">Forespørgsels id</th>
                <th scope="col">Kundenavn</th>
                <th scope="col">Tlf</th>
                <th scope="col">E-mail</th>
                <th scope="col">Note</th>
                <th scope="col">Ændre forespørgsel</th>
                <th scope="col">Lav ordre</th>
                <th scope="col">Fjern forespørgsel</th>
            </tr>
        </thead>
        <tbody>
            <% for (Request r : requests) {
            %>
            <tr>
                <td><%=r.getId()%></td>
                <td><%=r.getUser().getName()%></td>
                <td><%=r.getUser().getPhone()%></td>
                <td><%=r.getUser().getEmail()%></td>
                <td><%=r.getNote()%></td>
                <td>
                    <form id="requestForm" method="POST">
                        <input type="hidden" name="requestId" value="<%=r.getId()%>">
                        <button type="submit" class="btn btn-dark" formaction="FrontController?command=request">Rediger</button>
                    </form>    
                </td>
                <td>
                    <form id="requestForm" method="POST">
                        <input type="hidden" name="orderId" value="<%=r.getId()%>">
                        <button type="submit" class="btn btn-dark" formaction="FrontController?command=requestList">Gennemfør</button>
                    </form>    
                </td>
                <td>
                    <form id="requestForm" method="POST">
                        <input type="hidden" name="requestId" value="<%=r.getId()%>">
                        <button type="submit" class="btn btn-dark" formaction="FrontController?command=requestList">Fjern</button>
                    </form>    
                </td>
            </tr>
            <%}%>
        </tbody>
    </table>
</div>

<%@include file = "footer.jsp" %>

<%-- 
    Document   : requestList
    Created on : 06-05-2019, 11:57:36
    Author     : William Sehested Huusfeldt
--%>

<%@page import="data.models.Request"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file = "header.jsp" %>

<!DOCTYPE html>
<%
    ArrayList<Request> requests = (ArrayList<Request>) session.getAttribute("requests");
%>

<table class="table">
    <thead class="thead-light">
    <tr>
      <th scope="col">ID</th>
      <th scope="col">Brede</th>
      <th scope="col">Længde</th>
      <th scope="col">Skur brede</th>
      <th scope="col">Skur længde</th>
      <th scope="col">Tagtype</th>
      <th scope="col">Vinkel</th>
      <th scope="col">Kundenavn</th>
      <th scope="col">Adresse</th>
      <th scope="col">ZipCity</th>
      <th scope="col">Tlf</th>
      <th scope="col">E-mail</th>
      <th scope="col">Note</th>
    </tr>
  </thead>
  <tbody>
    <% for(Request r : requests) { 
    %>
    <tr>
      <td><%=r.getId() %></td>
      <td><%=r.getWidth() %></td>
      <td><%=r.getLength() %></td>
      <td><%=r.getShedWidth() %></td>
      <td><%=r.getShedLength() %></td>
      <td><%=r.getRoof() %></td>
      <td><%=r.getAngle() %></td>
      <td><%=r.getName() %></td>
      <td><%=r.getAddress() %></td>
      <td><%=r.getZipCity() %></td>
      <td><%=r.getPhone() %></td>
      <td><%=r.getEmail() %></td>
      <td><%=r.getNote()%></td>
    </tr>
  <%}%>
  </tbody>
</table>

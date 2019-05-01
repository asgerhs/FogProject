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
    ArrayList<Part> parts = (ArrayList<Part>) session.getAttribute("parts");
%>

<table class="table">
  <thead class="thead-light">
    <tr>
      <th scope="col">Beskrivelse</th>
      <th scope="col">Længde</th>
      <th scope="col">Antal</th>
      <th scope="col">Enhed</th>
      <th scope="col">Beskrivelse</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">Træ & Tagplader VI SKAL MÅSKE HAVE DELT LISTEN OP I 2???</th>
      <td></td>
      <td></td>
      <td></td>
      <td></td>
    </tr>
    <% for(Part p : parts) { 
        Material m = p.getMaterial();
    %>
    <tr>
      <th><%=m.getName()%></th>
      <td><%=m.getLength()%></td>
      <td><%=p.getQty()%></td>
      <td><%=m.getUnit()%></td>
      <td><%=m.getName()%></td>
  </tbody>
  <%}%>
</table>

<%@include file = "footer.jsp" %>
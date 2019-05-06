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
      <th scope="row">Træ & Tagplader</th>
      <td></td>
      <td></td>
      <td></td>
      <td></td>
    </tr>
    <% for(Part p : woodParts) { 
        Material m = p.getMaterial();
    %>
    <tr>
      <td><%=m.getName()%></td>
      <td><%=m.getLength()/10%></td>
      <td><%=p.getQty()%></td>
      <td><%=m.getUnit()%></td>
      <td><%=p.getDescription()%></td>
    </tr>
  <%}%>
    <tr>
      <th scope="row">Miscellaneous</th>
      <td></td>
      <td></td>
      <td></td>
      <td></td>
    </tr>
    <% for(Part p : miscParts) { 
        Material m = p.getMaterial();
    %>
    <tr>
      <td><%=m.getName()%></td>
      <td></td>
      <td><%=p.getQty()%></td>
      <td><%=m.getUnit()%></td>
      <td><%=p.getDescription()%></td>
    </tr>
  <%}%>
  </tbody>
</table>

<%@include file = "footer.jsp" %>
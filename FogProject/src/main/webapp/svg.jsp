<%-- 
    Document   : svg
    Created on : Apr 30, 2019, 10:41:33 AM
    Author     : Asger Hermind Sørensen & Andreas Vikke
--%>

<style>
    rect {
        stroke:#000000; fill: transparent;
    }
    .post {
        stroke-width: 4px;
    }
    
    .shed{
        stroke-width: 3px;
        
    }
    line{
        stroke: red;
        stroke-width: 2px;
    }
</style>

<%
    int width = 780;
    int height = 600;
    int outhang = 35;
    int rafterCount = 15;
    int rafterSpace = 55;
    int postCount = 3;
    int shedLength = 210;
    int distant = (width - 10) / (postCount - 2 + 1);
%>

<svg width="1000" height="1000">

<!--
Sets the frame for the carport
-->
<rect x="100" y="100" height="<%= height%>" width="<%= width%>" />

<!--
The top and bottom Carport raft respectively 
-->
<rect x="100" y="<%= 100 + outhang%>" height="10" width="<%= width%>" />
<rect x="100" y="<%= 100 + height - outhang - 10%>" height="10" width="<%= width%>" />

<!--
For loop to create the necessary amount of rafts across the width of the Carport
-->
<% for (int i = 0; i < rafterCount; i++) {%>
<rect x="<%= 100 + rafterSpace * i%>" y="100" height="<%= height%>" width="10" />
<% }%>



<!-- Carport top left post -->
<rect class="post" x="200" y="<%= 100 + outhang%>" height="10" width="10" />

<!-- Carport top middle post -->
<rect class="post" x="<%= 100 + outhang + distant%>" y="<%= 100 + outhang%>" height="10" width="10" />

<!-- Carport top right post -->
<rect class="post" x="<%= 100 + width - 30%>" y="<%= 100 + outhang%>" height="10" width="10" />





<!-- Carport bottom left post -->
<rect class="post" x="200" y="<%= 100 + height - outhang - 10%>" height="10" width="10" />

<!-- Carport bottom middle post -->
<rect class="post" x="<%= 100 + outhang + distant%>" y="<%= 100 + height - outhang - 10%>" height="10" width="10" />

<!-- Carport bottom right post -->
<rect class="post" x="<%= 100 + width - 30%>" y="<%= 100 + height - outhang - 10%>" height="10" width="10" />






<!-- Shed left side raft -->
<rect class="shed" x="<%= 100 + width - shedLength - 30%>" y="<%= 100 + outhang%>" height="<%=height + (-outhang * 2)%>" width="10"/>

<!-- Shed right side raft -->
<rect class="shed"  x="<%= 100 + width - 30%>" y="<%= 100 + outhang%>" height="<%=height + (-outhang * 2)%>" width="10"/>

<!-- Shed top Raft -->
<rect class="shed" x="<%= 100 + width - shedLength - 30%>" y="<%= 100 + outhang%>" height="10" width="<%= shedLength%>"/>

<!-- Shed bottom Raft -->
<rect class="shed" x="<%= 100 + width - shedLength - 30%>" y="<%= 100 +height - outhang - 10%>" height="10" width="<%=shedLength%>"/>

<!-- Shed top left post -->
<rect class="post" x="<%=100 + width - shedLength - 30%>" y="<%= 100 + outhang%>" height="10" width="10"/>

<!-- Shed middle post left -->
<rect class="post" x="<%=100 + width - shedLength - 30%>" y="<%= 100 + (height / 2) - 10%>" height="10" width="10"/>

<!-- Shed bottom left post-->
<rect class="post" x="<%=100 + width - shedLength - 30%>" y="<%= 100 + height - outhang - 10%>" height="10" width="10"/>

<!-- Shed middle post right  -->
<rect class="post" x="<%= 100 + width - 30%>" y="<%=100 + (height / 2) - 10%>" height="10 " width="10"/>





<!-- top left top bottom right striped line -->
<line x1="<%= 100 + rafterSpace %>" y1="<%= 100 + outhang + 10%>" x2="<%=height + outhang + 10 - rafterSpace%>" y2="<%= 100 + width - shedLength - 10%>" stroke-dasharray="5,5" d="M5 20 l215 0"/>

<!-- bottom left to top right striped line -->
<line x1="<%= 100 + rafterSpace %>" y1="<%= 100 + height - outhang - 10%>" x2="<%=height + outhang + 10 - rafterSpace%>" y2="<%= 100 + outhang + 10%>" stroke-dasharray="5,5" d="M5 20 l215 0"/>

<svg width="1000" height="1000">

<line x1="100" y1="100" x2="<%=100 + width%>" y2="<%=100 + 10%>" style="stroke:rgb(0, 0, 0);stroke-width:1"/>
<line x1="100" y1="<%=100 + 40%>" x2="<%=100 + width%>" y2="<%=100 + 10 + 40%>" style="stroke:rgb(0, 0, 0); stroke-width:1"/>
<line x1="100" y1="100" x2="<%=100%>" y2="<%=100 + 40%>" style="stroke:rgb(0, 0, 0); stroke-width:1"/>
<line x1="<%=100 + width%>" y1="<%=100 + 10%>" x2="<%=100 + width%>" y2="<%=100 + 10 + 40%>" style="stroke:rgb(0, 0, 0); stroke-width:1"/>

<rect class="post" x="<%=100 + 40 + outhang%>" y="<%=100 + 40%>" width="10" height="250" style="stroke:rgb(0, 0, 0);stroke-width:1"/>
<rect class="post" x="<%=100 + 40 + outhang + distant%>" y="<%=100 + 45%>" width="10" height="245" style="stroke:rgb(0, 0, 0);stroke-width:1"/>

</svg>

<%= (-outhang * 2)%>

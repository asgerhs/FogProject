<%-- 
    Document   : svg
    Created on : Apr 30, 2019, 10:41:33 AM
    Author     : Andreas Vikke
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
        stroke-width: 4px;
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
<rect x="100" y="100" height="<%= height%>" width="<%= width%>" />

<rect x="100" y="<%= 100 + outhang%>" height="10" width="<%= width%>" />
<rect x="100" y="<%= 100 + height - outhang - 10%>" height="10" width="<%= width%>" />

<% for (int i = 0; i < rafterCount; i++) {%>
<rect x="<%= 100 + rafterSpace * i%>" y="100" height="<%= height%>" width="10" />
<% }%>

<rect class="post" x="200" y="<%= 100 + outhang%>" height="10" width="10" />
<rect class="post" x="<%= 100 + outhang + distant%>" y="<%= 100 + outhang%>" height="10" width="10" />
<rect class="post" x="<%= 100 + width - 30%>" y="<%= 100 + outhang%>" height="10" width="10" />

<rect class="post" x="200" y="<%= 100 + height - outhang - 10%>" height="10" width="10" />
<rect class="post" x="<%= 100 + outhang + distant%>" y="<%= 100 + height - outhang - 10%>" height="10" width="10" />
<rect class="post" x="<%= 100 + width - 30%>" y="<%= 100 + height - outhang - 10%>" height="10" width="10" />


<rect class="post" x="<%= 100 + width - 30%>" y="<%=100 + (height / 2) - 10%>" height="10 " width="10"/>
<rect class="shed"  x="<%= 100 + width - 30%>" y="<%= 100 + outhang%>" height="<%=height + (-outhang * 2)%>" width="10"/>

<rect class="post" x="<%=100 + width - shedLength - 30%>" y="<%= 100 + outhang%>" height="10" width="10"/>
<rect class="post" x="<%=100 + width - shedLength - 30%>" y="<%= 100 + (height / 2) - 10%>" height="10" width="10"/>
<rect class="post" x="<%=100 + width - shedLength - 30%>" y="<%= 100 + height - outhang - 10%>" height="10" width="10"/>
<rect class="shed" x="<%= 100 + width - shedLength - 30%>" y="<%= 100 + outhang%>" height="<%=height + (-outhang * 2)%>" width="10"/>

<rect class="shed" x="<%= 100 + width - shedLength - 30%>" y="<%= 100 + outhang%>" height="10" width="<%= shedLength%>"/>
<rect class="shed" x="<%= 100 + width - shedLength - 30%>" y="<%= 100 +height - outhang - 10%>" height="10" width="<%=shedLength%>"/>

<line x1="<%= 100 + rafterSpace %>" y1="<%= 100 + outhang + 10%>" x2="<%=height + outhang + 10%>" y2="<%= 100 + height - outhang -10%>"/>
<line x1="<%= 100 + rafterSpace %>" y1="<%= 100 + height - outhang - 10%>" x2="<%=height + outhang + 10%>" y2="<%= 100 + outhang + 10%>"/>



</svg>

<svg width="1000" height="1000">

<line x1="100" y1="100" x2="<%=100 + width%>" y2="<%=100 + 10%>" style="stroke:rgb(0, 0, 0);stroke-width:1"/>
<line x1="100" y1="<%=100 + 40%>" x2="<%=100 + width%>" y2="<%=100 + 10 + 40%>" style="stroke:rgb(0, 0, 0); stroke-width:1"/>
<line x1="100" y1="100" x2="<%=100%>" y2="<%=100 + 40%>" style="stroke:rgb(0, 0, 0); stroke-width:1"/>
<line x1="<%=100 + width%>" y1="<%=100 + 10%>" x2="<%=100 + width%>" y2="<%=100 + 10 + 40%>" style="stroke:rgb(0, 0, 0); stroke-width:1"/>

<rect class="post" x="<%=100 + 40 + outhang%>" y="<%=100 + 40%>" width="10" height="250" style="stroke:rgb(0, 0, 0);stroke-width:1"/>
<rect class="post" x="<%=100 + 40 + outhang + distant%>" y="<%=100 + 45%>" width="10" height="245" style="stroke:rgb(0, 0, 0);stroke-width:1"/>

</svg>

<%= (-outhang * 2)%>

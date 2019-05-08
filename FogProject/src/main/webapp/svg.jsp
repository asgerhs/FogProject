<%-- 
    Document   : svg
    Created on : Apr 30, 2019, 10:41:33 AM
    Author     : Asger Hermind Sørensen & Andreas Vikke & William Huusfeldt
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

    .measure{
        stroke:#000000; 
        marker-start: url(#beginArrow); 
        marker-end: url(#endArrow);
    }
</style>

<%
    int width = 780;
    int height = 600;
    int outhang = 35;
    int frontOuthang = 100;
    int backOuthang = 30;
    int rafterCount = 15;
    int rafterSpace = 55;
    int postCount = 3;
    int shedLength = 210;
    //int distant = (width - 130 - 10) / (postCount - 2 + 1);
    int calcLine = 0;
    int distance = (width - frontOuthang - backOuthang - 10) / (postCount - 2 + 1);

    if (shedLength % rafterSpace == 0) {
        calcLine = shedLength / rafterSpace;
    } else {
        calcLine = shedLength / rafterSpace + 1;
    }
    int calcShedLine = (rafterCount - calcLine) - 1;
%>

<div  class="greyBox">
    <h1>Top-down view</h1>
    <svg width="<%= width + 110%>" height="<%= height + 150%>">

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


        <% for (int i = 0; i < postCount; i++) {%>
        <rect class="post" x="<%=100 + frontOuthang + distance * i + 10%>" y="<%=100 + outhang%>" width="10" height="10"/>
        <% }%>



        <% for (int i = 0; i < postCount; i++) {%>
        <rect class="post" x="<%=100 + frontOuthang + distance * i + 10%>" y="<%=100 + height - outhang - 10%>" width="10" height="10" />
        <% }%>





        <%if (shedLength > 0) {%>

        <!-- Shed left side raft -->
        <rect class="shed" x="<%= 100 + width - shedLength - 30%>" y="<%= 100 + outhang%>" height="<%=height + (-outhang * 2)%>" width="10"/>

        <!-- Shed right side raft -->
        <rect class="shed"  x="<%= 100 + width - 30%>" y="<%= 100 + outhang%>" height="<%=height + (-outhang * 2)%>" width="10"/>

        <!-- Shed top Raft -->
        <rect class="shed" x="<%= 100 + width - shedLength - 30%>" y="<%= 100 + outhang%>" height="10" width="<%= shedLength%>"/>

        <!-- Shed bottom Raft -->
        <rect class="shed" x="<%= 100 + width - shedLength - 30%>" y="<%= 100 + height - outhang - 10%>" height="10" width="<%=shedLength%>"/>

        <!-- Shed top left post -->
        <rect class="post" x="<%=100 + width - shedLength - 30%>" y="<%= 100 + outhang%>" height="10" width="10"/>

        <!-- Shed middle post left -->
        <rect class="post" x="<%=100 + width - shedLength - 30%>" y="<%= 100 + (height / 2) - 10%>" height="10" width="10"/>

        <!-- Shed bottom left post-->
        <rect class="post" x="<%=100 + width - shedLength - 30%>" y="<%= 100 + height - outhang - 10%>" height="10" width="10"/>

        <!-- Shed middle post right  -->
        <rect class="post" x="<%= 100 + width - 30%>" y="<%=100 + (height / 2) - 10%>" height="10 " width="10"/>
        <% }%>




        <!-- top left top bottom right striped line -->
        <line x1="<%= 100 + rafterSpace%>" y1="<%= 100 + outhang + 10%>" x2="<%=(calcShedLine * rafterSpace) + 100 - 55 + 10%>" y2="<%= 100 + height - outhang - 10%>" stroke-dasharray="5,5"/>

        <!-- bottom left to top right striped line -->
        <line x1="<%= 100 + rafterSpace%>" y1="<%= 100 + height - outhang - 10%>" x2="<%=(calcShedLine * rafterSpace) + 100 - 55 + 10%>" y2="<%= 100 + outhang + 10%>" stroke-dasharray="5,5"/>

        <defs>
        <marker id="beginArrow" 
                markerWidth="9" markerHeight="4" 
                refX="2" refY="2" 
                orient="auto">
            <path d="M0,2 L8,4 L8,0 L0,2" style="fill: #000000;" />
        </marker>
        <marker id="endArrow" 
                markerWidth="9" markerHeight="4" 
                refX="7" refY="2" 
                orient="auto">
            <path d="M0,0 L0,4 L8,2 L0,0" style="fill: #000000;" />
        </marker>
        </defs>


        <!-- width length text and line -->
        <line class="measure" x1="100" y1="<%=100 + height + 30%>" x2="<%= 100 + width%>" y2="<%=100 + height + 30%>" />
        <text x="<%=100 + (width / 2)%>" y="<%=100 + height + 50%>" fill="black"><%=width%></text>

        <!-- enitre height length line and text -->
        <line class="measure" x1="<%= 30%>" y1="100" x2="<%= 30%>" y2="<%= 100 + height%>"/>
        <text x="<%= 0%>" y ="<%= 100 + (height / 2)%>" fill="black"><%=height%></text>

        <!-- height length excluding outhang line and text -->
        <line class="measure" x1="70" y1="<%=100 + outhang%>" x2="70" y2="<%= 100 + height - outhang%>" fill="black"/>
        <text x="40" y="<%= 100 + (height / 2)%>" fill="black"><%=height - (outhang * 2)%></text> 

        <!-- loop for all rafter spaces -->
        <% for (int i = 0; i < rafterCount; i++) {%>
        <line x1="<%= 100 + rafterSpace * i%>" y1="50" x2="<%= 100 + rafterSpace * i%>" y2="90" style="stroke:black"/>

        <% }%>

        <% for (int i = 0; i < rafterCount - 1; i++) {%>
        <line class="measure" x1="<%= (100 + rafterSpace * i) + 5%>" y1="50" x2="<%= (100 + rafterSpace * i) + rafterSpace - 5%>" y2="50" style="stroke:black"  />

        <% }%>

        <!-- loop for all text between rafter spaces -->
        <% for (int i = 0; i < rafterCount - 1; i++) {%>
        <text x="<%= (100 + rafterSpace * i) + (rafterSpace / 2 - 10)%>" y="45" fill="black"><%=rafterSpace%></text>
        <% }%>
    </svg>
</div>

<%  int a = ((calcShedLine * rafterSpace) + 100) - 55 + 10;
    int b = rafterCount - calcLine;
%>

<!-- Drawing of carport from 2nd perspective (sideview) -->

<div class="greyBox">
    <h1>Side view</h1>
    <svg width="<%= width + 110%>" height="<%= height / 2 + 150%>">
        <!-- Upper line on roof -->
        <line x1="100" y1="100" x2="<%=100 + width%>" y2="<%=100 + 10%>" style="stroke:rgb(0, 0, 0);stroke-width:1"/>

        <!-- bottom line on roof -->
        <line x1="100" y1="<%=100 + 40%>" x2="<%=100 + width%>" y2="<%=100 + 10 + 40%>" style="stroke:rgb(0, 0, 0); stroke-width:1"/>

        <!-- Left vertical line on roof -->
        <line x1="100" y1="100" x2="<%=100%>" y2="<%=100 + 40%>" style="stroke:rgb(0, 0, 0); stroke-width:1"/>

        <!-- Right vertical line on roof -->
        <line x1="<%=100 + width%>" y1="<%=100 + 10%>" x2="<%=100 + width%>" y2="<%=100 + 10 + 40%>" style="stroke:rgb(0, 0, 0); stroke-width:1"/>


        <!-- Posts -->
        <% for (int i = 0; i < postCount - 1; i++) {%>
        <rect class="post" x="<%=100 + frontOuthang + distance * i%>" y="<%=100 + 40 + (3 * i)%>" width="10" height="<%=250 - (3 * i)%>" style="stroke:rgb(0, 0, 0);stroke-width:1"/>
        <% }%>

        <!-- Last post -->
        <rect class="post" x="<%=100 + width - backOuthang - 10%>" y="<%=100 + 50%>" width="10" height="240" style="stroke:rgb(0, 0, 0);stroke-width:2"/>

        <!-- Shed -->
        <% for (int i = shedLength; i > 0; i -= 5) {%>
        <rect x="<%=100 + width - backOuthang - i%>" y="<%=100 + 40%>" height="250" width="5" />
        <% }%>


        <!-- Measure for frontOuthang -->
        <line class="measure" x1="100" y1="425" x2="<%=100 + frontOuthang%>" y2="425"/>
        <line x1="<%=100 + frontOuthang%>" y1="430" x2="<%=100 + frontOuthang%>" y2="400"/>
        <text x="<%=100 + (frontOuthang / 2)%>" y="<%=440%>" fill="black"><%=frontOuthang%></text>

        <!-- Measure for posts minus last for shed -->
        <%if (shedLength > 0) {%>
        <%for (int i = 0; i < postCount - 2; i++) {%>
        <line class="measure" x1="<%=100 + frontOuthang + distance * i%>" y1="425" x2="<%=(100 + frontOuthang + distance * i) + distance%>" y2="425"/>
        <line x1="<%=100 + frontOuthang + distance * i + distance%>" y1="430" x2="<%=100 + frontOuthang + distance * i + distance%>" y2="400" style="stroke: black"/>
        <text x="<%=100 + frontOuthang + (distance / 2) + distance * i%>" y="440" fill="black"><%=distance%></text>
        <% }%>

        <!-- Measure for shed -->
        <line class="measure" x1="<%=100 + width - backOuthang%>" y1="425" x2="<%=100 + width - backOuthang - shedLength%>" y2="425"/>
        <line x1="<%=100 + width - backOuthang - shedLength%>" y1="430" x2="<%=100 + width - backOuthang - shedLength%>" y2="400" style="stroke:black"/>
        <text x="<%=100 + width - backOuthang - shedLength / 2%>" y="440" fill="black"><%=shedLength%></text>
        <% }%>

        <!-- Measure for posts with last -->
        <%if (shedLength == 0) {%>
        <%for (int i = 0; i < postCount - 1; i++) {%>
        <line class="measure" x1="<%=100 + frontOuthang + distance * i%>" y1="425" x2="<%=(100 + frontOuthang + distance * i) + distance%>" y2="425"/>
        <line x1="<%=100 + frontOuthang + distance * i + distance%>" y1="430" x2="<%=100 + frontOuthang + distance * i + distance%>" y2="400" style="stroke: black"/>
        <text x="<%=100 + frontOuthang + (distance / 2) + distance * i%>" y="440" fill="black"><%=distance%></text>
        <% }%>
        <% }%>

        <!-- Measure for backOuthang (with last post) -->
        <line class="measure" x1="<%=100 + width - backOuthang%>" y1="425" x2="<%=100 + width%>" y2="425" style="stroke: black"/>
        <line x1="<%=100 + width%>" y1="430" x2="<%=100 + width%>" y2="400" style="stroke: black"/>
        <text x="<%=100 + width - backOuthang / 2 - 10%>" y="440" fill="black"><%=backOuthang%></text>

        <!-- Front height measurement -->
        <line class="measure" x1="75" y1="105" x2="75" y2="390"/>
        <text x="40" y="240">230</text>

        <!-- Back height measurement -->
        <line class="measure" x1="<%=100 + width + 25%>" y1="115" x2="<%=100 + width + 25%>" y2="390"/>
        <text x="<%=100 + width + 40%>" y="250">210</text>
    </svg>
</div>
package logic;

/**
 *
 * @author Andreas Vikke
 */
public class GenerateSVG {

    private String topViewSVG;
    private String sideViewSVG;
    private int height, width, shedHeight, shedWidth, offset, outhangFront, outhangBack, outhangSites, postSpace;

    public GenerateSVG(int length, int width, int shedLength, int shedWidth, int outhangFront, int outhangBack, int outhangSites) {
        this.height = width / 10;
        this.width = length / 10;
        this.shedHeight = shedWidth / 10;
        this.shedWidth = shedLength / 10;
        this.offset = 100;
        this.outhangFront = outhangFront / 10;
        this.outhangBack = outhangBack / 10;
        this.outhangSites = outhangSites / 10;
        
        topViewSVG = "<svg height='" + (this.height + offset * 2) + "' width='" + (this.width + offset) + "'>";
        topViewSVG += generateArrows();
        topViewSVG += generateRect(0, 0, this.height, this.width, false);
        
        sideViewSVG = "<svg height='" + (this.height) + "' width='" + (this.width + offset * 2) + "'>";
        sideViewSVG += generateArrows();
    }
    
    public void generateRoof() {
        sideViewSVG += generateLine(0, 0, width, 10, false);
        sideViewSVG += generateLine(0, 40, width, 50, false);
        sideViewSVG += generateLine(0, 0, 0, 40, false);
        sideViewSVG += generateLine(width, 10, width, 50, false);
    }
    
    public void generateShed() {
        for(int i = shedWidth; i >= 0; i -= 5) {
            sideViewSVG += generateRect(width - outhangBack - i, 50, 250, 5, false);
        }
    }


    public void generatePosts(int count, int postWidth) {
        postSpace = (width - outhangFront - outhangBack - postWidth) / (count - 1);
        for (int i = 0; i < count; i++) {
            topViewSVG += generateRect(outhangFront + postSpace * i + postWidth, outhangSites, postWidth, postWidth, true);
            topViewSVG += generateRect(outhangFront + postSpace * i + postWidth, height - outhangSites - postWidth, postWidth, postWidth, true);
            sideViewSVG += generateRect(outhangFront + postSpace * i, 42 + 4 * i, 250, postWidth, false);
        }
    }

    public void generateRafter(int count, int space, int rafterWidth) {
        System.out.println(space);
        for (int i = 0; i < count; i++) {
            topViewSVG += generateRect(space * i, 0, height, rafterWidth, false);
        }
    }

    public void generateRem(int remWidth) {
        // Top Raft
        topViewSVG += generateRect(0, outhangSites, remWidth, width, false);
        // Bottom Raft
        topViewSVG += generateRect(0, height - outhangSites - remWidth, remWidth, width, false);
    }

    public void generateShedPosts(int postWidth) {
        // Top left post
        topViewSVG += generateRect(width - shedWidth - outhangBack, outhangSites, postWidth, postWidth, true);

        // Bottom left post
        topViewSVG += generateRect(width - shedWidth - outhangBack, shedHeight - outhangSites - postWidth, postWidth, postWidth, true);
        // Bottom right post
        topViewSVG += generateRect(width - outhangBack, shedHeight - outhangSites - postWidth, postWidth, postWidth, true);

        // Middle left post
        topViewSVG += generateRect(width - shedWidth - outhangBack, shedHeight / 2 - (shedHeight == height ? outhangSites : 0) - postWidth, postWidth, postWidth, true);
        // Middle right post
        topViewSVG += generateRect(width - outhangBack, shedHeight / 2 - (shedHeight == height ? outhangSites : 0) - postWidth, postWidth, postWidth, true);
    }

    public void generateShedReglar(int postWidth) {
        // Outer Box
        topViewSVG += generateRect(width - shedWidth - outhangBack, outhangSites, shedHeight - outhangSites * 2, shedWidth + postWidth, true);
        // Inner Box
        topViewSVG += generateRect(width - shedWidth - outhangBack + postWidth, outhangSites + postWidth, shedHeight - outhangSites * 2 - postWidth * 2, shedWidth - postWidth, true);
    }

    public void generateBand(int rafterCount, int rafterSpace, int rafterWidth) {
        int calcLine = 0;
        if (shedWidth % rafterSpace == 0) {
            calcLine = shedWidth / rafterSpace;
        } else {
            calcLine = shedWidth / rafterSpace + 1;
        }
        int calcShedLine = (rafterCount - calcLine) - 1;

        topViewSVG += generateLine(rafterSpace, outhangSites + rafterWidth, calcShedLine * rafterSpace - rafterSpace + rafterWidth, height - outhangSites - rafterWidth, true);
        topViewSVG += generateLine(rafterSpace, height - outhangSites - rafterWidth, calcShedLine * rafterSpace - rafterSpace + rafterWidth, outhangSites + rafterWidth, true);
    }

    private String generateArrows() {
        return "<defs>\n"
                + "            <marker id=\"beginArrow\" \n"
                + "                    markerWidth=\"9\" markerHeight=\"4\" \n"
                + "                    refX=\"2\" refY=\"2\" \n"
                + "                    orient=\"auto\">\n"
                + "                <path d=\"M0,2 L8,4 L8,0 L0,2\" style=\"fill: #000000;\" />\n"
                + "            </marker>\n"
                + "            <marker id=\"endArrow\" \n"
                + "                    markerWidth=\"9\" markerHeight=\"4\" \n"
                + "                    refX=\"7\" refY=\"2\" \n"
                + "                    orient=\"auto\">\n"
                + "                <path d=\"M0,0 L0,4 L8,2 L0,0\" style=\"fill: #000000;\" />\n"
                + "            </marker>\n"
                + "        </defs>";
    }

    public void generateMeasurements(int rafterCount, int rafterSpace, int postCount) {
        // Top-View measurements
        // Width Line & Text
        topViewSVG += generateMeasLine(0 + offset, height + outhangSites + offset, width + offset, height + outhangSites + offset, true);
        topViewSVG += generateText(width / 2, height + 60, "" + width, true);

        // Entire height Line & Text
        topViewSVG += generateMeasLine(outhangSites, offset, outhangSites, offset + height, true);
        topViewSVG += generateText(0, offset + (height / 2), "" + height, false);

        // Height excluding outhang Line & Text
        topViewSVG += generateMeasLine(outhangSites * 2, offset + outhangSites, outhangSites * 2, offset + height - outhangSites, true);
        topViewSVG += generateText(outhangSites + 5, offset + (height / 2), "" + (height - outhangSites * 2), false);

        for (int i = 0; i < rafterCount; i++) {
            topViewSVG += generateMeasLine(offset + rafterSpace * i, 50, offset + rafterSpace * i, 90, false);
            if(i < rafterCount - 1) {
                topViewSVG += generateMeasLine(offset + rafterSpace * i + 5, 50, offset + rafterSpace * i + rafterSpace - 5, 50, true);
                topViewSVG += generateText(rafterSpace * i + rafterSpace / 2 - 10, 45 - 100, "" + rafterSpace, true);
            }
        }
        
        // Side-View Measurement
        // Front Outhang Lines & Text
        sideViewSVG += generateMeasLine(offset, 425, offset + outhangFront, 425, true);
        sideViewSVG += generateMeasLine(offset + outhangFront, 430, offset + outhangFront, 400, false);
        sideViewSVG += generateMeasLine(offset, 430, offset, offset + 45, false);
        sideViewSVG += generateText(outhangFront / 2 - 15, 340, "" + outhangFront, true);
        
        // Front height Line & Text
        sideViewSVG += generateMeasLine(75, 105, 75, 390, true);
        sideViewSVG += generateText(40, 240, "230", false);
        
        // Back height Line & Text
        sideViewSVG += generateMeasLine(offset + width + 25, 115, offset + width + 25, 390, true);
        sideViewSVG += generateText(offset + width + 40, 250, "210", false);
        
        // Back Outhang (with last post) Lines & Text
        sideViewSVG += generateMeasLine(offset + width - outhangBack - 10, 425, offset + width, 425, true);
        sideViewSVG += generateMeasLine(offset + width, 430, offset + width, 155, false);
        sideViewSVG += generateText(width + 10, 340, "" + (outhangBack + 10), true);
        
        for (int i = 0; i < postCount - (shedHeight > 0 && shedWidth > 0 ? 2 : 1); i++) {
            sideViewSVG += generateMeasLine(offset + outhangFront +postSpace * i, 425, offset + outhangFront + postSpace * i + postSpace, 425, true);
            sideViewSVG += generateMeasLine(offset + outhangFront + postSpace * i + postSpace, 430, offset + outhangFront + postSpace * i + postSpace, 400, false);
            sideViewSVG += generateText(outhangFront + postSpace / 2 + postSpace * i, 340, "" + postSpace, true);
        }

        if(shedHeight > 0 && shedWidth > 0) {
            sideViewSVG += generateMeasLine(offset + width - outhangBack - 10, 425, offset + width - outhangBack - shedWidth, 425, true);
            sideViewSVG += generateMeasLine(offset + width - outhangBack - shedWidth, 430, offset + width - outhangBack - shedWidth, 405, false);
            sideViewSVG += generateText(width - outhangBack - shedWidth / 2, 340, "" + shedWidth, true);
        
            sideViewSVG += generateMeasLine(offset + width - outhangBack - 10, 430, offset + width - outhangBack - 10, 405, false);
            
            sideViewSVG += generateMeasLine(offset + outhangFront + postSpace, 425, offset + outhangFront + postSpace + postSpace - shedWidth + 10, 425, true);
            sideViewSVG += generateText(outhangFront + postSpace + (postSpace - shedWidth) / 2, 340, "" + (postSpace - shedWidth), true);
        }
    }
    
    public void generateRoofWithAngle(int laths){
        int lathSpace = width / laths * 2;
        for(int i = 0; i < laths * 2; i++){
            topViewSVG +=generateRect(0, i*lathSpace, 10, width, true);
        }
    }

    private String generateRect(int x, int y, int height, int width, boolean bold) {
        return "<rect class='" + (bold ? "svgBold" : "") + "' x='" + (x + offset) + "' y='" + (y + offset) + "' height='" + height + "' width='" + width + "'/>";
    }

    private String generateLine(int x1, int y1, int x2, int y2, boolean dashed) {
        return "<line class='" + (dashed ? "svgDashed' stroke-dasharray='5,5'" : "svgThin'") + " x1='" + (x1 + offset) + "' y1='" + (y1 + offset) + "' x2='" + (x2 + offset) + "' y2='" + (y2 + offset) + "'/>";
    }
    
    private String generateMeasLine(int x1, int y1, int x2, int y2, boolean arrows) {
        return "<line class='" + (arrows ? "measure" : "") + "' x1='" + (x1) + "' y1='" + (y1) + "' x2='" + (x2) + "' y2='" + (y2) + "'/>";
    }

    private String generateText(int x, int y, String text, boolean os) {
        return "<text x='" + (x + (os ? offset : 0)) + "' y='" + (y + (os ? offset : 0)) + "' fill='black'>" + text + "</text>";
    }

    public String getTopViewSVG() {
        return topViewSVG;
    }

    public String getSideViewSVG() {
        return sideViewSVG;
    }
}
package logic;

import data.ExceptionLogger;
import data.exceptions.MaterialException;
import data.models.LoggerEnum;
import data.models.Material;
import data.models.Part;
import data.models.PartList;
import java.util.ArrayList;
import java.util.Comparator;
import logic.facades.MaterialFacade;

/**
 *
 * @author William Sehested Huusfeldt, Martin Frederiksen, Andreas Vikke
 */
public class AdvancedCalculator {

    private int length, width, posts, rafters, rafterSpace, shedLength, shedWidth, shedPostsWidth, shedPostsLength, shedReglar, shedCladdings, laths;
    private boolean shed;
    private boolean roof;
    private PartList pl;
    private GenerateSVG svg;
    private ArrayList<Material> materials;
    private MaterialFacade mf;
    private double angle;

    public AdvancedCalculator(int length, int width, boolean shed, int shedLength, int shedWidth, boolean roof, double angle) {
        this.length = length;
        this.width = width;
        this.shed = shed;
        this.shedLength = shedLength;
        this.shedWidth = shedWidth;
        this.roof = roof;
        this.angle = angle;
        pl = new PartList();
        svg = new GenerateSVG(length, width, shedLength, shedWidth, 1000, 200, 350);
        mf = new MaterialFacade();

        try {

            calcBottomFasciasFB();
            calcBottomFasciasSide();
            calcTopFasciasFront();
            calcTopFasciasSide();
            calcPosts();
            calcRem();
            calcRafters();
            calcRem();
            calcWaterBoardFront();
            calcWaterBoardSide();
            calcRoofingSheets();

            calcRoofScrews();
            calcBand();
            calcBracketsRight();
            calcBracketsLeft();
            calcFasciasScrews();

            calcBoltsAndSquares();

            if (roof) {
                calcLathsRoof();
                calcRoofBricks();
            }
            if (shed) {
                calcShedReglarWidth();
                calcShedCladding();
                calcShedMisc();
                svg.generateShed();
            }

            calcScrewPackages();
            svg.generateMeasurements(rafters, rafterSpace, posts / 2);
            if(!roof){
            svg.generateRoof();
            }
        } catch (MaterialException ex) {
            ExceptionLogger.log(LoggerEnum.ADVANCECALCULATOR, "Error in AdvanceCalculator Method: \n" + ex.getMessage(), ex.getStackTrace());
        }
    }

    //Calculating the square of carport
    private void calcPosts() throws MaterialException {
        int p = 4;
        p += (length % 5000 == 0) ? ((length / 5000) - 1) * 2 : ((length / 5000) - 1 + 1) * 2;
        posts = p;

        svg.generatePosts(p / 2, 10);

        if (shed) {
            p += 2;
            p += calcShedPosts(true);
            p += calcShedPosts(false);
            if (shedWidth != width) {
                p++;
            }
            svg.generateShedPosts(10);
        }

        materials = mf.getAllByCategory(7);
        pl.addWoodPart(new Part(materials.get(0), p, "Stolper nedgraves 90 cm. i jord", materials.get(0).getPrice() * p));
    }

    private int calcShedPosts(boolean isWidth) throws MaterialException {
        int widthOuthang = 350 * 2;
        int lengthOuthang = 0; //1000 + 300;
        materials = mf.getAllByCategory(4);
        materials.sort(new MatSortLowComparator());

        boolean done = false;
        int esp = 0;

        while (!done) {
            boolean addPost = false;
            for (Material m : materials) {
                double sValue = (isWidth ? shedWidth - widthOuthang : shedLength - lengthOuthang) - 200;
                double sCheck = sValue / m.getLength();
                if (sCheck > esp + 1) {
                    addPost = true;
                } else {
                    addPost = false;
                }
            }
            if (addPost) {
                esp++;
            } else {
                done = true;
            }
        }
        if (isWidth) {
            shedPostsWidth = esp;
        } else {
            shedPostsLength = esp;
        }

        return esp * 2;
    }

    private void calcShedReglarWidth() throws MaterialException {
        int outhang = 350;

        int countWidth = (shedPostsWidth + 1) * 3 * 2;
        int countLength = (shedPostsLength + 1) * 2 * 2;
        shedReglar += countWidth;
        shedReglar += countLength;

        materials = mf.getAllByCategory(4);
        materials.sort(new MatSortLowComparator());

        for (Material m : materials) {
            double sWidth = shedWidth - outhang * 2 - 200;
            double sCheck = sWidth / m.getLength();
            if (sCheck < shedPostsWidth + 1) {
                pl.addWoodPart(new Part(m, countWidth, "Løsholter til skur gavle", m.getPrice() * countWidth));
                break;
            }
        }
        for (Material m : materials) {
            double sLegnth = shedLength - 200;
            double sCheck = sLegnth / m.getLength();
            if (sCheck < shedPostsLength + 1) {
                pl.addWoodPart(new Part(m, countLength, "Løsholter til skur siderne", m.getPrice() * countLength));
                break;
            }
        }

        svg.generateShedReglar(10);
    }

    private void calcShedCladding() throws MaterialException {
        int widthOuthang = 350 * 2;
        materials = mf.getAllByCategory(8);
        materials.sort(new MatSortLowComparator());

        int shedCircumference = (shedWidth - widthOuthang) * 2 + shedLength * 2;
        shedCladdings = shedCircumference / (100 - 40) + 1;
        pl.addWoodPart(new Part(materials.get(0), shedCladdings, "Til beklædning af skur. 1 på 2", materials.get(0).getPrice() * shedCladdings));
    }

    private void calcShedMisc() throws MaterialException {
        materials = mf.getAllByCategory(16);
        materials.sort(new MatSortLowComparator());
        pl.addMiscPart(new Part(materials.get(0), 1, "Til lås på dør i skur", materials.get(0).getPrice() * 1));
        pl.addMiscPart(new Part(materials.get(1), 2, "Til skurdør", materials.get(1).getPrice() * 2));
        pl.addWoodPart(new Part(materials.get(2), 1, "Til z på bagside af dør", materials.get(2).getPrice() * 1));

        materials = mf.getAllByCategory(13);
        materials.sort(new MatSortLowComparator());
        pl.addMiscPart(new Part(materials.get(0), shedReglar * 2, "Til montering af løsholter i skur", materials.get(0).getPrice() * (shedReglar * 2)));
    }

    private void calcRem() throws MaterialException {
        int frontOuthang = 1000;
        int backOuthang = 300;

        // Length without outhang
        int calcLength = length - frontOuthang - backOuthang - 100;
        // Extra posts other then the 4 corners        
        int extraPosts = ((posts - 4) / 2) + 1;
        // Distance between posts from start to mid.
        int a = calcLength / extraPosts + 50;

        addParts(a, 6, extraPosts * 2, "Remme i sider, sadles ned i stolper", new MatSortLowComparator());

        svg.generateRem(10);
    }

    //Calculating the roof of carport
    private void calcRafters() throws MaterialException {
        rafters = length / 500;
        rafterSpace = (length / 10) / (rafters - 1);
        addParts(width, 5, length / 500, "Spær, monteres på rem", new MatSortHeighComparator());
        svg.generateRafter(rafters, rafterSpace, 10);
    }

    //need a good way to present this
    private void calcTopFasciasFront() throws MaterialException {
        addParts(width, 2, 1, "oversternbrædder til forenden", new MatSortHeighComparator());
    }

    private void calcTopFasciasSide() throws MaterialException {
        addParts(length, 2, 2, "oversternbrædder til siderne", new MatSortHeighComparator());
    }

    private void calcBottomFasciasFB() throws MaterialException {
        addParts(width, 1, 2, "understernbrædder til for & bag ende", new MatSortHeighComparator());
    }

    private void calcBottomFasciasSide() throws MaterialException {
        addParts(length, 1, 2, "understernbrædder til siderne", new MatSortHeighComparator());
    }

    private void calcWaterBoardFront() throws MaterialException {
        addParts(width, 9, 1, "vandbrædt på stern i forende", new MatSortHeighComparator());
    }

    private void calcWaterBoardSide() throws MaterialException {
        addParts(length, 9, 2, "vandbrædt på stern i sider", new MatSortHeighComparator());
    }

    private void calcRoofingSheets() throws MaterialException {
        int multiplier = (width % 1000 == 0) ? width / 1000 : (width / 1000) + 1;
        addParts(length, 10, multiplier, "tagplader monteres på spær", new MatSortHeighComparator());
    }

    //Calculating the pieces of carport
    private void calcBracketsRight() throws MaterialException {
        materials = mf.getAllByCategory(13);
        materials.sort(new MatSortHeighComparator());
        for (Material m : materials) {
            if (m.getRef().charAt(m.getRef().length() - 1) == 'r') {
                pl.addMiscPart(new Part(m, rafters, "Til montering af spær på rem", m.getPrice() * rafters));
            }
        }
    }

    private void calcBracketsLeft() throws MaterialException {
        materials = mf.getAllByCategory(13);
        materials.sort(new MatSortHeighComparator());
        for (Material m : materials) {
            if (m.getRef().charAt(m.getRef().length() - 1) == 'l') {
                pl.addMiscPart(new Part(m, rafters, "Til montering af spær på rem", m.getPrice() * rafters));
            }
        }
    }

    private void calcScrewPackages() throws MaterialException {
        //needs a fix
        materials = mf.getAllByCategory(11);
        int screws = 0;
        int packages;
        //screws to brackets
        screws += rafters * 2 * 9;
        //screws to band
        screws += (rafters - 2) * 2 * 2;
        //extra screws
        screws *= 1.1;
        packages = (screws % 250 == 0) ? screws / 250 : (screws / 250) + 1;
        pl.addMiscPart(new Part(materials.get(2), packages, "Til montering af universalbeslag + hulbånd", materials.get(2).getPrice() * packages));

        if (shed) {
            int shedScrewCount = shedCladdings * 3 * 3;
            shedScrewCount *= 1.1;
            pl.addMiscPart(new Part(materials.get(3), shedScrewCount / materials.get(3).getAmount(), "Til montering af yderste beklædning", materials.get(3).getPrice() * (shedScrewCount / materials.get(3).getAmount())));
            pl.addMiscPart(new Part(materials.get(4), shedScrewCount / materials.get(4).getAmount(), "Til montering af inderste beklædning", materials.get(4).getPrice() * (shedScrewCount / materials.get(4).getAmount())));
        }
    }

    //10% more?
    private void calcRoofScrews() throws MaterialException {
        materials = mf.getAllByCategory(11);
        int screws = (width * length * 12) / 1000000;
        int packages = (screws % 200 == 0) ? screws / 200 : (screws / 200) + 1;
        pl.addMiscPart(new Part(materials.get(0), packages, "Skruer til tagplader", materials.get(0).getPrice() * packages));
    }

    //10% more ?
    private void calcBand() throws MaterialException {
        materials = mf.getAllByCategory(12);
        double spaceBetweenRafters = (length - 4.5) / (rafters - 1);
        double fullSpace = length - (spaceBetweenRafters * 2) - 4.5;
        double bandLength = Math.sqrt(Math.pow(fullSpace, 2) + Math.pow(width, 2)) * 2;
        int bandCount = (bandLength % 10000.0 == 0) ? (int) (bandLength / 10000.0) : (int) (bandLength / 10000.0 + 1.0);
        pl.addMiscPart(new Part(materials.get(0), bandCount, "Til vindkryds på spær", materials.get(0).getPrice() * bandCount));
        svg.generateBand(rafters, rafterSpace, 10);
    }

    private void calcFasciasScrews() throws MaterialException {
        materials = mf.getAllByCategory(11);
        int screws = 0;
        //bottomFascias with 2 screws for each meter
        screws += (((length * 2) / 1000) + ((width * 2) / 1000)) * 2;
        //topFascias with 2 screws for each meter
        screws += (((length * 2) / 1000) + (width / 1000)) * 2;
        //waterBoards with 2 screws for each meter
        screws += (((length * 2) / 1000) + (width / 1000)) * 2;
        //10%
        screws *= 1.1;
        int packages = (screws % 200 == 0) ? screws / 200 : (screws / 200) + 1;
        pl.addMiscPart(new Part(materials.get(1), packages, "Til montering af stern&vandbrædt", materials.get(1).getPrice() * packages));
    }

    private void calcBoltsAndSquares() throws MaterialException {
        //with arbitrary values we should see if rem is in more than one piece before calculating this
        ArrayList<Material> boltType = mf.getAllByCategory(14);
        ArrayList<Material> squares = mf.getAllByCategory(15);
        int bolts = (posts == 4) ? 4 * 2 : (posts - 4) * 4 + (4 * 2);
        pl.addMiscPart(new Part(boltType.get(0), bolts, "Til montering af rem på stolper", boltType.get(0).getPrice() * bolts));
        pl.addMiscPart(new Part(squares.get(0), bolts, "Til montering af rem på stolper", squares.get(0).getPrice() * bolts));
    }

    private void calcLathsRoof() throws MaterialException {
        ArrayList<Material> lathType = mf.getAllByCategory(3);
        ArrayList<Material> lathHolder = mf.getAllByCategory(17);
        double angleRad = Math.toRadians(angle);
        double triangle = Math.toRadians(180);
        double roofWidth = (width * Math.sin(angle)) / Math.sin(triangle - (angle * 2));
        laths = (int) (roofWidth - 380) % 307 == 0 ? (int) ((roofWidth - 380) / 307) + 2 : (int) ((roofWidth - 380) / 307) + 3;
        pl.addWoodPart(new Part(lathType.get(1), (int) laths * 2, "Til montering på tag", lathType.get(1).getPrice() * ((int)laths * 2)));
        pl.addMiscPart(new Part(lathHolder.get(2), length/1000 + 1, "monteres på toppen af spæret (til toplægte)", lathHolder.get(2).getPrice() * (length/1000 + 1)));
        svg.generateRoofWithAngle(laths, (int)roofWidth);
    }

    private void calcRoofBricks() throws MaterialException {
        ArrayList<Material> bricks = mf.getAllByCategory(17);
        int sideBricks = length / 300;
        int topBricks = length / 333;
        int brickPack = ((sideBricks * (laths - 1) * 2) % 300 == 0) ? (sideBricks * (laths - 1) * 2) / 300 : ((sideBricks * (laths - 1) * 2) / 300) + 1;
        pl.addMiscPart(new Part(bricks.get(0), brickPack, "Monteres på taglægter af " + (laths - 1) + " rækker af " + sideBricks + " sten på hver side af taget", bricks.get(0).getPrice() * brickPack));
        pl.addMiscPart(new Part(bricks.get(1), topBricks, "monteres på toplægte med medfølgende beslag se tagstens vejledning", bricks.get(1).getPrice() * topBricks));
        pl.addMiscPart(new Part(bricks.get(3), topBricks, "Til montering af rygsten", bricks.get(3).getPrice() * topBricks));
        pl.addMiscPart(new Part(bricks.get(4), 2, "til montering af tagsten, alle ydersten + hver anden fastgøres", bricks.get(4).getPrice() * 2));
    }

    public void addParts(int lengthWidth, int categoryId, int multiplier, String description, Comparator<Material> comparator) throws MaterialException {
        materials = mf.getAllByCategory(categoryId);
        materials.sort(comparator);
        int rest = lengthWidth;
        int antal = 0;
        for (Material m : materials) {

            if (rest >= m.getLength()) {
                antal += rest / m.getLength();
                rest -= m.getLength();
                pl.addWoodPart(new Part(m, antal * multiplier, description, m.getPrice() * (antal * multiplier)));
            } else if (rest != 0) {
                pl.addWoodPart(new Part(m, 1 * multiplier, description, m.getPrice() * multiplier));
                return;
            }
        }
    }

    public PartList getParts() {
        return pl;
    }

    public GenerateSVG getTopViewSVG() {
        return svg;
    }
    
    public int getPosts() {
        return posts;
    }

    public int getRafters() {
        return rafters;
    }


    private class MatSortHeighComparator implements Comparator<Material> {

        @Override
        public int compare(Material o1, Material o2) {
            return o2.getLength() - o1.getLength();
        }
    }

    private class MatSortLowComparator implements Comparator<Material> {

        @Override
        public int compare(Material o1, Material o2) {
            return o1.getLength() - o2.getLength();
        }
    }

}

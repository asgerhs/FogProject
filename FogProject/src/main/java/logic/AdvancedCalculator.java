package logic;

import data.exceptions.MapperExceptions;
import data.models.Material;
import data.models.Part;
import data.models.PartList;
import java.util.ArrayList;
import java.util.Comparator;
import logic.facades.MaterialFacade;

/**
 *
 * @author William Sehested Huusfeldt & Martin Frederiksen & Andreas Vikke
 */
public class AdvancedCalculator {

    private int length;
    private int width;
    private int posts;
    private int rafters;
    private boolean shed;
    private int shedLength;
    private int shedWidth;
    private int shedPostsWidth;
    private int shedPostsLength;
    private int shedReglar;
    private int shedCladdings;
    private boolean roof;
    private PartList pl;
    private ArrayList<Material> materials;
    private MaterialFacade mf;

    public AdvancedCalculator(int length, int width, boolean shed, int shedLength, int shedWidth, boolean roof) {
        this.length = length;
        this.width = width;
        this.shed = shed;
        this.shedLength = shedLength;
        this.shedWidth = shedWidth;
        this.roof = roof;
        pl = new PartList();
        mf = new MaterialFacade();
        //catch exception here?

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

            }
            if (shed) {
                calcShedReglarWidth();
                calcShedCladding();
                calcShedMisc();
            }
            
            calcScrewPackages();
        } catch (MapperExceptions ex) {
            // TODO: Exception
        }
    }

    //Calculating the square of carport
    private void calcPosts() throws MapperExceptions {
        int p = 4;
        p += (length % 5000 == 0) ? ((length / 500) - 1) * 2 : ((length / 5000) - 1 + 1) * 2;
        posts = p;
        
        if(shed) {
            p += 2;
            p += calcShedPosts(true);
            p += calcShedPosts(false);
        }
        
        materials = mf.getAllByCategory(7);
        pl.addWoodPart(new Part(materials.get(0), p, "Stolper nedgraves 90 cm. i jord"));
    }
    
    private int calcShedPosts(boolean isWidth) throws MapperExceptions {
        int widthOuthang = 350 * 2;
        int lengthOuthang = 0; //1000 + 300;
        materials = mf.getAllByCategory(4);
        materials.sort(new MatSortLowComparator());

        boolean done = false;
        int esp = 0;
        
        while(!done) {
            boolean addPost = false;
            for(Material m : materials) {
                double sValue = (isWidth ? shedWidth - widthOuthang : shedLength - lengthOuthang) - 200;
                double sCheck = sValue / m.getLength();
                if(sCheck > esp + 1)
                    addPost = true;
                else
                    addPost = false;
            }
            if(addPost)
                esp++;
            else
                done = true;
        }
        if(isWidth)
            shedPostsWidth = esp;
        else
            shedPostsLength = esp;
        
        return esp * 2;
    }
    
     private void calcShedReglarWidth() throws MapperExceptions {
        int outhang = 350;
        
        int countWidth = (shedPostsWidth + 1) * 3 * 2;
        int countLength = (shedPostsLength + 1) * 2 * 2;
            shedReglar += countWidth;
            shedReglar += countLength;
        
        materials = mf.getAllByCategory(4);
        materials.sort(new MatSortLowComparator());

        for(Material m : materials) {
            double sWidth = shedWidth - outhang * 2 - 200;
            double sCheck = sWidth / m.getLength();
            if(sCheck < shedPostsWidth + 1) {
                pl.addWoodPart(new Part(m, countWidth, "Løsholter til skur gavle"));
                break;
            }
        }
        for(Material m : materials) {
            double sLegnth = shedLength - 200;
            double sCheck = sLegnth / m.getLength();
            if(sCheck < shedPostsLength + 1) {
                pl.addWoodPart(new Part(m, countLength, "Løsholter til skur siderne"));
                break;
            }
        }
    }
     
    private void calcShedCladding() throws MapperExceptions {
        int widthOuthang = 350 * 2;
        materials = mf.getAllByCategory(8);
        materials.sort(new MatSortLowComparator());
        
        int shedCircumference = (shedWidth - widthOuthang) * 2 + shedLength * 2;
        shedCladdings = shedCircumference / (100 - 40) + 1;
        pl.addWoodPart(new Part(materials.get(0), shedCladdings, "Til beklædning af skur. 1 på 2"));
    }
    
     private void calcShedMisc() throws MapperExceptions {
        materials = mf.getAllByCategory(16);
        materials.sort(new MatSortLowComparator());
        pl.addMiscPart(new Part(materials.get(0), 1, "Til lås på dør i skur"));
        pl.addMiscPart(new Part(materials.get(1), 2, "Til skurdør"));
        pl.addWoodPart(new Part(materials.get(2), 1, "Til z på bagside af dør"));
        
        materials = mf.getAllByCategory(13);
        materials.sort(new MatSortLowComparator());
        pl.addMiscPart(new Part(materials.get(0), shedReglar * 2, "Til montering af løsholter i skur"));
    }

    private void calcRem() throws MapperExceptions {
        int frontOuthang = 1000;
        int backOuthang = 300;
        
        // Length without outhang
        int calcLength= length - frontOuthang - backOuthang - 100;
        // Extra posts other then the 4 corners        
        int extraPosts = ((posts - 4)/2) + 1;
        // Distance between posts from start to mid.
        int a = calcLength / extraPosts + 50;
        
        addParts(a, 6, extraPosts * 2, "Remme i sider, sadles ned i stolper", new MatSortLowComparator());
    }

    //Calculating the roof of carport
    private void calcRafters() throws MapperExceptions {
        rafters = length / 500;
        addParts(width, 5, length / 500, "Spær, monteres på rem", new MatSortHeighComparator());
    }

    //need a good way to present this
    private void calcTopFasciasFront() throws MapperExceptions {
        addParts(width, 2, 1, "oversternbrædder til forenden", new MatSortHeighComparator());
    }

    private void calcTopFasciasSide() throws MapperExceptions {
        addParts(length, 2, 2, "oversternbrædder til siderne", new MatSortHeighComparator());
    }

    private void calcBottomFasciasFB() throws MapperExceptions {
        addParts(width, 1, 2, "understernbrædder til for & bag ende", new MatSortHeighComparator());
    }

    private void calcBottomFasciasSide() throws MapperExceptions {
        addParts(length, 1, 2, "understernbrædder til siderne", new MatSortHeighComparator());
    }

    private void calcWaterBoardFront() throws MapperExceptions {
        addParts(width, 9, 1, "vandbrædt på stern i forende", new MatSortHeighComparator());
    }

    private void calcWaterBoardSide() throws MapperExceptions {
        addParts(length, 9, 2, "vandbrædt på stern i sider", new MatSortHeighComparator());
    }

    private void calcRoofingSheets() throws MapperExceptions {
        int multiplier = (width % 1000 == 0) ? width / 1000 : (width / 1000) + 1;
        addParts(length, 10, multiplier, "tagplader monteres på spær", new MatSortHeighComparator());
    }

    //Calculating the pieces of carport
    private void calcBracketsRight() throws MapperExceptions {
        materials = mf.getAllByCategory(13);
        materials.sort(new MatSortHeighComparator());
        for (Material m : materials) {
            if (m.getRef().charAt(m.getRef().length() - 1) == 'r') {
                pl.addMiscPart(new Part(m, rafters, "Til montering af spær på rem"));
            }
        }
    }

    private void calcBracketsLeft() throws MapperExceptions {
        materials = mf.getAllByCategory(13);
        materials.sort(new MatSortHeighComparator());
        for (Material m : materials) {
            if (m.getRef().charAt(m.getRef().length() - 1) == 'l') {
                pl.addMiscPart(new Part(m, rafters, "Til montering af spær på rem"));
            }
        }
    }

    private void calcScrewPackages() throws MapperExceptions {
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
        pl.addMiscPart(new Part(materials.get(2), packages, "Til montering af universalbeslag + hulbånd"));
        
        if(shed) {
            int shedScrewCount = shedCladdings * 3 * 3;
            shedScrewCount *= 1.1;
            pl.addMiscPart(new Part(materials.get(3), shedScrewCount / materials.get(3).getAmount(), "Til montering af yderste beklædning"));
            pl.addMiscPart(new Part(materials.get(4), shedScrewCount / materials.get(4).getAmount(), "Til montering af inderste beklædning"));
        }
    }

    //10% more?
    private void calcRoofScrews() throws MapperExceptions {
        materials = mf.getAllByCategory(11);   
        int screws = (width * length * 12) / 1000000;
        int packages = (screws % 200 == 0) ? screws / 200 : (screws / 200) + 1;
        pl.addMiscPart(new Part(materials.get(0), packages, "Skruer til tagplader"));
    }
    
    //10% more ?
    private void calcBand() throws MapperExceptions {
        materials = mf.getAllByCategory(12);
        double spaceBetweenRafters = (length - 4.5) / (rafters - 1);
        double fullSpace = length - (spaceBetweenRafters * 2) - 4.5;
        double bandLength = Math.sqrt(Math.pow(fullSpace, 2) + Math.pow(width, 2)) * 2;
        int bandCount = (bandLength % 10000.0 == 0) ? (int) (bandLength / 10000.0) : (int) (bandLength / 10000.0 + 1.0);
        pl.addMiscPart(new Part(materials.get(0), bandCount, "Til vindkryds på spær"));
    }
    
    private void calcFasciasScrews() throws MapperExceptions {
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
        pl.addMiscPart(new Part(materials.get(1), packages, "Til montering af stern&vandbrædt"));
    }
    
    private void calcBoltsAndSquares() throws MapperExceptions {
        //with arbitrary values we should see if rem is in more than one piece before calculating this
        ArrayList<Material> boltType = mf.getAllByCategory(14);
        ArrayList<Material> squares = mf.getAllByCategory(15);
        int bolts = (posts == 4) ? 4 * 2 : (posts - 4) * 4 + ( 4 * 2);
        pl.addMiscPart(new Part(boltType.get(0), bolts, "Til montering af rem på stolper"));
        pl.addMiscPart(new Part(squares.get(0), bolts, "Til montering af rem på stolper"));
    }

    public void addParts(int lengthWidth, int categoryId, int multiplier, String description, Comparator<Material> comparator) throws MapperExceptions {
        materials = mf.getAllByCategory(categoryId);
        materials.sort(comparator);
        int rest = lengthWidth;
        int antal = 0;
        for (Material m : materials) {

            if (rest >= m.getLength()) {
                antal += rest / m.getLength();
                rest -= m.getLength();
                pl.addWoodPart(new Part(m, antal * multiplier, description));
            } else if (rest != 0) {
                pl.addWoodPart(new Part(m, 1 * multiplier, description));
                return;
            }
        }
    }

    public PartList getParts() {
        return pl;
    }

    public static void main(String[] args) throws MapperExceptions {
        AdvancedCalculator ac = new AdvancedCalculator(7800, 6000, true, 2100, 6000, false);
        ac.calcFasciasScrews();
        for (Part p : ac.getParts().getWoodList()) {
            System.out.println(p);
        }
        for (Part p : ac.getParts().getMiscList()) {
            System.out.println(p);
        }
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

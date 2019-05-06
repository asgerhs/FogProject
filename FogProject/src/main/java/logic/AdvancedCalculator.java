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
 * @author William Sehested Huusfeldt
 */
public class AdvancedCalculator {

    private int length;
    private int width;
    private int posts;
    private int rafters;
    private boolean sheet;
    private boolean roof;
    private PartList pl;
    private ArrayList<Material> materials;
    private MaterialFacade mf;

    public AdvancedCalculator(int length, int width, boolean sheet, boolean roof) {
        this.length = length;
        this.width = width;
        this.sheet = sheet;
        this.roof = roof;
        pl = new PartList();
        mf = new MaterialFacade();
        //catch exception here?

        try {
            calcBottomFasciasFB();
            calcBottomFasciasSide();
            calcTopFasciasFront();
            calcTopFasciasSide();
            calcRem();
            calcRafters();
            calcPosts(sheet);
            calcWaterBoardFront();
            calcWaterBoardSide();
            calcRoofingSheets();
            
            calcRoofScrews();
            calcBand(sheet);
            calcBracketsRight();
            calcBracketsLeft();

            calcScrewPackages(sheet);
            calcBoltsAndSquares();
        } catch (MapperExceptions ex) {
            // TODO: Exception
        }

        if (roof) {

        }
        if (sheet) {

        }
    }

    //Calculating the square of carport
    private void calcPosts(boolean sheet) throws MapperExceptions {
        int p = 4;
        p += (length % 5000 == 0) ? ((length / 500) - 1) * 2 : ((length / 5000) - 1 + 1) * 2;
        posts = p;
        materials = mf.getAllByCategory(7);
        pl.addPart(new Part(materials.get(0), p, "Stolper nedgraves 90 cm. i jord"));

    }

    //look at space between posts
    private void calcRem() throws MapperExceptions {
        /*// length > 600 use if statement when we use arbitrary value

        //If length is between 480 and 600 use 1 on each side
        if (length > 480 && length < 600) {
            return 2;
        }
        if (length < 480) {
            return 2;
        } else {
            return (length % 480 == 0) ? (length / 480) * 2 : ((length / 480) + 1) * 2;
        }*/
        addParts(length, 6, 2, "Remme i sider, sadles ned i stolper");
    }

    //Calculating the roof of carport
    private void calcRafters() throws MapperExceptions {
        rafters = length / 500;
        addParts(width, 5, length / 500, "Spær, monteres på rem");
    }

    //need a good way to present this
    private void calcTopFasciasFront() throws MapperExceptions {
        addParts(width, 2, 1, "oversternbrædder til forenden");
    }

    private void calcTopFasciasSide() throws MapperExceptions {
        addParts(length, 2, 2, "oversternbrædder til siderne");
    }

    private void calcBottomFasciasFB() throws MapperExceptions {
        addParts(width, 1, 2, "understernbrædder til for & bag ende");
    }

    private void calcBottomFasciasSide() throws MapperExceptions {
        addParts(length, 1, 2, "understernbrædder til siderne");
    }

    private void calcWaterBoardFront() throws MapperExceptions {
        addParts(width, 9, 1, "vandbrædt på stern i forende");
    }

    private void calcWaterBoardSide() throws MapperExceptions {
        addParts(length, 9, 2, "vandbrædt på stern i sider");
    }

    private void calcRoofingSheets() throws MapperExceptions {
        int multiplier = (width % 1000 == 0) ? width / 1000 : (width / 1000) + 1;
        addParts(length, 10, multiplier, "tagplader monteres på spær");
    }

    //Calculating the pieces of carport
    private void calcBracketsRight() throws MapperExceptions {
        materials = mf.getAllByCategory(13);
        materials.sort(new MatSortComparator());
        for (Material m : materials) {
            if (m.getRef().charAt(m.getRef().length() - 1) == 'r') {
                pl.addPart(new Part(m, rafters, "Til montering af spær på rem"));
            }
        }
    }

    private void calcBracketsLeft() throws MapperExceptions {
        materials = mf.getAllByCategory(13);
        materials.sort(new MatSortComparator());
        for (Material m : materials) {
            if (m.getRef().charAt(m.getRef().length() - 1) == 'l') {
                pl.addPart(new Part(m, rafters, "Til montering af spær på rem"));
            }
        }
    }

    private void calcScrewPackages(boolean sheet) throws MapperExceptions {
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
        pl.addPart(new Part(materials.get(2), packages, "Til montering af universalbeslag + hulbånd"));
    }

    //10% more?
    private void calcRoofScrews() throws MapperExceptions {
        materials = mf.getAllByCategory(11);   
        int screws = (width * length * 12) / 1000000;
        int packages = (screws % 200 == 0) ? screws / 200 : (screws / 200) + 1;
        pl.addPart(new Part(materials.get(0), packages, "Skruer til tagplader"));
    }
    
    //10% more ?
    private void calcBand(boolean sheet) throws MapperExceptions {
        materials = mf.getAllByCategory(12);
        double spaceBetweenRafters = (length - 4.5) / (rafters - 1);
        double fullSpace = length - (spaceBetweenRafters * 2) - 4.5;
        double bandLength = Math.sqrt(Math.pow(fullSpace, 2) + Math.pow(width, 2)) * 2;
        int bandCount = (bandLength % 10000.0 == 0) ? (int) (bandLength / 10000.0) : (int) (bandLength / 10000.0 + 1.0);
        pl.addPart(new Part(materials.get(0), bandCount, "Til vindkryds på spær"));
    }
    
    private void calcFasciasScrews() throws MapperExceptions {
        materials = mf.getAllByCategory(11);
        int screws = 0;
        //bottomFascias
        screws += ((length * 2) + (width * 2)) * 2;
        //topFascias
        screws += ((length * 2) + width) * 2;
        System.out.println(screws);
        int packages = 0;
    }
    
    private void calcBoltsAndSquares() throws MapperExceptions {
        //with arbitrary values we should see if rem is in more than one piece before calculating this
        ArrayList<Material> boltType = mf.getAllByCategory(14);
        ArrayList<Material> squares = mf.getAllByCategory(15);
        int bolts = (posts - 1 == 4) ? 4 * 2 : ((posts - 1) % 4) * 4 + (4 * 2);
        pl.addPart(new Part(boltType.get(0), bolts, "Til montering af rem på stolper"));
        pl.addPart(new Part(squares.get(0), bolts, "Til montering af rem på stolper"));
    }

    public void addParts(int lengthWidth, int categoryId, int multiplier, String description) throws MapperExceptions {
        materials = mf.getAllByCategory(categoryId);
        materials.sort(new MatSortComparator());
        int rest = lengthWidth;
        int antal = 0;
        for (Material m : materials) {

            if (rest >= m.getLength()) {
                antal += rest / m.getLength();
                rest -= m.getLength();
                pl.addPart(new Part(m, antal * multiplier, description));
            } else if (rest != 0) {
                pl.addPart(new Part(m, 1 * multiplier, description));
                return;
            }
        }
    }

    public PartList getParts() {
        return pl;
    }

    public static void main(String[] args) throws MapperExceptions {
        AdvancedCalculator ac = new AdvancedCalculator(7800, 6000, false, false);
        ac.calcFasciasScrews();
        /*for (Part p : ac.getParts().getPartList()) {
            System.out.println(p);
        }*/
    }

    private class MatSortComparator implements Comparator<Material> {

        @Override
        public int compare(Material o1, Material o2) {
            return o2.getLength() - o1.getLength();
        }
    }
}

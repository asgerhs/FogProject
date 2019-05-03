package logic;

import data.exceptions.MapperExceptions;
import data.models.Material;
import data.models.Part;
import data.models.PartList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import logic.facades.MaterialFacade;

/**
 *
 * @author William Sehested Huusfeldt
 */
public class AdvancedCalculator {

    private int length;
    private int width;
    private int rafters;
    private boolean sheet;
    private boolean roof;
    private PartList pl;
    private Map<Integer, Material> materials;
    private MaterialFacade mf;

    public AdvancedCalculator(int length, int width, boolean sheet, boolean roof) throws MapperExceptions {
        this.length = length;
        this.width = width;
        this.sheet = sheet;
        this.roof = roof;
        pl = new PartList();
        mf = new MaterialFacade();
        //catch exception here?
        
        calcPosts();
        calcTopFasciasFront();
        calcTopFasciasSide();
        calcBottomFasciasFB();
        calcBottomFasciasSide();
        calcRem();
        calcRafters();
        calcWaterBoardFront();
        calcWaterBoardSide();
        calcRoofingSheets();
        //calcBracketsRight();
        calcBracketsLeft();

        
        if (roof) {

        }
        if (sheet) {

        }
    }

    //Calculating the square of carport
    private void calcPosts() throws MapperExceptions {
        /*int p = 5;
        p += (length % 500 == 0) ? ((length / 500) - 1) * 2 : ((length / 500) - 1 + 1) * 2;
        //p += (width % 500 == 0) ? (width / 500) - 1 : (width / 500) - 1 + 1;
        //pl.addPart(new Part(material, p));
        return p;*/
        addParts(length, 7, 2, "Stolper nedgraves 90 cm. i jord");
    }

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
        rafters = length/50;
        addParts(width, 5, length/50, "Spær, monteres på rem");
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
        addParts(length, 10, 1, "tagplader monteres på spær");
    }

    //Calculating the pieces of carport
    private void calcBracketsRight() throws MapperExceptions {
        TreeMap<Integer, Material> mats = mf.getAllByCategory(13);
        for (Map.Entry<Integer, Material> entry : mats.entrySet()) {
                if(entry.getValue().getRef().charAt(entry.getValue().getRef().length()-1)=='r'){
                    pl.addPart(new Part(entry.getValue(), rafters, "Til montering af spær på rem"));                   
                }
            }
    }

    private void calcBracketsLeft() throws MapperExceptions {
        TreeMap<Integer, Material> mats = mf.getAllByCategory(13);
        for (Map.Entry<Integer, Material> entry : mats.entrySet()) {
            System.out.println(entry.getValue().getRef());
                if(entry.getValue().getRef().charAt(entry.getValue().getRef().length()-1)=='l'){
                    System.out.println(entry.getValue().getRef());
                    pl.addPart(new Part(entry.getValue(), rafters, "Til montering af spær på rem"));                   
                }
            }
    }

    /*public int calcScrewPackages() {
        int screws = 0;
        screws += calcBracketsRight() * 9;
        screws += calcBracketsLeft() * 9;

        return (screws % 250 == 0) ? screws / 250 : (screws / 250) + 1;
    }*/

    public int calcRoofScrews() {
        int screws = (width * length * 12) / 10000;

        return (screws % 200 == 0) ? screws / 200 : (screws / 200) + 1;
    }

    /*public int calcBand() {
        double spaceBetweenRafters = (length - 4.5) / (calcRafters() - 1);
        double fullSpace = length - (spaceBetweenRafters * 2) - 4.5;
        double bandLength = Math.sqrt(Math.pow(fullSpace, 2) + Math.pow(width, 2)) * 2;
        return (bandLength % 1000.0 == 0) ? (int) (bandLength / 1000.0) : (int) (bandLength / 1000.0 + 1.0);

    }*/

    /*public int calcBolts() {
        //value should not be less then 4
        //with arbitrary values we should see if rem is in more than one piece before calculating this
        return (calcPosts() - 1 == 4) ? 4 * 2 : ((calcPosts() - 1) % 4) * 4 + (4 * 2);
    }*/

    public void addParts(int lengthWidth, int categoryId, int multiplier, String description) throws MapperExceptions {
        materials = mf.getAllByCategory(categoryId);
        TreeMap<Integer, Material> mats = new TreeMap(Collections.reverseOrder());
        mats.putAll(materials);
        int rest = lengthWidth;
        int antal = 0;
        for (Map.Entry<Integer, Material> entry : mats.entrySet()) {

            if (rest >= entry.getKey()) {
                antal += rest / entry.getKey();
                rest -= entry.getKey();
                pl.addPart(new Part(entry.getValue(), antal * multiplier, description));
            } else if(rest != 0) {
                pl.addPart(new Part(entry.getValue(), 1 * multiplier, description));
                return;
            }
        }
    }

    public PartList getParts() {
        return pl;
    }

    public static void main(String[] args) throws MapperExceptions {
        AdvancedCalculator ac = new AdvancedCalculator(780, 600, false, false);
        ac.calcBracketsLeft();
        for (Part p : ac.getParts().getPartList()) {
            //System.out.println(p);
        }
    }

}

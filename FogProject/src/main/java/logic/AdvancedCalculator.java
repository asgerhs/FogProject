package logic;

import data.exceptions.MapperExceptions;
import data.models.Material;
import data.models.Part;
import data.models.PartList;
import java.util.Collections;
import java.util.List;
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

    }

    //Calculating the square of carport
    public int calcPosts() {
        int p = 5;
        p += (length % 500 == 0) ? ((length / 500) - 1) * 2 : ((length / 500) - 1 + 1) * 2;
        //p += (width % 500 == 0) ? (width / 500) - 1 : (width / 500) - 1 + 1;
        return p;
    }

    public int calcRem() throws MapperExceptions {
        // length > 600 use if statement when we use arbitrary value

        //If length is between 480 and 600 use 1 on each side
        if (length > 480 && length < 600) {
            return 2;
        }
        if (length < 480) {
            return 2;
        } else {
            return (length % 480 == 0) ? (length / 480) * 2 : ((length / 480) + 1) * 2;
        }
    }

    //Calculating the roof of carport
    public int calcRafters() {
        return length / 50;
    }

    //need a good way to present this
    public int calcTopFasciasFront() throws MapperExceptions {
        materials = mf.getAllByCategory(1);
        TreeMap<Integer, Material> mats = new TreeMap(Collections.reverseOrder());
        mats.putAll(materials);
        int rest;
        int antal = 0;
        
        for (Map.Entry<Integer, Material> entry : mats.entrySet()) {
            antal = (width % entry.getKey() == 0) ? width / entry.getKey() : (width / entry.getKey()) + 1;
            pl.addPart(new Part(entry.getValue(), antal));
            for(Part p : pl.getPartList()){
                System.out.println(p);
            }
            //int key = entry.getKey();
            //Material value = entry.getValue();

        }
        //pl.addPart(part);
        return antal;
    }

    public int calcTopFasciasSide() {
        return (width % 540 == 0) ? (width / 540) * 2 : ((width / 540) + 1) * 2;
    }

    public int calcBottomFasciasFB() {
        return (width % 360 == 0) ? (width / 360) * 2 : ((width / 360) + 1) * 2;
    }

    public int calcBottomFasciasSide() {
        return (length % 540 == 0) ? (length / 540) * 2 : ((length / 540) + 1) * 2;
    }

    public int calcWaterBoardFront() {
        return (width % 360 == 0) ? width / 360 : (width / 360) + 1;
    }

    public int calcWaterBoardSide() {
        return (length % 540 == 0) ? (length / 540) * 2 : ((length / 540) + 1) * 2;
    }

    //RoofingSheets need rework to specify
    /*
    int calcLength = length;
        int rest = 0;
        if(calcLength >= 600) {
            rest = calcLength - 600;
            calcLength = 600;
        }
        
        switch(length) {
            case (length > 0): return 0;
            
            
            default: return 0;
        }
     */
    //Big quant = small quant?? maybe calc length of different sheets
    public int calcRoofingSheetsBig() {
        return (width % 100 == 0) ? width / 100 : width / 100 + 1;
    }

    public int calcRoofingSheetsSmall() {
        return (width % 100 == 0) ? width / 100 : width / 100 + 1;
    }

    //Calculating the pieces of carport
    public int calcBracketsRight() {
        return calcRafters();
    }

    public int calcBracketsLeft() {
        return calcRafters();
    }

    public int calcScrewPackages() {
        int screws = 0;
        screws += calcBracketsRight() * 9;
        screws += calcBracketsLeft() * 9;

        return (screws % 250 == 0) ? screws / 250 : (screws / 250) + 1;
    }

    public int calcRoofScrews() {
        int screws = (width * length * 12) / 10000;

        return (screws % 200 == 0) ? screws / 200 : (screws / 200) + 1;
    }

    public int calcBand() {
        double spaceBetweenRafters = (length - 4.5) / (calcRafters() - 1);
        double fullSpace = length - (spaceBetweenRafters * 2) - 4.5;
        double bandLength = Math.sqrt(Math.pow(fullSpace, 2) + Math.pow(width, 2)) * 2;
        return (bandLength % 1000.0 == 0) ? (int) (bandLength / 1000.0) : (int) (bandLength / 1000.0 + 1.0);

    }

    public int calcBolts() {
        //value should not be less then 4
        //with arbitrary values we should see if rem is in more than one piece before calculating this
        return (calcPosts() - 1 == 4) ? 4 * 2 : ((calcPosts() - 1) % 4) * 4 + (4 * 2);
    }

    public static void main(String[] args) throws MapperExceptions {
        AdvancedCalculator ac = new AdvancedCalculator(780, 600, false, false);
        PartList pl = new PartList();
        /*
        pl.addPart(new Part(new Material("25x200 mm. trykimp. Brædt", 360, "stk", "understernbrædder til for & bag ende"), ac.calcBottomFasciasFB()));
        pl.addPart(new Part(new Material("25x200 mm. trykimp. Brædt", 540, "stk", "understernbrædder til siderne"), ac.calcBottomFasciasSide()));
        pl.addPart(new Part(new Material("25x125 mm. trykimp. Brædt", 360, "stk", "oversternbrædder til forenden"), ac.calcTopFasciasFront()));
        pl.addPart(new Part(new Material("25x125 mm. trykimp. Brædt", 540, "stk", "oversternbrædder til siderne"), ac.calcBottomFasciasSide()));
        pl.addPart(new Part(new Material("45x195 mm. spærtræ ubh.", 480, "stk", "Remme i sider, sadles ned i stolper"), ac.calcRem()));
        pl.addPart(new Part(new Material("45x195 mm. spærtræ ubh.", 600, "stk", "Spær, monteres på rem"), ac.calcRafters()));
        pl.addPart(new Part(new Material("97x97 mm. trykimp. Stolpe", 300, "stk", "Stolper nedgraves 90 cm. i jord"), ac.calcPosts()));
        pl.addPart(new Part(new Material("19x100 mm. trykimp. Brædt", 540, "stk", "vandbrædt på stern i sider"), ac.calcWaterBoardSide()));
        pl.addPart(new Part(new Material("19x100 mm. trykimp. Brædt", 360, "stk", "vandbrædt på stern i forende"), ac.calcWaterBoardFront()));
        pl.addPart(new Part(new Material("Plastmo Ecolite blåtonet", 600, "stk", "tagplader monteres på spær"), ac.calcRoofingSheetsBig()));
        pl.addPart(new Part(new Material("Plastmo Ecolite blåtonet", 360, "stk", "tagplader monteres på spær"), ac.calcPosts()));

        pl.addPart(new Part(new Material("plastmo bundskruer 200 stk.", 0, "pakke", "Skruer til tagplader"), ac.calcRoofScrews()));
        pl.addPart(new Part(new Material("hulbånd 1x20 mm. 10 mtr.", 0, "Rulle", "Til vindkryds på spær"), ac.calcBand()));
        pl.addPart(new Part(new Material("universal 190 mm højre", 0, "stk", "Til montering af spær på rem"), ac.calcBracketsRight()));
        pl.addPart(new Part(new Material("universal 190 mm venstre", 0, "stk", "Til montering af spær på rem"), ac.calcBracketsLeft()));
        pl.addPart(new Part(new Material("4,5 x 60 mm. skruer 200 stk.", 0, "pakke", "Til montering af stern&vandbrædt"), 1));
        pl.addPart(new Part(new Material("4,0 x 50 mm. beslagskruer 250 stk.", 0, "pakke", "Til montering af universalbeslag + hulbånd"), ac.calcScrewPackages()));
        pl.addPart(new Part(new Material("bræddebolt 10 x 120 mm.", 0, "stk", "Til montering af rem på stolper"), ac.calcBolts()));
        pl.addPart(new Part(new Material("firkantskiver 40x40x11mm", 0, "stk", "Til montering af rem på stolper"), 1));
        
        BasicPartListPrinter.print(pl);
         */

        System.out.println(ac.calcTopFasciasFront());
    }

}

package logic;

import data.models.Material;
import data.models.Part;
import data.models.PartList;

/**
 *
 * @author Martin Frederiksen
 */
public class BasicCalculator {
    private int length;
    private int width;

    public BasicCalculator() {
        this.length = 780;
        this.width = 600;
    }
    
    //Calculating the square of carport
    public int calcPosts(){
        return 4 + ((length / 500) * 2) + 1;
    }
        
    public int calcRem(){
        // length > 600 use if statement when we use arbitrary value
        return (length % 480 == 0) ? (length / 480) * 2 : ((length / 480) + 1) * 2;
    }
    
    //Calculating the roof of carport
    public int calcRafters(){
        return length / 50;
    }
    
    public int calcTopFasciasFront(){
        return (width % 360 == 0) ? width / 360 : (width / 360) + 1;
    }
    
    public int calcTopFasciasSide(){
        return (width % 540 == 0) ? (width / 540) * 2 : ((width / 540) + 1) * 2;
    }
    
    public int calcBottomFasciasFB(){
        return (width % 360 == 0) ? (width / 360) * 2 : ((width / 360) + 1) * 2;
    } 
    
    public int calcBottomFasciasSide(){
        return (length % 540 == 0) ? (length / 540) * 2 : ((length / 540) + 1) * 2;
    } 
    
    public int calcWaterBoardFront(){
        return (width % 360 == 0) ? width / 360 : (width / 360) + 1;
    }
    
    public int calcWaterBoardSide(){
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
    public int calcRoofingSheetsBig(){
        return (width % 100 == 0) ? width / 100 : width / 100 + 1;
    }
    
    public int calcRoofingSheetsSmall(){
        return (width % 100 == 0) ? width / 100 : width / 100 + 1;
    }
    
    
    //Calculating the pieces of carport
    public int calcBracketsRight(){
        return calcRafters();
    }
    
    public int calcBracketsLeft(){
        return calcRafters();
    }
    
    public int calcScrewPackages(){
        int screws = 0;
        screws += calcBracketsRight()*9;
        screws += calcBracketsLeft()*9;
        
        return (screws % 250 == 0) ? screws / 250 : (screws / 250) + 1;
    }
    
    public int calcRoofScrews(){
        int screws = (width * length * 12) / 10000;
        
        return (screws % 200 == 0) ? screws / 200 : (screws / 200) + 1;
    }
    
    public int calcBand(){
        double bandLengt = Math.sqrt(Math.pow(521, 2) + Math.pow(504, 2)) * 2;
        return (bandLengt % 1000.0 == 0) ? (int) (bandLengt / 1000.0) : (int) (bandLengt / 1000.0 + 1.0);
    }
    
    public int calcBolts(){
        //value should not be less then 4
        //with arbitrary values we should see if rem is in more than one piece before calculating this
        return (calcPosts()-1 == 4) ? 4 * 2 : ((calcPosts()-1) % 4) * 4 + (4 * 2);
    }
    
    public PartList getParts(){
        PartList pl = new PartList();
        
//        pl.addPart(new Part(new Material("25x200 mm. trykimp. Brædt", 360, "stk", "understernbrædder til for & bag ende"), calcBottomFasciasFB()));
//        pl.addPart(new Part(new Material("25x200 mm. trykimp. Brædt", 540, "stk", "understernbrædder til siderne"), calcBottomFasciasSide()));
//        pl.addPart(new Part(new Material("25x125 mm. trykimp. Brædt", 360, "stk", "oversternbrædder til forenden"), calcTopFasciasFront()));
//        pl.addPart(new Part(new Material("25x125 mm. trykimp. Brædt", 540, "stk", "oversternbrædder til siderne"), calcBottomFasciasSide()));
//        pl.addPart(new Part(new Material("45x195 mm. spærtræ ubh.", 480, "stk", "Remme i sider, sadles ned i stolper"), calcRem()));
//        pl.addPart(new Part(new Material("45x195 mm. spærtræ ubh.", 600, "stk", "Spær, monteres på rem"), calcRafters()));
//        pl.addPart(new Part(new Material("97x97 mm. trykimp. Stolpe", 300, "stk", "Stolper nedgraves 90 cm. i jord"), calcPosts()));       
//        pl.addPart(new Part(new Material("19x100 mm. trykimp. Brædt", 540, "stk", "vandbrædt på stern i sider"), calcWaterBoardSide()));
//        pl.addPart(new Part(new Material("19x100 mm. trykimp. Brædt", 360, "stk", "vandbrædt på stern i forende"), calcWaterBoardFront()));
//        pl.addPart(new Part(new Material("Plastmo Ecolite blåtonet", 600, "stk", "tagplader monteres på spær"), calcRoofingSheetsBig()));
//        pl.addPart(new Part(new Material("Plastmo Ecolite blåtonet", 360, "stk", "tagplader monteres på spær"), calcPosts()));
//        
//        
//        pl.addPart(new Part(new Material("plastmo bundskruer 200 stk.", 0, "pakke", "Skruer til tagplader"), calcRoofScrews()));
//        pl.addPart(new Part(new Material("hulbånd 1x20 mm. 10 mtr.", 0, "Rulle", "Til vindkryds på spær"), calcBand()));
//        pl.addPart(new Part(new Material("universal 190 mm højre", 0, "stk", "Til montering af spær på rem"), calcBracketsRight()));
//        pl.addPart(new Part(new Material("universal 190 mm venstre", 0, "stk", "Til montering af spær på rem"), calcBracketsLeft()));
//        pl.addPart(new Part(new Material("4,5 x 60 mm. skruer 200 stk.", 0, "pakke", "Til montering af stern&vandbrædt"), 1));
//        pl.addPart(new Part(new Material("4,0 x 50 mm. beslagskruer 250 stk.", 0, "pakke", "Til montering af universalbeslag + hulbånd"), calcScrewPackages()));
//        pl.addPart(new Part(new Material("bræddebolt 10 x 120 mm.", 0, "stk", "Til montering af rem på stolper"), calcBolts()));
//        pl.addPart(new Part(new Material("firkantskiver 40x40x11mm", 0, "stk", "Til montering af rem på stolper"), 1));
        
        return pl;
    }
    
    
    public static void main(String[] args) {
        BasicCalculator bc = new BasicCalculator();
        PartList pl = new PartList();
        
//        pl.addPart(new Part(new Material("25x200 mm. trykimp. Brædt", 360, "stk", "understernbrædder til for & bag ende"), bc.calcBottomFasciasFB()));
//        pl.addPart(new Part(new Material("25x200 mm. trykimp. Brædt", 540, "stk", "understernbrædder til siderne"), bc.calcBottomFasciasSide()));
//        pl.addPart(new Part(new Material("25x125 mm. trykimp. Brædt", 360, "stk", "oversternbrædder til forenden"), bc.calcTopFasciasFront()));
//        pl.addPart(new Part(new Material("25x125 mm. trykimp. Brædt", 540, "stk", "oversternbrædder til siderne"), bc.calcBottomFasciasSide()));
//        pl.addPart(new Part(new Material("45x195 mm. spærtræ ubh.", 480, "stk", "Remme i sider, sadles ned i stolper"), bc.calcRem()));
//        pl.addPart(new Part(new Material("45x195 mm. spærtræ ubh.", 600, "stk", "Spær, monteres på rem"), bc.calcRafters()));
//        pl.addPart(new Part(new Material("97x97 mm. trykimp. Stolpe", 300, "stk", "Stolper nedgraves 90 cm. i jord"), bc.calcPosts()));       
//        pl.addPart(new Part(new Material("19x100 mm. trykimp. Brædt", 540, "stk", "vandbrædt på stern i sider"), bc.calcWaterBoardSide()));
//        pl.addPart(new Part(new Material("19x100 mm. trykimp. Brædt", 360, "stk", "vandbrædt på stern i forende"), bc.calcWaterBoardFront()));
//        pl.addPart(new Part(new Material("Plastmo Ecolite blåtonet", 600, "stk", "tagplader monteres på spær"), bc.calcRoofingSheetsBig()));
//        pl.addPart(new Part(new Material("Plastmo Ecolite blåtonet", 360, "stk", "tagplader monteres på spær"), bc.calcPosts()));
//        
//        
//        pl.addPart(new Part(new Material("plastmo bundskruer 200 stk.", 0, "pakke", "Skruer til tagplader"), bc.calcRoofScrews()));
//        pl.addPart(new Part(new Material("hulbånd 1x20 mm. 10 mtr.", 0, "Rulle", "Til vindkryds på spær"), bc.calcBand()));
//        pl.addPart(new Part(new Material("universal 190 mm højre", 0, "stk", "Til montering af spær på rem"), bc.calcBracketsRight()));
//        pl.addPart(new Part(new Material("universal 190 mm venstre", 0, "stk", "Til montering af spær på rem"), bc.calcBracketsLeft()));
//        pl.addPart(new Part(new Material("4,5 x 60 mm. skruer 200 stk.", 0, "pakke", "Til montering af stern&vandbrædt"), 1));
//        pl.addPart(new Part(new Material("4,0 x 50 mm. beslagskruer 250 stk.", 0, "pakke", "Til montering af universalbeslag + hulbånd"), bc.calcScrewPackages()));
//        pl.addPart(new Part(new Material("bræddebolt 10 x 120 mm.", 0, "stk", "Til montering af rem på stolper"), bc.calcBolts()));
//        pl.addPart(new Part(new Material("firkantskiver 40x40x11mm", 0, "stk", "Til montering af rem på stolper"), 1));
        
        
        
        BasicPartListPrinter.print(pl);
        
    }
}
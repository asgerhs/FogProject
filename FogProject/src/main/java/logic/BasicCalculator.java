package logic;

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
    
    public int calcBand(){
        double bandLengt = Math.sqrt(Math.pow(521, 2) + Math.pow(504, 2)) * 2;
        return (bandLengt % 1000.0 == 0) ? (int) (bandLengt / 1000.0) : (int) (bandLengt / 1000.0 + 1.0);
    }
    
    public static void main(String[] args) {
        BasicCalculator bc = new BasicCalculator();
        System.out.println(bc.calcBand());
        
    }
}
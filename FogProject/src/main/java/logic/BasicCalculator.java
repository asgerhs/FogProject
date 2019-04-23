package logic;

/**
 *
 * @author Martin Frederiksen
 */
public class BasicCalculator {
    private double length;
    private double width;

    public BasicCalculator() {
        this.length = 7.80;
        this.width = 6.00;
    }
    
    
    private int calcPosts(){
        return 7;
    }
    
    private int calcRafters(){
        return 15;
    }
    
    private int calcBrackets(){
        return 30;
    }
    
    private int calcScrewPackages(){
        return 1;
    }
    
}

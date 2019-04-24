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
    
    //Calculating the square of carport
    public int calcPosts(){
        return 4 + (int) (length / 5) * 2 + 1;
    }
    
    
    //Calculating the roof of carport
    public int calcRafters(){
        return (int) (length / .5);
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
        screws += calcBracketsRight()*6;
        screws += calcBracketsLeft()*6;
        
        return (screws % 250 == 0) ? screws / 250 : (screws / 250) + 1;
    }
    
    public static void main(String[] args) {
        BasicCalculator bc = new BasicCalculator();
        System.out.println(bc.calcScrewPackages());
        
    }
}
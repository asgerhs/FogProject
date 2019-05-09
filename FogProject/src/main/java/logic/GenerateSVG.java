package logic;

/**
 *
 * @author Andreas Vikke
 */
public class GenerateSVG {
    private String svg;
    private int length;
    private int width;
    
    public GenerateSVG(int length, int width) {
        svg = "<svg width='" + length + "' height='" + (width + 100) + "'>";
        this.length = length;
        this.width = width;
    }
    
    public void generateRafter(int count, int space) {
        for (int i = 0; i < count; i++) {
            svg += generateRect(100 + space * i, 100, width, 10);
        }
    }
    
    private String generateRect(int x, int y, int height, int width) {
        return "<rect x='" + x + "' y='" + y + "' height='" + height + "' width='" + width + "'/>";
    }
    
    public String getSVG() {
        return svg;
    }
}

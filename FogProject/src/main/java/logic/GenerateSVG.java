package logic;

/**
 *
 * @author Andreas Vikke
 */
public class GenerateSVG {
    private String svg;
    private int height;
    private int width;
    
    public GenerateSVG(int length, int width) {
        svg = "<svg width='" + length + "' height='" + (width + 100) + "'>";
        svg += generateRect(0, 0, width, length);
        this.height = width;
        this.width = length;
    }
    
    public void generateRafter(int count, int space) {
        for (int i = 0; i < count; i++) {
            svg += generateRect(space * i, 0, height, 10);
        }
    }
    
    private String generateRect(int x, int y, int height, int width) {
        return "<rect x='" + x + "' y='" + y + "' height='" + height + "' width='" + width + "'/>";
    }
    
    public String getSVG() {
        return svg;
    }
}

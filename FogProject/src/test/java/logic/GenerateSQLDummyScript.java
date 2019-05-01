package logic;

import com.github.javafaker.Faker;
import data.models.Category;
import data.models.Material;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andreas Vikke
 */
public class GenerateSQLDummyScript {
    private Formatter f;
    
    private void generateMaterialScript() throws IOException {
        f.format("-- Woods\n");
        List<Material> mats = new ArrayList();
        
        // Woods
        mats.add(new Material("1000", "25x200 mm. trykimp. Brædt", 360, 1, "stk"));
        mats.add(new Material("1001", "25x200 mm. trykimp. Brædt", 540, 1, "stk"));
        mats.add(new Material("1002", "25x125mm. trykimp. Brædt", 360, 1, "stk"));
        mats.add(new Material("1003", "25x125mm. trykimp. Brædt", 540, 1, "stk"));
        mats.add(new Material("1004", "38x73 mm. Lægte ubh.", 420, 1, "stk"));
        mats.add(new Material("1005", "45x95 mm. Reglar ub.", 240, 1, "stk"));
        mats.add(new Material("1006", "45x95 mm. Reglar ub.", 270, 1, "stk"));
        mats.add(new Material("1007", "45x195 mm. spærtræ ubh.", 480, 1, "stk"));
        mats.add(new Material("1008", "45x195 mm. spærtræ ubh.", 600, 1, "stk"));
        mats.add(new Material("1009", "97x97 mm. trykimp. Stolpe", 300, 1, "stk"));
        mats.add(new Material("1010", "19x100 mm. trykimp. Brædt", 210, 1, "stk"));
        mats.add(new Material("1011", "19x100 mm. trykimp. Brædt", 360, 1, "stk"));
        mats.add(new Material("1012", "19x100 mm. trykimp. Brædt", 540, 1, "stk"));
        mats.add(new Material("1013", "Plastmo Ecolite blåtonet", 360, 1, "stk"));
        mats.add(new Material("1014", "Plastmo Ecolite blåtonet", 600, 1, "stk"));
        
        // Misc
        mats.add(new Material("1030", "Plastmo bundskruer 200 stk.", 1, 200, "pakke"));
        mats.add(new Material("1031", "Hulbånd 1x20 mm. 10 mtr.", 1000, 1, "rulle"));
        mats.add(new Material("1032", "Universal 190 mm højre", 190, 1, "stk"));
        mats.add(new Material("1033", "Universal 190 mm venstre", 190, 1, "stk"));
        mats.add(new Material("1034", "4,5x60 mm. skruer 200 stk.", 60, 200, "pakke"));
        mats.add(new Material("1035", "4,0x50 mm. skruer 250 stk.", 50, 250, "pakke"));
        mats.add(new Material("1036", "Bræddebolt 10x120 mm.", 120, 1, "stk"));
        mats.add(new Material("1037", "Firkantskiver 40x40x11mm", 11, 1, "stk"));
        mats.add(new Material("1038", "4,5x70 mm. Skruer 400 stk.", 70, 400, "pakke"));
        mats.add(new Material("1039", "4,5x50 mm. Skruer 300 stk.", 50, 300, "pakke"));
        mats.add(new Material("1040", "Stalddørsgreb 50x75", 75, 1, "sæt"));
        mats.add(new Material("1041", "T hængsel 390 mm", 390, 1, "stk"));
        mats.add(new Material("1042", "Vinkelbeslag 35", 35, 1, "stk"));
        
        String sql = "";
        String sqlStart = "INSERT INTO stock VALUES (";
        String sqlEnd = ");\n";
        
        for(Material m : mats) {
            sql += sqlStart + "'" + m.getRef() + "', '" + m.getName()+ "', " + m.getLength()+ ", " + m.getAmount()+ ", '" + m.getUnit()+ "'" + sqlEnd;
        }
        
        f.format(sql);
    }
    
    
    private void generateMatCategoriesScript() throws IOException {
        f.format("\n\n-- Categories\n");
        List<Category> cats = new ArrayList();
        
        //all categories in database
        cats.add(new Category("Understernbrædder"));
        cats.add(new Category("Oversternbrædder"));
        cats.add(new Category("Lægter"));
        cats.add(new Category("Reglar"));
        cats.add(new Category("Spærtræ"));
        cats.add(new Category("Rem"));
        cats.add(new Category("Stolpe"));
        cats.add(new Category("Brædt"));
        cats.add(new Category("Vandbræt"));
        cats.add(new Category("Tagplader"));
        cats.add(new Category("Skruer"));
        cats.add(new Category("Hulbånd"));
        cats.add(new Category("Beslag"));
        cats.add(new Category("Bolt"));
        cats.add(new Category("Skiver"));
        cats.add(new Category("Diverse"));
        
        
        String sql = "";
        String sqlStart = "INSERT INTO categories(name) VALUES (";
        String sqlEnd = ");\n";
        
        for(Category c : cats) {
            sql += sqlStart + "'" + c.getName() + "'" + sqlEnd;
        }
        
        f.format(sql);
    }
    
    private void generateMatCategoriesLinkScript() throws IOException {
        f.format("\n\n-- Categories - Stock link\n");
        HashMap<String, Integer> link = new HashMap();
        
        //all categories in database
        link.put("1000", 1);
        link.put("1001", 1);
        link.put("1002", 2);
        link.put("1003", 2);
        link.put("1004", 3);
        link.put("1005", 4);
        link.put("1006", 4);
        link.put("1007", 5);
        link.put("1008", 5);
        link.put("1007", 6);
        link.put("1008", 6);
        link.put("1009", 7);
        link.put("1010", 8);
        link.put("1011", 8);
        link.put("1012", 8);
        link.put("1010", 9);
        link.put("1011", 9);
        link.put("1012", 9);
        link.put("1013", 10);
        link.put("1014", 10);
        
        link.put("1030", 11);
        link.put("1034", 11);
        link.put("1035", 11);
        link.put("1038", 11);
        link.put("1039", 11);
        link.put("1031", 12);
        link.put("1032", 13);
        link.put("1033", 13);
        link.put("1042", 13);
        link.put("1036", 14);
        link.put("1037", 15);
        link.put("1040", 16);
        link.put("1041", 16);
        
        String sql = "";
        String sqlStart = "INSERT INTO stockToCategory VALUES (";
        String sqlEnd = ");\n";
        
        for(Map.Entry<String, Integer> entry : link.entrySet()) {
            sql += sqlStart + "'" + entry.getKey() + "'," + entry.getValue() + sqlEnd;
        }
        
        f.format(sql);
    }    
    
    private void generateUserScript() throws IOException {
        f.format("\n\n-- Users\n");
        
        String sql = "";
        String sqlStart = "INSERT INTO users VALUES (";
        String sqlEnd = ");\n";
        
        ArrayList<String> names = new ArrayList(); 
        
        for(int i = 0; i < 100; i++) {
            Faker faker = new Faker();
            String name = faker.name().firstName();
            while(names.contains(name)) {
                name = faker.name().firstName();
            }
            names.add(name);
            sql += sqlStart + "'" + name + "', '" + name + "@somewhere.dk', " + "'1234', 'SALESMAN'" + sqlEnd;
        }
        
        f.format(sql);
    }
    
    @Test
    public void runTests() throws IOException {
        FileWriter fw = new FileWriter("GeneratedDummyData.sql", false);
        f = new Formatter(fw);
        
        generateMaterialScript();
        generateMatCategoriesScript();
        generateMatCategoriesLinkScript();
        generateUserScript();
        
        f.close();
        assertTrue(true);
    }
}

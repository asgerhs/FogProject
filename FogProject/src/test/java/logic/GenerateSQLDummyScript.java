package logic;

import com.github.javafaker.Faker;
import data.models.Material;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andreas Vikke
 */
public class GenerateSQLDummyScript {
    
    @Test
    public void generateMaterialScript() throws IOException {
        FileWriter fw = new FileWriter("GeneratedDummyData.sql", false);
        Formatter f = new Formatter(fw);
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
        f.close();
        
        assertTrue(true);
    }
    
    @Test
    public void generateUserScript() throws IOException {
        FileWriter fw = new FileWriter("GeneratedDummyData.sql", true);
        Formatter f = new Formatter(fw);
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
        f.close();
        
        assertTrue(true);
    }
}

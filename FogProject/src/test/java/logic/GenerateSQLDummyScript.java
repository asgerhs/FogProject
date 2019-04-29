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
        mats.add(new Material("1000", "25x200 mm. trykimp. Brædt", 540, 1, "stk"));
        mats.add(new Material("1001", "25x125mm. trykimp. Brædt", 360, 1, "stk"));
        mats.add(new Material("1001", "25x125mm. trykimp. Brædt", 540, 1, "stk"));
        mats.add(new Material("1002", "38x73 mm. Lægte ubh.", 420, 1, "stk"));
        mats.add(new Material("1003", "45x95 mm. Reglar ub.", 240, 1, "stk"));
        mats.add(new Material("1003", "45x95 mm. Reglar ub.", 270, 1, "stk"));
        mats.add(new Material("1003", "45x195 mm. spærtræ ubh.", 480, 1, "stk"));
        mats.add(new Material("1003", "45x195 mm. spærtræ ubh.", 600, 1, "stk"));
        mats.add(new Material("1004", "97x97 mm. trykimp. Stolpe", 300, 1, "stk"));
        mats.add(new Material("1005", "19x100 mm. trykimp. Brædt", 210, 1, "stk"));
        mats.add(new Material("1005", "19x100 mm. trykimp. Brædt", 360, 1, "stk"));
        mats.add(new Material("1005", "19x100 mm. trykimp. Brædt", 540, 1, "stk"));
        mats.add(new Material("1006", "Plastmo Ecolite blåtonet", 360, 1, "stk"));
        mats.add(new Material("1006", "Plastmo Ecolite blåtonet", 600, 1, "stk"));
        
        // Misc
        mats.add(new Material("1020", "Plastmo bundskruer 200 stk.", 1, 200, "pakke"));
        mats.add(new Material("1021", "Hulbånd 1x20 mm. 10 mtr.", 1000, 1, "rulle"));
        mats.add(new Material("1022", "Universal 190 mm højre", 190, 1, "stk"));
        mats.add(new Material("1023", "Universal 190 mm venstre", 190, 1, "stk"));
        mats.add(new Material("1024", "4,5x60 mm. skruer 200 stk.", 60, 200, "pakke"));
        mats.add(new Material("1024", "4,0x50 mm. skruer 250 stk.", 50, 250, "pakke"));
        mats.add(new Material("1025", "Bræddebolt 10x120 mm.", 120, 1, "stk"));
        mats.add(new Material("1026", "Firkantskiver 40x40x11mm", 11, 1, "stk"));
        mats.add(new Material("1027", "4,5x70 mm. Skruer 400 stk.", 70, 400, "pakke"));
        mats.add(new Material("1027", "4,5x50 mm. Skruer 300 stk.", 50, 300, "pakke"));
        mats.add(new Material("1028", "Stalddørsgreb 50x75", 75, 1, "sæt"));
        mats.add(new Material("1029", "T hængsel 390 mm", 390, 1, "stk"));
        mats.add(new Material("1030", "Vinkelbeslag 35", 35, 1, "stk"));
        
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

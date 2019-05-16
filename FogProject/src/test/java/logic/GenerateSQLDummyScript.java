package logic;

import com.github.javafaker.Faker;
import data.models.Category;
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

    private Formatter f;

    private void generateMaterialScript() throws IOException {
        //f.format("-- Woods\n");
        List<Material> mats = new ArrayList();

        // Woods
        mats.add(new Material("1000", "25x200 mm. trykimp. Brædt", 3600, 1, "stk", 17983));
        mats.add(new Material("1001", "25x200 mm. trykimp. Brædt", 5400, 1, "stk", 26973));
        mats.add(new Material("1002", "25x125mm. trykimp. Brædt", 3600, 1, "stk", 10783));
        mats.add(new Material("1003", "25x125mm. trykimp. Brædt", 5400, 1, "stk", 16173));
        mats.add(new Material("1004", "38x73 mm. Lægte ubh.", 4200, 1, "stk", 2499));
        mats.add(new Material("1005", "45x95 mm. Reglar ub.", 2400, 1, "stk", 3108)); 
        mats.add(new Material("1006", "45x95 mm. Reglar ub.", 2700, 1, "stk", 3496)); 
        mats.add(new Material("1007", "45x195 mm. spærtræ ubh.", 4800, 1, "stk", 23016));
        mats.add(new Material("1008", "45x195 mm. spærtræ ubh.", 6000, 1, "stk", 28770));
        mats.add(new Material("1009", "97x97 mm. trykimp. Stolpe", 3000, 1, "stk", 11385));
        mats.add(new Material("1010", "19x100 mm. trykimp. Brædt", 2100, 1, "stk", 1250));
        mats.add(new Material("1011", "19x100 mm. trykimp. Brædt", 3600, 1, "stk", 2143));
        mats.add(new Material("1012", "19x100 mm. trykimp. Brædt", 5400, 1, "stk", 3213));
        mats.add(new Material("1013", "Plastmo Ecolite blåtonet", 3600, 1, "stk", 11900));
        mats.add(new Material("1014", "Plastmo Ecolite blåtonet", 6000, 1, "stk", 23000));
        mats.add(new Material("1015", "38x73 mm. taglægte T1", 5400, 1, "stk", 9153));
        mats.add(new Material("1016", "38x73 mm. taglægte T1", 4200, 1, "stk", 7119));

        // Misc
        mats.add(new Material("1030", "Plastmo bundskruer 200 stk.", 0, 200, "pakke", 39500));
        mats.add(new Material("1031", "Hulbånd 1x20 mm. 10 mtr.", 1000, 1, "rulle", 20900));
        mats.add(new Material("1032-r", "Universal 190 mm højre", 190, 1, "stk", 1590));
        mats.add(new Material("1032-l", "Universal 190 mm venstre", 190, 1, "stk", 1590));
        mats.add(new Material("1034", "4,5x60 mm. skruer 200 stk.", 60, 200, "pakke", 15900));
        mats.add(new Material("1035", "4,0x50 mm. skruer 250 stk.", 50, 250, "pakke", 22900));
        mats.add(new Material("1036", "Bræddebolt 10x120 mm.", 120, 1, "stk", 1953));
        mats.add(new Material("1037", "Firkantskiver 40x40x11mm", 11, 1, "stk", 1011));
        mats.add(new Material("1038", "4,5x70 mm. Skruer 400 stk.", 70, 400, "pakke", 19900));
        mats.add(new Material("1039", "4,5x50 mm. Skruer 300 stk.", 50, 300, "pakke", 9595));
        mats.add(new Material("1040", "Stalddørsgreb 50x75 mm", 75, 1, "sæt", 14320));
        mats.add(new Material("1041", "T hængsel 390 mm", 390, 1, "stk", 6396));
        mats.add(new Material("1042", "Vinkelbeslag 35 mm", 35, 1, "stk", 2495));
        mats.add(new Material("1043", "5,0 x 40 mm. beslagskruer 250 stk.", 40, 250, "pakke", 21000));
        mats.add(new Material("1044", "5,0 x 100 mm. skruer 100 stk", 100, 100, "pakke", 18900));
        
        //Roof materials (pack)
        mats.add(new Material("1060", "B & C Dobbelt -s sort", 0, 300, "stk", 589));
        mats.add(new Material("1061", "B & C Rygsten sort", 0, 1, "stk", 4500));
        mats.add(new Material("1062", "B & C Toplægte holder", 0, 1, "stk", 1995));
        mats.add(new Material("1063", "B & C Rygstensbeslag", 0, 1, "stk", 1010));
        mats.add(new Material("1064", "B & C Tagstens bindere & nakkekroge", 0, 1, "pakke", 81900));

        String sql = "";
        String sqlStart = "INSERT INTO `stock` VALUES (";
        String sqlEnd = ");\n";

        for (Material m : mats) {
            sql += sqlStart + "'" + m.getRef() + "', '" + m.getName() + "', " + m.getLength() + ", " + m.getAmount() + ", '" + m.getUnit() + "', " + m.getPrice() + sqlEnd;
        }

        f.format(sql);
    }

    private void generateMatCategoriesScript() throws IOException {
        //f.format("\n\n-- Categories\n");
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
        cats.add(new Category("ShedMisc"));
        cats.add(new Category("Tagpakke"));
        
        String sql = "";
        String sqlStart = "INSERT INTO `categories`(name) VALUES (";
        String sqlEnd = ");\n";

        for (Category c : cats) {
            sql += sqlStart + "'" + c.getName() + "'" + sqlEnd;
        }

        f.format(sql);
    }

    private void generateMatCategoriesLinkScript() throws IOException {
        //f.format("\n\n-- Categories - Stock link\n");
        ArrayList<MatCatLink> link = new ArrayList();

        //all categories in database
        //Wood materials
        link.add(new MatCatLink("1000", 1));
        link.add(new MatCatLink("1001", 1));
        link.add(new MatCatLink("1002", 2));
        link.add(new MatCatLink("1003", 2));
        link.add(new MatCatLink("1004", 3));
        link.add(new MatCatLink("1005", 4));
        link.add(new MatCatLink("1006", 4));
        link.add(new MatCatLink("1007", 5));
        link.add(new MatCatLink("1008", 5));
        link.add(new MatCatLink("1007", 6));
        link.add(new MatCatLink("1008", 6));
        link.add(new MatCatLink("1009", 7));
        link.add(new MatCatLink("1010", 8));
        link.add(new MatCatLink("1011", 8));
        link.add(new MatCatLink("1012", 8));
        link.add(new MatCatLink("1010", 9));
        link.add(new MatCatLink("1011", 9));
        link.add(new MatCatLink("1012", 9));
        link.add(new MatCatLink("1013", 10));
        link.add(new MatCatLink("1014", 10));
        link.add(new MatCatLink("1015", 3));
        link.add(new MatCatLink("1016", 3));
        
        //Misc materials
        link.add(new MatCatLink("1030", 11));
        link.add(new MatCatLink("1034", 11));
        link.add(new MatCatLink("1035", 11));
        link.add(new MatCatLink("1038", 11));
        link.add(new MatCatLink("1039", 11));
        link.add(new MatCatLink("1031", 12));
        link.add(new MatCatLink("1032-r", 13));
        link.add(new MatCatLink("1032-l", 13));
        link.add(new MatCatLink("1042", 13));
        link.add(new MatCatLink("1036", 14));
        link.add(new MatCatLink("1037", 15));
        link.add(new MatCatLink("1040", 16));
        link.add(new MatCatLink("1041", 16));
        link.add(new MatCatLink("1004", 16));
        link.add(new MatCatLink("1043", 11));
        link.add(new MatCatLink("1044", 11));
        
        //Materials for roof, might need to change the categories of some
        link.add(new MatCatLink("1060", 17));
        link.add(new MatCatLink("1061", 17));
        link.add(new MatCatLink("1062", 17));
        link.add(new MatCatLink("1063", 17));
        link.add(new MatCatLink("1064", 17));
        
        String sql = "";
        String sqlStart = "INSERT INTO `stockToCategory` VALUES (";
        String sqlEnd = ");\n";

        for (MatCatLink mcl : link) {
            sql += sqlStart + "'" + mcl.ref + "'," + mcl.id + sqlEnd;
        }

        f.format(sql);
    }

    private void generateUserScript() throws IOException {
        //f.format("\n\n-- Users\n");

        String sql = "";
        String sqlStart = "INSERT INTO `users` VALUES (";
        String sqlEnd = ");\n";

        ArrayList<String> names = new ArrayList();

        for (int i = 0; i < 100; i++) {
            Faker faker = new Faker();
            String name = faker.name().firstName();
            while (names.contains(name)) {
                name = faker.name().firstName();
            }
            names.add(name);
            sql += sqlStart + "'" + name + "', '" + name + "@somewhere.dk', " + "'1234', 'CUSTOMER'" + sqlEnd;
        }
        sql += sqlStart + "'" + "BestSalesman" + "', '" + "BestSalesman" + "@somewhere.dk', " + "'1234', 'EMPLOYEE'" + sqlEnd;
        sql += sqlStart + "'" + "Admin" + "', '" + "Admin" + "@somewhere.dk', " + "'1234', 'ADMIN'" + sqlEnd;
        
        f.format(sql);
    }

    private void generateRequestScript() throws IOException {
        //f.format("\n\n-- Requests\n");

        String sql = "";
        String sqlStart = "INSERT INTO `requests`"
                + "(width, length, shedWidth, shedLength, roof, angle, name, address, zipCity, phone, email, note) VALUES (";
        String sqlEnd = ");\n";

        for (int i = 0; i < 25; i++) {
            Faker faker = new Faker();
            int num = faker.number().numberBetween(500, 780);
            int shed = faker.number().numberBetween(100, 210);
            String phone = faker.phoneNumber().cellPhone();
            String address = faker.address().streetAddress();
            String name = faker.name().firstName();

            sql += sqlStart + num + ", " + num + ", " + shed + ", " + shed + ", " + "'not flat'" + ", " + 30 + ", '"
                    + name + "', '" + address + "', '" + name + "s Zip" + "', '" + phone
                    + "', '" + name + "@somewhere.com" + "', '" + "This is a test for " + name + "'" + sqlEnd;

        }
        f.format(sql);
    }

    @Test
    public void runTests() throws IOException {
        FileWriter fw = new FileWriter("../Database/GeneratedDummyData.sql", false);
        f = new Formatter(fw);

        generateMaterialScript();
        generateMatCategoriesScript();
        generateMatCategoriesLinkScript();
        generateUserScript();
        generateRequestScript();

        f.close();
        assertTrue(true);
    }

    private class MatCatLink {

        public String ref;
        public int id;

        public MatCatLink(String ref, int id) {
            this.ref = ref;
            this.id = id;
        }
    }
}

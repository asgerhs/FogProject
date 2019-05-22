package data;

import data.interfaces.MapperInterface;
import data.models.LoggerEnum;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author Andreas Vikke
 */
public class ExceptionLogger {
    
    private static Logger logger = Logger.getLogger(MapperInterface.class.getName());
    
    public static void log(LoggerEnum loggerEnum, String message, StackTraceElement[] stes) {
        try {
            FileHandler handler = new FileHandler("logs/" + loggerEnum.toString().toLowerCase() + "/" + loggerEnum.toString().toLowerCase()
                    + "-log.%u.%g.txt", 1024 * 1024, 10);
            logger.addHandler(handler);
            handler.setFormatter(new SimpleFormatter());
            
            String error =  "Error in getAll Method: \n" + message + "\n";
            for(StackTraceElement ste : stes)
                error += ste.toString() + "\n";
            logger.log(Level.SEVERE, error);
        } catch (IOException ex) {
            ex.printStackTrace();
            logger.log(Level.SEVERE, "Error in logger", new IOException("Error: " + ex.getMessage()));
        }
    }
}

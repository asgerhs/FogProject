package data.mappers;

import data.DatabaseConnector;
import data.exceptions.OrderException;
import data.interfaces.MapperInterface;
import data.models.Order;
import data.models.Request;
import data.models.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.sql.DataSource;

/**
 *
 * @author Asger Hermind Sørensen
 */
public class OrderMapper implements MapperInterface<Order, String> {
DatabaseConnector dbc = new DatabaseConnector();

   public OrderMapper(DataSource ds) {
       dbc.setDataSource(ds);
   }

   private static Logger logger = Logger.getLogger(OrderMapper.class.getName());

   public OrderMapper() {
       try {

           FileHandler handler = new FileHandler("logs/OrderMapper/OrderMapper-log.%u.%g.txt",
                   1024 * 1024, 10);
           logger.addHandler(handler);

           handler.setFormatter(new SimpleFormatter());
       } catch (IOException ex) {
           logger.log(Level.SEVERE, "Error in logger", new IOException("Error: "));

       }
   }

   @Override
   public List<Order> getAll() throws OrderException {
       try(Connection con = dbc.open()){
           List<Order> order = new ArrayList();
           String qry = "SELECT * FROM orderrs;";
           Statement stmt = con.createStatement();
           ResultSet rs = stmt.executeQuery(qry);

           while (rs.next()) {
               order.add(new Order(rs.getInt("id"),
                       rs.getString("email"),
                       rs.getInt("width"),
                       rs.getInt("length"),
                       rs.getInt("shedWidth"),
                       rs.getInt("shedLength"),
                       rs.getString("roof"),
                       rs.getInt("angle")));
           }
           return order;
       } catch (SQLException ex) {
           logger.log(Level.SEVERE, "Error in getAllOrders method", new SQLException("Error: "));
           throw new OrderException("Couldn't access orders from Database");
       }
   }

   @Override
   public Order getById(String username) throws OrderException {
       try(Connection con = dbc.open()){
           String sql = "SELECT * FROM orders where id = ?;";
           PreparedStatement ps = con.prepareStatement(sql);
           ps.setString(1, username);
           ResultSet rs = ps.executeQuery();

           while (rs.next()) {
               return new Order(rs.getInt("id"),
                       rs.getString("email"),
                       rs.getInt("width"),
                       rs.getInt("length"),
                       rs.getInt("shedWidth"),
                       rs.getInt("shedLength"),
                       rs.getString("roof"),
                       rs.getInt("angle"));
           }
       } catch (SQLException ex) {
           logger.log(Level.SEVERE, "Error in getById method", new SQLException("Error: "));
           throw new OrderException("Something went wrong with retrieving the specific order from the database");
       }
       return null;
   }

   public void createOrder(Request request) throws OrderException {
       try(Connection con = dbc.open()){
           String sql = "INSERT INTO orders "
                   + "(width, length, shedWidth, shedLength, roof, angle)"
                   + "VALUES(?,?,?,?,?,?);";
           PreparedStatement ps = con.prepareStatement(sql);
           ps.setInt(1, request.getWidth());
           ps.setInt(2, request.getLength());
           ps.setInt(3, request.getShedWidth());
           ps.setInt(4, request.getShedLength());
           ps.setString(5, request.getRoof());
           ps.setInt(6, request.getAngle());

           ps.executeUpdate();

       } catch (SQLException ex) {
           logger.log(Level.SEVERE, "Error in createOrder method", new SQLException("Error: "));
           throw new OrderException("Couldn't create Order");
       }
   }
   
   public List<Order> getAllByUser(User user) throws OrderException{
        try(Connection con = dbc.open()){
            List<Order> orders = new ArrayList();
            String qry = "SELECT * FROM orders WHERE username = ?;";
            PreparedStatement ps = con.prepareStatement(qry);
            ps.setString(1, user.getUsername());
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                orders.add(new Order(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getInt("width"),
                        rs.getInt("length"),
                        rs.getInt("shedWidth"),
                        rs.getInt("shedLength"),
                        rs.getString("roof"),
                        rs.getInt("angle")));
                
            }
            return orders;
        }catch(SQLException ex){
            ex.printStackTrace();
            throw new OrderException("Couldn't access orders from Database");
        }
    }
}
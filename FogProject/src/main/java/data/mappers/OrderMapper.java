package data.mappers;

import data.DBConnector;
import data.exceptions.OrderException;
import data.exceptions.RequestExceptions;
import data.interfaces.MapperInterface;
import data.models.Order;
import data.models.Request;
import data.models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asger Hermind Sørensen
 */
public class OrderMapper implements MapperInterface<Order, Integer> {

    @Override
    public List<Order> getAll() throws OrderException {
        try(Connection con = DBConnector.getConnection()){
            List<Order> orders = new ArrayList();
            String qry = "SELECT * FROM orders;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(qry);
            
            while(rs.next()){
                Order o = new Order(rs.getString("email"),
                        rs.getInt("width"),
                        rs.getInt("length"),
                        rs.getInt("shedWidth"),
                        rs.getInt("shedLength"),
                        rs.getString("roof"),
                        rs.getInt("angle"));
                o.setId(rs.getInt("id"));
                orders.add(o);
            }
            return orders;
        }catch(SQLException ex){
            ex.printStackTrace();
            throw new OrderException("Couldn't access orders from Database");
        }
    }

    @Override
    public Order getById(Integer id) throws OrderException {
        try(Connection con = DBConnector.getConnection()){
            String sql = "SELECT * FROM orders where id = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Order o = new Order(rs.getString("email"),
                rs.getInt("width"),
                rs.getInt("length"),
                rs.getInt("shedWidth"),
                rs.getInt("shedLength"),
                rs.getString("roof"),
                rs.getInt("angle"));
                o.setId(rs.getInt("id"));
                return o;
                
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            throw new OrderException("Something went wrong with retrieving the specific order from the database");
        }
        return null;
    }

    public void createOrder(Request request) throws OrderException {
        try(Connection con = DBConnector.getConnection()){
            String sql = "INSERT INTO orders "
                    + "(email, width, length, shedWidth, shedLength, roof, angle)"
                    + "VALUES(?,?,?,?,?,?,?);";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, request.getEmail());
            ps.setInt(2, request.getWidth());
            ps.setInt(3, request.getLength());
            ps.setInt(4, request.getShedWidth());
            ps.setInt(5, request.getShedLength());
            ps.setString(6, request.getRoof());
            ps.setInt(7, request.getAngle());
            
            ps.executeUpdate();
            
        }catch(SQLException ex){
            ex.printStackTrace();
            throw new OrderException("Couldn't create Order");
        }
    }
    
    public List<Order> getAllByUser(User user) throws OrderException{
        try(Connection con = DBConnector.getConnection()){
            List<Order> orders = new ArrayList();
            String qry = "SELECT * FROM orders WHERE username = ?;";
            PreparedStatement ps = con.prepareStatement(qry);
            ps.setString(1, user.getUsername());
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Order o = new Order(rs.getString("email"),
                        rs.getInt("width"),
                        rs.getInt("length"),
                        rs.getInt("shedWidth"),
                        rs.getInt("shedLength"),
                        rs.getString("roof"),
                        rs.getInt("angle"));
                o.setId(rs.getInt("id"));
                orders.add(o);
                
            }
            return orders;
        }catch(SQLException ex){
            ex.printStackTrace();
            throw new OrderException("Couldn't access orders from Database");
        }
    }

    
    public static void main(String[] args) throws RequestExceptions, OrderException {
        OrderMapper om = new OrderMapper();
        RequestMapper rm = new RequestMapper();
        //Request r = new Request(2000, 2000, 2400, 2400, "flat", 200, "Admin", "test", "test", "test", "Admin@Somewhere.dk", "");
        //new Request(0, 0, 0, 0, roof, 0, name, address, zipCity, phone, email, note)
        Request rr = rm.getById(12);
        System.out.println(rr.toString());
        om.createOrder(rr);
        //System.out.println(om.getById(10).getLength());
        
        
        
    }    
    
    
    
    
}

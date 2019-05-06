package data.mappers;

import data.DBConnector;
import data.exceptions.OrderException;
import data.interfaces.OrderMapperInterface;
import data.models.Order;
import data.models.Request;
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
public class OrderMapper implements OrderMapperInterface {

    @Override
    public List<Order> getAllOrders() throws OrderException {
        try(Connection con = DBConnector.getConnection()){
            List<Order> order = new ArrayList();
            String qry = "SELECT * FROM orders;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(qry);
            
            while(rs.next()){
                order.add(new Order(rs.getInt("width"),
                        rs.getInt("length"),
                        rs.getInt("shedWidth"),
                        rs.getInt("shedLength"),
                        rs.getString("roof"),
                        rs.getInt("angle")));
            }
            return order;
        }catch(SQLException ex){
            ex.printStackTrace();
            throw new OrderException("Couldn't access orders from Database");
        }
    }

    @Override
    public Order getById(int id) throws OrderException {
        try(Connection con = DBConnector.getConnection()){
            String sql = "SELECT * FROM orders where id = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                return new Order(rs.getInt("width"),
                rs.getInt("length"),
                rs.getInt("shedWidth"),
                rs.getInt("shedLength"),
                rs.getString("roof"),
                rs.getInt("angle"));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            throw new OrderException("Something went wrong with retrieving the specific order from the database");
        }
        return null;
    }

    @Override
    public void createOrder(Request request) throws OrderException {
        try(Connection con = DBConnector.getConnection()){
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
            
        }catch(SQLException ex){
            ex.printStackTrace();
            throw new OrderException("Couldn't create Order");
        }
    }

    
    
    
    
    
}

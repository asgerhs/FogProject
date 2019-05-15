package data.mappers;

import data.DBConnector;
import data.exceptions.OrderException;
import data.exceptions.RequestExceptions;
import data.interfaces.OrderMapperInterface;
import data.models.Order;
import data.models.Request;
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

/**
 *
 * @author Asger Hermind Sørensen
 */
public class OrderMapper implements OrderMapperInterface {

    private static Logger logger = Logger.getLogger(MaterialMapper.class.getName());

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
    public List<Order> getAllOrders() throws OrderException {
        try (Connection con = DBConnector.getConnection()) {
            List<Order> order = new ArrayList();
            String qry = "SELECT * FROM orderrs;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(qry);

            while (rs.next()) {
                order.add(new Order(rs.getInt("width"),
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
    public Order getById(int id) throws OrderException {
        try (Connection con = DBConnector.getConnection()) {
            String sql = "SELECT * FROM orderrs where id = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return new Order(rs.getInt("width"),
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

    @Override
    public void createOrder(Request request) throws OrderException {
        try (Connection con = DBConnector.getConnection()) {
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

    public static void main(String[] args) throws RequestExceptions, OrderException {
        OrderMapper om = new OrderMapper();
        RequestMapper rm = new RequestMapper();
        om.createOrder(rm.getById(1));
    }

}

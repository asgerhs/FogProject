package data.mappers;

import data.DatabaseConnector;
import data.exceptions.OrderException;
import data.interfaces.MapperInterface;
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
import javax.sql.DataSource;

/**
 *
 * @author Asger Hermind Sørensen
 */
public class OrderMapper implements MapperInterface<Order, Integer> {

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
                order.add(new Order(rs.getInt("width"),
                        rs.getInt("length"),
                        rs.getInt("shedWidth"),
                        rs.getInt("shedLength"),
                        rs.getString("roof"),
                        rs.getInt("angle"));
                o.setId(rs.getInt("id"));
                orders.add(o);
            }
            throw new OrderException("Couldn't access orders from Database");
        }
    }

    @Override
            String sql = "SELECT * FROM orders where id = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return order;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error in getAllOrders method", new SQLException("Error: "));
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
                    + "(email, width, length, shedWidth, shedLength, roof, angle)"
                    + "VALUES(?,?,?,?,?,?,?);";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error in createOrder method", new SQLException("Error: "));
            throw new OrderException("Couldn't create Order");
        }
    }
    public Order getById(String username) throws OrderException {
        try(Connection con = dbc.open()){
}

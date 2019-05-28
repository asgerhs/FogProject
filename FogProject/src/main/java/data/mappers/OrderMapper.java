package data.mappers;

import data.DatabaseConnector;
import data.ExceptionLogger;
import data.exceptions.OrderException;
import data.exceptions.RequestException;
import data.interfaces.MapperInterface;
import data.models.LoggerEnum;
import data.models.Order;
import data.models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author Asger Hermind Sørensen & Martin Frederiksen
 */
public class OrderMapper implements MapperInterface<Order, Integer> {

    DatabaseConnector dbc = new DatabaseConnector();
    RequestMapper requestMapper;

    public OrderMapper(DataSource ds) {
        requestMapper = new RequestMapper(ds);
        dbc.setDataSource(ds);
    }

    /**
     * Returns all materials from the database
     * @return List of Orders
     * @throws OrderException 
     */
    @Override
    public List<Order> getAll() throws OrderException {
        try (Connection con = dbc.open()) {
            List<Order> order = new ArrayList();
            String qry = "SELECT * FROM orders;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(qry);

            while (rs.next()) {
                order.add(new Order(rs.getInt("id"),
                        requestMapper.getSingle(rs.getInt("requestId"))));
            }
            return order;
        } catch (SQLException | RequestException ex) {
            ex.printStackTrace();
            ExceptionLogger.log(LoggerEnum.ORDERMAPPER, "Error in getAll Method: \n" + ex.getMessage(), ex.getStackTrace());
            throw new OrderException("Couldn't access orders from Database");
        }
    }

    /**
     * Returns specific material based on id from Database
     * @param id, specific order id
     * @return Single Order
     * @throws OrderException 
     */
    @Override
    public Order getSingle(Integer id) throws OrderException {
        try (Connection con = dbc.open()) {
            String sql = "SELECT * FROM orders where id = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return new Order(rs.getInt("id"),
                        requestMapper.getSingle(rs.getInt("requestId")));
            }
        } catch (SQLException | RequestException ex) {
            ex.printStackTrace();
            ExceptionLogger.log(LoggerEnum.ORDERMAPPER, "Error in getSingle Method: \n" + ex.getMessage(), ex.getStackTrace());
            throw new OrderException("Something went wrong with retrieving the specific order from the database");
        }
        return null;
    }

    /**
     * Adds new order to Database
     * @param order, Order to be added
     * @throws OrderException 
     */
    @Override
    public void add(Order order) throws OrderException {
        try (Connection con = dbc.open()) {
            String sql = "INSERT INTO orders "
                    + "(requestId)"
                    + "VALUES(?);";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, order.getRequest().getId());

            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            ExceptionLogger.log(LoggerEnum.ORDERMAPPER, "Error in add Method: \n" + ex.getMessage(), ex.getStackTrace());
            throw new OrderException("Couldn't create Order");
        }
    }

    /**
     * Gets all 
     * @param user
     * @return
     * @throws OrderException 
     */
    public List<Order> getAllByUser(User user) throws OrderException {
        try (Connection con = dbc.open()) {
            List<Order> orders = new ArrayList();
            String qry = "SELECT * FROM orders JOIN requests ON requestId = requests.id WHERE requests.email = ?";
            PreparedStatement ps = con.prepareStatement(qry);
            ps.setString(1, user.getEmail());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                orders.add(new Order(rs.getInt("id"),
                        requestMapper.getSingle(rs.getInt("requestId"))));
            }
            return orders;
        } catch (SQLException | RequestException ex) {
            ex.printStackTrace();
            ExceptionLogger.log(LoggerEnum.ORDERMAPPER, "Error in getAllByUser Method: \n" + ex.getMessage(), ex.getStackTrace());
            throw new OrderException("Couldn't access orders from Database");
        }
    }
}

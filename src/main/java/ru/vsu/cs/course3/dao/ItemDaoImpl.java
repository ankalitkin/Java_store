package ru.vsu.cs.course3.dao;

import ru.vsu.cs.course3.domain.Item;
import ru.vsu.cs.course3.exception.ApplicationException;
import ru.vsu.cs.course3.provider.ServiceProvider;
import ru.vsu.cs.course3.utils.Connector;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl implements ItemDao {
    public static final String GET_QUERY = "SELECT id, name, cost, department_id FROM item";

    public static final String GET_BY_ID_QUERY = "SELECT id, name, cost, department_id FROM item WHERE id  = ?";

    public static final String GET_BY_DEPARTMENT_ID_QUERY = "SELECT id, name, cost, department_id FROM item " +
            "WHERE department_id  = ?";

    public static final String INSERT_QUERY = "INSERT INTO item(name, cost, department_id) VALUES(?, ?, ?)";

    public static final String UPDATE_QUERY = "UPDATE item SET name = ?, cost = ?, department_id = ? WHERE id = ?";

    public static final String DELETE_QUERY = "DELETE FROM item WHERE id = ?";

    private final Connector connector = ServiceProvider.get(Connector.class);

    @Override
    public Item findById(int id) {
        try (PreparedStatement statement = connector.getConnection().prepareStatement(GET_BY_ID_QUERY)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    BigDecimal cost = rs.getBigDecimal("cost");
                    int departmentId = rs.getInt("department_id");
                    return new Item(id, name, cost, departmentId);
                }
                return null;
            }
        } catch (SQLException e) {
            throw new ApplicationException(e);
        }
    }

    @Override
    public List<Item> findAll() {
        try (PreparedStatement statement = connector.getConnection().prepareStatement(GET_QUERY)) {
            try (ResultSet rs = statement.executeQuery()) {
                List<Item> items = new ArrayList<>();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    BigDecimal cost = rs.getBigDecimal("cost");
                    int departmentId = rs.getInt("department_id");
                    items.add(new Item(id, name, cost, departmentId));
                }
                return items;
            }
        } catch (SQLException e) {
            throw new ApplicationException(e);
        }
    }

    @Override
    public List<Item> findAllByDepartmentId(int departmentId) {
        try (PreparedStatement statement = connector.getConnection().prepareStatement(GET_BY_DEPARTMENT_ID_QUERY)) {
            statement.setInt(1, departmentId);
            try (ResultSet rs = statement.executeQuery()) {
                List<Item> items = new ArrayList<>();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    BigDecimal cost = rs.getBigDecimal("cost");
                    items.add(new Item(id, name, cost, departmentId));
                }
                return items;
            }
        } catch (SQLException e) {
            throw new ApplicationException(e);
        }
    }

    @Override
    public void update(Item entity) {
        try (PreparedStatement statement = connector.getConnection().prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, entity.getName());
            statement.setBigDecimal(2, entity.getCost());
            statement.setInt(3, entity.getDepartmentId());
            statement.setInt(4, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ApplicationException(e);
        }
    }

    @Override
    public Item insert(Item entity) {
        String name = entity.getName();
        BigDecimal cost = entity.getCost();
        int departmentId = entity.getDepartmentId();

        try (PreparedStatement statement = connector.getConnection()
                .prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getName());
            statement.setBigDecimal(2, entity.getCost());
            statement.setInt(3, entity.getDepartmentId());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                generatedKeys.next();
                int id = generatedKeys.getInt(1);
                return new Item(id, name, cost, departmentId);
            }
        } catch (SQLException e) {
            throw new ApplicationException(e);
        }
    }

    @Override
    public boolean deleteById(int id) {
        try (PreparedStatement statement = connector.getConnection().prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new ApplicationException(e);
        }
    }
}

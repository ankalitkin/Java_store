package ru.vsu.cs.course3.dao;

import ru.vsu.cs.course3.domain.Department;
import ru.vsu.cs.course3.exception.ApplicationException;
import ru.vsu.cs.course3.provider.ServiceProvider;
import ru.vsu.cs.course3.utils.Connector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoImpl implements DepartmentDao {
    public static final String GET_QUERY = "SELECT id, name, working_hours FROM department";

    public static final String GET_BY_ID_QUERY = "SELECT id, name, working_hours FROM department WHERE id  = ?";

    public static final String INSERT_QUERY = "INSERT INTO department(name, working_hours) VALUES(?, ?)";

    public static final String UPDATE_QUERY = "UPDATE department SET name = ?, working_hours = ? WHERE id = ?";

    public static final String DELETE_QUERY = "DELETE FROM department WHERE id = ?";

    private final Connector connector = ServiceProvider.get(Connector.class);

    @Override
    public Department findById(int id) {
        try (PreparedStatement statement = connector.getConnection().prepareStatement(GET_BY_ID_QUERY)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    String workingHours = rs.getString("working_hours");
                    return new Department(id, name, workingHours);
                }
                return null;
            }
        } catch (SQLException e) {
            throw new ApplicationException(e);
        }
    }

    @Override
    public List<Department> findAll() {
        try (PreparedStatement statement = connector.getConnection().prepareStatement(GET_QUERY)) {
            try (ResultSet rs = statement.executeQuery()) {
                List<Department> departments = new ArrayList<>();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String workingHours = rs.getString("working_hours");
                    departments.add(new Department(id, name, workingHours));
                }
                return departments;
            }
        } catch (SQLException e) {
            throw new ApplicationException(e);
        }
    }

    @Override
    public void update(Department entity) {
        try (PreparedStatement statement = connector.getConnection().prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getWorkingHours());
            statement.setInt(3, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ApplicationException(e);
        }
    }

    @Override
    public Department insert(Department entity) {
        String name = entity.getName();
        String workingHours = entity.getWorkingHours();

        try (PreparedStatement statement = connector.getConnection()
                .prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, name);
            statement.setString(2, workingHours);
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                generatedKeys.next();
                int id = generatedKeys.getInt(1);
                return new Department(id, name, workingHours);
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

package com.kai.TaskManager;

import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskDAO{

    private DataSource dataSource;

    public TaskDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Task save(Task task) {
        String query = "INSERT INTO taskManager (name, description, completed, priority, " +
                "dueDate) VALUES (?,?,?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query,
                     Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, task.getName());
            ps.setString(2, task.getDescription());
            ps.setBoolean(3, task.isCompleted());

            if (task.getPriority() != null) {
                ps.setString(4, task.getPriority().name());
            } else {
                ps.setNull(4, Types.VARCHAR);
            }

            if (task.getDueDate() != null ) {
                ps.setTimestamp(5, task.getDueDate());
            } else {
                ps.setNull(5, Types.TIMESTAMP);
            }

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                task.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.getMessage();
        } return task;
    }

    public void delete(String name) {
        String query = "DELETE FROM taskManager WHERE name = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, name);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public Task findTaskByName(String name) {
        String query = "SELECT * FROM taskManager WHERE name = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, name);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Task task = new Task();
                    task.setName(rs.getString("name"));
                    task.setDescription(rs.getString("description"));
                    task.setCompleted(rs.getBoolean("completed"));

                    String priorityString = rs.getString("priority");
                    if (priorityString != null) {
                        task.setPriority(Task.PriorityLevel.valueOf(priorityString));
                    } else {
                        task.setPriority(null);
                    }

                    Timestamp dueDate = rs.getTimestamp("dueDate");
                    task.setDueDate(rs.getTimestamp("dueDate"));
                    return task;
                }
            }
        } catch (SQLException e) {
            e.getMessage();
        } return null;
    }

    public List<Task> findAllTasks() {
        String query = "SELECT * FROM taskManager";
        List<Task> tasks = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Task task = new Task();
                task.setName(rs.getString("name"));
                task.setDescription(rs.getString("description"));
                task.setCompleted(rs.getBoolean("completed"));

                String priorityString = rs.getString("priority");
                if (priorityString != null) {
                    task.setPriority(Task.PriorityLevel.valueOf(priorityString));
                } else {
                    task.setPriority(null);
                }

                task.setDueDate(rs.getTimestamp("dueDate"));
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.getMessage();
        } return tasks;
    }


    public Task update(Task task) {
        String query = "UPDATE taskManager SET name = ?, description = ?, completed = ?, " +
                "priority = ?, dueDate = ? WHERE name = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, task.getName());
            ps.setString(2, task.getDescription());
            ps.setBoolean(3, task.isCompleted());

            if (task.getPriority() != null) {
                ps.setString(4, task.getPriority().name());
            } else {
                ps.setNull(4, Types.VARCHAR);
            }

            if (task.getDueDate() != null) {
                ps.setTimestamp(5,task.getDueDate());
            } else {
                ps.setNull(5, Types.TIMESTAMP);
            }

            ps.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
        return task;
    }
}

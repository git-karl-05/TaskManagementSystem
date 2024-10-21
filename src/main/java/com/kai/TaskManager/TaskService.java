package com.kai.TaskManager;

import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
//TaskDetailsDTO Service -> TaskDetailsDTO DAO
public class TaskService {

    private DataSource dataSource;

    public TaskService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Task save(Task task) {
        String query = "INSERT INTO taskManager (name, description, completed) VALUES (?,?," +
                "?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query,
                     Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, task.getName());
            ps.setString(2, task.getDescription());
            ps.setBoolean(3, task.isCompleted());

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
                    task.setId(rs.getLong("id"));
                    task.setName(rs.getString("name"));
                    task.setDescription(rs.getString("description"));
                    task.setCompleted(rs.getBoolean("completed"));
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
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.getMessage();
        } return tasks;
    }

    public void update(Task task) {
        String query = "UPDATE taskManager SET name = ?, description = ?, completed = ? " +
                "WHERE name = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, task.getName());
            ps.setString(2, task.getDescription());
            ps.setBoolean(3, task.isCompleted());
            ps.setLong(4, task.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }
}
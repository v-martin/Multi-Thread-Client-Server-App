package managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;

public class ConnectionManager {
    Queue<Connection> connectionQueue = new LinkedList<>();
    int CONNECTION_POOL_SIZE = 10;

    public int getSize() {
        return connectionQueue.size();
    }

    public void init() {
        while (connectionQueue.size() < CONNECTION_POOL_SIZE) {
            connectionQueue.add(connect());
        }
    }

    private Connection connect() {
//        String url = "jdbc:postgresql://pg:5432/studs";
        String url = "jdbc:postgresql://localhost:5432/lab7";
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, "postgres", "martiseva2003");
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Connection ERROR");
            return null;
        }
    }

    public Connection getConnection() {
        return connectionQueue.poll();
    }

    public void addConnection(Connection connection) {
        connectionQueue.add(connection);
    }
}

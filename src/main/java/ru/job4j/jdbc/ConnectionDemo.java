package ru.job4j.jdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class ConnectionDemo {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public ConnectionDemo(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            String stringText;
            while ((stringText = reader.readLine()) != null) {
                boolean noEmptyLineAndComments = !stringText.isEmpty() && !stringText.startsWith("#");
                if (noEmptyLineAndComments
                        && (!stringText.contains("=")
                        || stringText.startsWith("=")
                        || stringText.endsWith("="))) {
                    throw new IllegalArgumentException();
                } else if (noEmptyLineAndComments) {
                    values.put(stringText.substring(0, stringText.indexOf("=")),
                            stringText.substring(stringText.indexOf("=") + 1));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ConnectionDemo conf = new ConnectionDemo("data/app.properties");
        conf.load();
        Class.forName(conf.value("main.connection.driver_class"));
        String url = conf.value("main.connection.url");
        String login = conf.value("main.connection.username");
        String password = conf.value("main.connection.password");
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }

}
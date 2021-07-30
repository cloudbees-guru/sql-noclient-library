package com.cloudbees.cd.plugin.sql.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SQLConnection implements DatabaseConnection {
  private String login;
  private String password;
  private String jdbcUrl;

  SQLConnection(String server, String port, String databaseName, String properties, String jdbcUrlPattern, String login, String password) {
    validateParameters(server, port, databaseName);

    Pattern pattern = Pattern.compile("\\[(.+?)\\]");
    Matcher matcher = pattern.matcher(jdbcUrlPattern);
    StringBuffer result = new StringBuffer();

    while (matcher.find()) {
      matcher.appendReplacement(result, "");
      if ("server".equalsIgnoreCase(matcher.group(1))) {
        result.append(server);
      } else if ("port".equalsIgnoreCase(matcher.group(1))) {
        result.append(port);
      } else if ("databaseName".equalsIgnoreCase(matcher.group(1))) {
        result.append(databaseName);
      } else if ("properties".equalsIgnoreCase(matcher.group(1))) {
        if (properties != null && properties.length() != 0) {
          result.append("?");
          result.append(properties);
        }
      }
    }
    matcher.appendTail(result);
    this.jdbcUrl = result.toString();
    this.login = login;
    this.password = password;
  }

  public Connection connect(boolean autoCommit) throws Exception {
    Connection connection = null;

    try {
      connection = DriverManager.getConnection(jdbcUrl, login, password);
      connection.setAutoCommit(autoCommit);
    } catch (SQLException e) {
      e.printStackTrace();
      throw new Exception("Could not connect to database: " + e.getMessage(), e);
    }

    return connection;
  }

  public void disconnect(Connection con) throws Exception {
    try {
      if (con != null) {
        con.close();
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
      throw new Exception("Could not close connection to database: " + ex.getMessage(), ex);
    }
  }

  public String getJDBCUrl() {
    return this.jdbcUrl;
  }

  private void validateParameters(String server, String port, String databaseName) {
    if (server == null || server.length() == 0) {
      throw new IllegalArgumentException("'server' should not be null or empty");
    }
    if (port == null || port.length() == 0) {
      throw new IllegalArgumentException("'port' should not be null or empty");
    }
    if (databaseName == null || databaseName.length() == 0) {
      throw new IllegalArgumentException("'databaseName' should not be null or empty");
    }
  }
}

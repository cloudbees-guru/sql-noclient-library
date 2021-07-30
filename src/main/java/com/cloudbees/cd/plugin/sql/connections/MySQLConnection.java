package com.cloudbees.cd.plugin.sql.connections;

import java.sql.Connection;

public class MySQLConnection extends SQLConnection {
  private static final String jdbcUrlPattern = "jdbc:mysql://[server]:[port]/[databaseName][properties]";

  public MySQLConnection(String server, String port, String databaseName, String properties, String login, String password) {
    super(server, port, databaseName, properties, jdbcUrlPattern, login, password);
  }

  public String getJDBCUrl() {
    return super.getJDBCUrl();
  }
}

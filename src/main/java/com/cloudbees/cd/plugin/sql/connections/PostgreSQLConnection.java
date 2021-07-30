package com.cloudbees.cd.plugin.sql.connections;

public class PostgreSQLConnection extends SQLConnection {
  private static final String jdbcUrlPattern = "jdbc:postgresql://[server]:[port]/[databaseName][properties]";

  public PostgreSQLConnection(String server, String port, String databaseName, String properties, String login, String password) {
    super(server, port, databaseName, properties, jdbcUrlPattern, login, password);
  }

  public String getJDBCUrl() {
    return super.getJDBCUrl();
  }
}

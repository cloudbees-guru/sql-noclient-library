package com.cloudbees.cd.plugin.sql.connections;

public class OracleConnection extends SQLConnection {
  private static final String jdbcUrlPattern = "jdbc:oracle:thin:@[server]:[port]:[databaseName]";

  public OracleConnection(String server, String port, String databaseName, String properties, String login, String password) {
    super(server, port, databaseName, properties, jdbcUrlPattern, login, password);
  }

  public String getJDBCUrl() {
    return super.getJDBCUrl();
  }
}

package com.cloudbees.cd.plugin.sql.connections;

public class HSQLDBConnection extends SQLConnection {
  private static final String jdbcUrlPattern = "jdbc:hsqldb:mem:databaseName]";

  public HSQLDBConnection(String server, String port, String databaseName, String properties, String login, String password) {
    super(server, port, databaseName, properties, jdbcUrlPattern, login, password);
  }

  public String getJDBCUrl() {
    return super.getJDBCUrl();
  }
}

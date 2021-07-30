package com.cloudbees.cd.plugin.sql.connections;

public class MicrosoftSQLConnection extends SQLConnection {
  private static final String jdbcUrlPattern = "jdbc:sqlserver://[server]:[port];[databaseName]";

  public MicrosoftSQLConnection(String server, String port, String databaseName, String properties, String login, String password) {
    super(server, port, "databaseName="+databaseName, properties, jdbcUrlPattern, login, password);
  }

  public String getJDBCUrl() {
    return super.getJDBCUrl();
  }
}

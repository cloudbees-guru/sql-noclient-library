package com.cloudbees.cd.plugin.sql;

import com.cloudbees.cd.plugin.sql.connections.*;

public class DatabaseSelector {

  public DatabaseConnection getDatabaseConnection(String database, String server, String port, String databaseName, String properties, String login, String password) throws Exception {
    if ("ORACLE".equalsIgnoreCase(database)) {
      return new OracleConnection(server, port, databaseName, properties, login, password);
    } else if ("MSSQL".equalsIgnoreCase(database)) {
      return new MicrosoftSQLConnection(server, port, databaseName, properties, login, password);
    } else if ("MYSQL".equalsIgnoreCase(database)) {
      return new MySQLConnection(server, port, databaseName, properties, login, password);
    }  else if ("POSTGRESQL".equalsIgnoreCase(database)) {
      return new PostgreSQLConnection(server, port, databaseName, properties, login, password);
    } else {
      throw new Exception("Database not found in supported databases: " + database);
    }
  }

}

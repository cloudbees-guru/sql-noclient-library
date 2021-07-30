package com.cloudbees.cd.plugin.sql.connections;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SQLConnectionTest {
  private final String server = "server1.cloudbees.com";
  private final String port = "1234";
  private final String databaseName = "cd_database";
  private final String properties = "useSSL=false&serverTimezone=UTC";

  @Test
  void connect() throws Exception {
    SQLConnection sqlConnection = new SQLConnection(server, port, databaseName, properties, "jdbc:sqlserver://[server]:[port];[databaseName]", "login", "password");
    try {
      Connection connection = sqlConnection.connect(false);
    } catch (Exception e) {
      assertThat(e).getCause().isInstanceOf(SQLException.class);
      assertThat(e).hasMessageContaining("Could not connect to database:");
    }
  }

}
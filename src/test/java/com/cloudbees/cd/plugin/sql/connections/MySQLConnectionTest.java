package com.cloudbees.cd.plugin.sql.connections;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MySQLConnectionTest {
  private final String server = "server1.cloudbees.com";
  private final String port = "1234";
  private final String databaseName = "cd_database";
  private final String properties = "useSSL=false&serverTimezone=UTC";

  @Test
  void getJDBCUrl_basic_url() {
    MySQLConnection tested = new MySQLConnection(server, port, databaseName, properties, null, null);

    String result = tested.getJDBCUrl();
    assertThat(result).isEqualToIgnoringCase("jdbc:mysql://server1.cloudbees.com:1234/cd_database?useSSL=false&serverTimezone=UTC");
  }

  @Test
  void getJDBCUrl_noProperties() {
    MySQLConnection tested = new MySQLConnection(server, port, databaseName, null, null, null);

    String result = tested.getJDBCUrl();
    assertThat(result).isEqualToIgnoringCase("jdbc:mysql://server1.cloudbees.com:1234/cd_database");
  }

  @Test
  void getJDBCUrl_server_notNull() {
    try {
      new MySQLConnection(null, port, databaseName, properties, null, null);
    } catch (IllegalArgumentException iae) {
      assertThat(iae).hasMessage("'server' should not be null or empty");
    }
  }

  @Test
  void getJDBCUrl_server_notEmpty() {
    try {
      new MySQLConnection("", port, databaseName, properties, null, null);
    } catch (IllegalArgumentException iae) {
      assertThat(iae).hasMessage("'server' should not be null or empty");
    }
  }

  @Test
  void getJDBCUrl_port_notNull() {
    try {
      new MySQLConnection(server, null, databaseName, properties, null, null);
    } catch (IllegalArgumentException iae) {
      assertThat(iae).hasMessage("'port' should not be null or empty");
    }
  }

  @Test
  void getJDBCUrl_port_notEmpty() {
    try {
      new MySQLConnection(server, "", databaseName, properties, null, null);
    } catch (IllegalArgumentException iae) {
      assertThat(iae).hasMessage("'port' should not be null or empty");
    }
  }

  @Test
  void getJDBCUrl_database_notNull() {
    try {
      new MySQLConnection(server, port, null, properties, null, null);
    } catch (IllegalArgumentException iae) {
      assertThat(iae).hasMessage("'databaseName' should not be null or empty");
    }
  }

  @Test
  void getJDBCUrl_database_notEmpty() {
    try {
      new MySQLConnection(server, port, "", properties, null, null);
    } catch (IllegalArgumentException iae) {
      assertThat(iae).hasMessage("'databaseName' should not be null or empty");
    }
  }

}
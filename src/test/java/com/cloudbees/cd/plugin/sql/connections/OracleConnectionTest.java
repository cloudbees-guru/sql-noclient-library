package com.cloudbees.cd.plugin.sql.connections;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OracleConnectionTest {
  private final String server = "server1.cloudbees.com";
  private final String port = "1234";
  private final String databaseName = "cd_database";
  private final String properties = "useSSL=false&serverTimezone=UTC";

  @Test
  void getJDBCUrl_basic_url() {
    OracleConnection tested = new OracleConnection(server, port, databaseName, properties, null, null);

    String result = tested.getJDBCUrl();
    assertThat(result).isEqualToIgnoringCase("jdbc:oracle:thin:@server1.cloudbees.com:1234:cd_database");
  }

  @Test
  void getJDBCUrl_noProperties() {
    OracleConnection tested = new OracleConnection(server, port, databaseName, null, null, null);

    String result = tested.getJDBCUrl();
    assertThat(result).isEqualToIgnoringCase("jdbc:oracle:thin:@server1.cloudbees.com:1234:cd_database");
  }

  @Test
  void getJDBCUrl_server_notNull() {
    try {
      new OracleConnection(null, port, databaseName, properties, null, null);
    } catch (IllegalArgumentException iae) {
      assertThat(iae).hasMessage("'server' should not be null or empty");
    }
  }

  @Test
  void getJDBCUrl_server_notEmpty() {
    try {
      new OracleConnection("", port, databaseName, properties, null, null);
    } catch (IllegalArgumentException iae) {
      assertThat(iae).hasMessage("'server' should not be null or empty");
    }
  }

  @Test
  void getJDBCUrl_port_notNull() {
    try {
      new OracleConnection(server, null, databaseName, properties, null, null);
    } catch (IllegalArgumentException iae) {
      assertThat(iae).hasMessage("'port' should not be null or empty");
    }
  }

  @Test
  void getJDBCUrl_port_notEmpty() {
    try {
      new OracleConnection(server, "", databaseName, properties, null, null);
    } catch (IllegalArgumentException iae) {
      assertThat(iae).hasMessage("'port' should not be null or empty");
    }
  }

  @Test
  void getJDBCUrl_database_notNull() {
    try {
      new OracleConnection(server, port, null, properties, null, null);
    } catch (IllegalArgumentException iae) {
      assertThat(iae).hasMessage("'databaseName' should not be null or empty");
    }
  }

  @Test
  void getJDBCUrl_database_notEmpty() {
    try {
      new OracleConnection(server, port, "", properties, null, null);
    } catch (IllegalArgumentException iae) {
      assertThat(iae).hasMessage("'databaseName' should not be null or empty");
    }
  }

}
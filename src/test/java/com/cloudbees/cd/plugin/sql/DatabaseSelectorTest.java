package com.cloudbees.cd.plugin.sql;

import com.cloudbees.cd.plugin.sql.connections.DatabaseConnection;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class DatabaseSelectorTest {
  private DatabaseSelector databaseSelector = new DatabaseSelector();

  @Test
  void testOracle() {
    try {
      DatabaseConnection conn = databaseSelector.getDatabaseConnection("Oracle", "server1", "1234", "dbName", null, "login", "password");
      assertThat(conn).isNotNull();
    } catch (Exception e) {
      fail("Exception should not have been raised");
    }
  }

  @Test
  void testMicrosoftSQL() throws Exception {
    try {
      DatabaseConnection conn = databaseSelector.getDatabaseConnection("MsSQL", "server1", "1234", "dbName", null, "login", "password");
      assertThat(conn).isNotNull();
    } catch (Exception e) {
      fail("Exception should not have been raised");
    }
  }

  @Test
  void testMySQL() throws Exception {
    try {
      DatabaseConnection conn = databaseSelector.getDatabaseConnection("MySQL", "server1", "1234", "dbName", null, "login", "password");
      assertThat(conn).isNotNull();
    } catch (Exception e) {
      fail("Exception should not have been raised");
    }
  }

  @Test
  void testPostgreSQL() throws Exception {
    try {
      DatabaseConnection conn = databaseSelector.getDatabaseConnection("PostgreSQL", "server1", "1234", "dbName", null, "login", "password");
      assertThat(conn).isNotNull();
    } catch (Exception e) {
      fail("Exception should not have been raised");
    }
  }

  @Test
  void test_InvalidDB() throws Exception {
    try {
      DatabaseConnection conn = databaseSelector.getDatabaseConnection("TCHOUTCHOU", "server1", "1234", "dbName", null, "login", "password");
    } catch (Exception e) {
      assertThat(e).hasMessageContaining("Database not found in supported databases:");
    }
  }

}
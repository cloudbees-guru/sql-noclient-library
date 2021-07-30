package com.cloudbees.cd.plugin.sql;

import com.cloudbees.cd.plugin.sql.connections.DatabaseConnection;
import com.cloudbees.cd.plugin.sql.connections.HSQLDBConnection;
import org.dbunit.JdbcBasedDBTestCase;
import org.dbunit.dataset.IDataSet;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class QueryExecutorTest extends JdbcBasedDBTestCase {


  @Test
  public void testExecuteQuery() throws Exception {
    DatabaseConnection connection = new HSQLDBConnection("server1", "1234", "cdunittests", null, null, null);
    QueryExecutor queryExecutor = new QueryExecutor(connection);

    queryExecutor.executeQuery("SELECT CURRENT_DATE AS today, CURRENT_TIME AS now FROM (VALUES(0))", false);
  }

  @Test
  public void testExecuteQuery_WrongQuery() throws Exception {
    DatabaseConnection connection = new HSQLDBConnection("server1", "1234", "cdunittests", null, null, null);
    QueryExecutor queryExecutor = new QueryExecutor(connection);

    try {
      queryExecutor.executeQuery("SELECT BLABLABLABLA FROM DUAL", false);
    } catch (Exception e) {
      assertThat(e).getCause().isInstanceOf(SQLException.class);
      assertThat(e).hasMessageContaining("Error while executing the queries:");
    }
  }

  @Test
  public void testExecuteQueries() throws Exception {
    DatabaseConnection connection = new HSQLDBConnection("server1", "1234", "cdunittests", null, null, null);
    QueryExecutor queryExecutor = new QueryExecutor(connection);

    queryExecutor.executeQueries("SELECT CURRENT_DATE AS today, CURRENT_TIME AS now FROM (VALUES(0));\nSELECT CURRENT_DATE AS today, CURRENT_TIME AS now FROM (VALUES(0));", false);
  }

  @Test
  public void testExecuteQueries_WrongQueries() throws Exception {
    DatabaseConnection connection = new HSQLDBConnection("server1", "1234", "cdunittests", null, null, null);
    QueryExecutor queryExecutor = new QueryExecutor(connection);

    try {
      queryExecutor.executeQueries("SELECT BLABLABLABLA FROM DUAL;\nSELECT TOTO FROM TITI", false);
    } catch (Exception e) {
      assertThat(e).getCause().isInstanceOf(SQLException.class);
      assertThat(e).hasMessageContaining("Error while executing the queries:");
    }
  }

  @Override
  protected String getConnectionUrl() {
    return "jdbc:hsqldb:mem:cdunittests";
  }

  @Override
  protected String getDriverClass() {
    return "org.hsqldb.jdbcDriver";
  }

  @Override
  protected IDataSet getDataSet() throws Exception {
    return null;
  }
}
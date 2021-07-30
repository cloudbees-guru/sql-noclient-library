package com.cloudbees.cd.plugin.sql;

import com.cloudbees.cd.plugin.sql.connections.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryExecutor {
  private DatabaseConnection dbConnection;
  private Pattern reg = Pattern.compile("^.*;$", Pattern.MULTILINE);

   public QueryExecutor(DatabaseConnection connection) {
     this.dbConnection = connection;
   }

   public void executeQuery(String query, boolean autoCommit) throws Exception {
     System.out.println("Found 1 query to execute:\n" + query);
     Connection connection = null;
     try {
       connection = dbConnection.connect(autoCommit);
       Statement st = connection.createStatement();
       st.execute(query);
       connection.commit();
       st.close();
     } catch (SQLException e) {
       e.printStackTrace();
       throw new Exception("Error while executing the queries: " + e.getMessage(), e);
     } finally {
        dbConnection.disconnect(connection);
     }

   }

  public void executeQueries(String queriesAsString, boolean autoCommit) throws Exception {
    List<String> queries = this.getQueries(queriesAsString);
    System.out.println("Found " + queries.size() + " query(ies) to execute:");
    for (String q : queries) {
      System.out.println(q);
    }

    Connection connection = null;
    try {
      connection = dbConnection.connect(autoCommit);
      Statement st = connection.createStatement();
      for (String q : queries) {
        st.addBatch(q);
      }
      st.executeBatch();
      connection.commit();
      st.close();
    } catch (SQLException e) {
      e.printStackTrace();
      throw new Exception("Error while executing the queries: " + e.getMessage(), e);
    } finally {
      dbConnection.disconnect(connection);
    }
  }

  private List<String> getQueries(String cd_parameter) {
    if (cd_parameter == null) {
      return Collections.emptyList();
    } else {
      List<String> result = new ArrayList<>(1);
      Matcher m = reg.matcher(cd_parameter);
      while(m.find()) {
        result.add(m.group());
      }
      return result;
    }
  }

}

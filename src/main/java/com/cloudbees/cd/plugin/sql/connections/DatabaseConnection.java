package com.cloudbees.cd.plugin.sql.connections;

import java.sql.Connection;

public interface DatabaseConnection {
  Connection connect(boolean autoCommit) throws Exception;
  void disconnect(Connection connection) throws Exception;
}

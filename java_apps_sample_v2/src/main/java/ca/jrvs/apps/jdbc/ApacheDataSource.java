package ca.jrvs.apps.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public class ApacheDataSource {

  private DataSource dataSource;

  public ApacheDataSource(String host, String databaseName, int port, String user,
      String password) {
    BasicDataSource ds = new BasicDataSource();
    ds.setDriverClassName("org.postgresql.Driver");
    ds.setUrl("jdbc:postgresql://" + host + "/" + databaseName);
    ds.setUsername(user);
    ds.setPassword(password);
    dataSource = ds;
  }

  public Connection getConnection() throws SQLException {
    return dataSource.getConnection();
  }


}

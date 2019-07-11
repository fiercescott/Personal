package ca.jrvs.apps.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCExecutor {

  public static void main(String[] args) {
/*    DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost", "hplussport",
        "postgres", "password");
    try {
      Connection connection = dcm.getConnection();
      CustomerDAO customerDAO = new CustomerDAO(connection);
      Customer customer = new Customer();
      customer.setFirstName("George");
      customer.setLastName("Washington");
      customer.setEmail("george.washinton@wh.gov");
      customer.setPhone("555-555-5555");
      customer.setAddress("123 Main St");
      customer.setCity("Mount Vernon");
      customer.setState("VA");
      customer.setZipCode("22121");

      customerDAO.create(customer);

      customer = customerDAO.findById(1000);
      System.out.println(customer.getFirstName());

      customer = customerDAO.findById(1000);
      System.out.println(JsonUtil.toPrettyJson(customer));

      customer.setEmail("abc@grmail.com");
      customer = customerDAO.update(customer);
      System.out.println(JsonUtil.toPrettyJson(customer));

      customerDAO.delete(1001);

      System.out.println(customerDAO.findById(1001));

      OrderDao orderDao = new OrderDao(connection);
      Order order = orderDao.findById(1000);
      System.out.println(JsonUtil.toPrettyJson(order));*/

/*    try {
      ApacheDataSource dataSource = new ApacheDataSource("localhost", "hplussport", 5432,
          "postgres", "password");
      CustomerDAO dao = new CustomerDAO(dataSource.getConnection());
      Customer customer = dao.findById(1000);
      System.out.println(JsonUtil.toPrettyJson(customer));

    } catch (SQLException e) {
      e.printStackTrace();
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }*/
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package school.util;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MASHUK
 */
public class DBConnect {

  public DBConnect() {
  }

  public static Connection getConnection() {
    Connection con = null;
    try {
      Class.forName("com.mysql.jdbc.Driver");
      con = DriverManager.getConnection("jdbc:mysql://localhost:3307/school", "root", "365653");
    } catch (SQLException ex) {
      Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
    }
    return con;
  }

  public static void close(Connection con) {
    try {
      con.close();
    } catch (SQLException ex) {
      Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}

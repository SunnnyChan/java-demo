package me.sunny.demo.basics.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTest {

  @Test
  public void test() {
    BasicDataSource dataSource = new BasicDataSource();

    dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
    dataSource.setUrl("jdbc:mysql://localhost:3306/stu?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true");
    dataSource.setUsername("root");
    dataSource.setPassword("123456");

    // 创建jdbc模板
    JdbcTemplate jdbcTemplate = new JdbcTemplate();
    jdbcTemplate.setDataSource(dataSource);

    // 通过api操作执行sql
    // jdbcTemplate.update("insert into student(stu_name,stu_pwd) values(?,?);", "jack","46asd4634");
  }

  @Test
  public void test1() throws SQLException {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    try {
      // (1)注册驱动
      Class.forName("com.mysql.cj.jdbc.Driver");
      //(2) 获取链接
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "123456");
      // (3)准备语句
      pst = conn.prepareStatement("select * from user");
      // (4)执行查询
      rs = pst.executeQuery();
      // (5)迭代结果
      while (rs.next()) {
        System.out.println(rs.getString("username"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (rs != null) {
          rs.close();
        }
        if (null != pst) {
          pst.close();
        }
        if (null != conn) {
          conn.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}

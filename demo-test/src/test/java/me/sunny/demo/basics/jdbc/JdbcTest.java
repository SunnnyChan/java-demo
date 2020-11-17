package me.sunny.demo.basics.jdbc;

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
}

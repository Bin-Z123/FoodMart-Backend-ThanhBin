package com.poly.ASSIGNMENT_JAVA5.config;

import jakarta.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Connect {
  @Autowired DataSource dataSource;

  @PostConstruct
  public void testConnection() {
    try (Connection conn = dataSource.getConnection()) {
      System.out.println("✅ Kết nối SQL Server thành công: " + conn.getMetaData().getURL());
    } catch (SQLException e) {
      System.out.println("❌ Kết nối thất bại: " + e.getMessage());
    }
  }
}

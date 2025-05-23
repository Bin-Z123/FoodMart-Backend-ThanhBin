package com.poly.ASSIGNMENT_JAVA5.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import vn.payos.PayOS;

@Configuration
public class PayOSConfig implements WebMvcConfigurer {
  @Value("${PAYOS_CLIENT_ID}")
  private String clientId;

  @Value("${PAYOS_API_KEY}")
  private String apiKey;

  @Value("${PAYOS_CHECKSUM_KEY}")
  private String checksumKey;

  @Bean
  public PayOS payOS() {
    return new PayOS(clientId, apiKey, checksumKey);
  }
}

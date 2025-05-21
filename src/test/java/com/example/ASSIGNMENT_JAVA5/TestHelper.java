package com.example.ASSIGNMENT_JAVA5;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TestHelper {

  private static String sessionId;

  public static String getSessionId() throws IOException, InterruptedException {
    HttpClient client = HttpClient.newHttpClient();
    if (sessionId == null) {
      HttpRequest loginRequest =
          HttpRequest.newBuilder()
              .uri(URI.create("http://localhost:9999/login"))
              .header("Content-Type", "application/x-www-form-urlencoded")
              .POST(HttpRequest.BodyPublishers.ofString("username=anhkiet111&password=anhkiet111"))
              .build();
      HttpResponse<String> response =
          client.send(loginRequest, HttpResponse.BodyHandlers.ofString());
      //            Lấy SessionId từ response header
      sessionId = response.headers().firstValue("Set-Cookie").orElse("").split(";")[0];
      System.out.println("Đã đăng nhập, Session ID: " + sessionId);
    }
    return sessionId;
  }
}

package com.example.ASSIGNMENT_JAVA5;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.junit.jupiter.api.Test;

public class LoginApiTest {

  @Test
  public void testLoginSuccess() throws IOException, InterruptedException {
    // Tạo client HTTP
    HttpClient client = HttpClient.newHttpClient();
    // Chuẩn bị request body (tên đăng nhập và mật khẩu)
    //        String jsonBody = "{\"username\":\"anhkiet111\",\"password\":\"anhkiet111\"}";
    String formData = "username=anhkiet111&password=anhkiet111";
    // Tạo request gửi đến API
    HttpRequest request =
        HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:9999/login"))
            .header("Content-Type", "application/x-www-form-urlencoded")
            .POST(HttpRequest.BodyPublishers.ofString(formData))
            .build();
    // Gửi request và nhận response
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    // Kiểm tra nếu response status code là 200 thì test case pass
    assertEquals(response.statusCode(), 200, "Login success");
  }

  @Test
  public void testLoginUsernameAndPasswordNull() throws IOException, InterruptedException {
    // Tạo client HTTP
    HttpClient client = HttpClient.newHttpClient();
    // Chuẩn bị request body (tên đăng nhập và mật khẩu)
    String formData = "username=&password=";
    // Tạo request gửi đến API
    HttpRequest request =
        HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:9999/login"))
            .header("Content-Type", "application/x-www-form-urlencoded")
            .POST(HttpRequest.BodyPublishers.ofString(formData))
            .build();
    // Gửi request và nhận response
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    // Kiểm tra nếu response status code là 401 thì test case pass
    assertEquals(response.statusCode(), 401, "Username and password must not be null");
  }

  @Test
  public void testLoginFail() throws IOException, InterruptedException {
    HttpClient client = HttpClient.newHttpClient();
    String formData = "username=anhkiet111&password=anhkiet";
    HttpRequest request =
        HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:9999/login"))
            .header("Content-Type", "application/x-www-form-urlencoded")
            .POST(HttpRequest.BodyPublishers.ofString(formData))
            .build();
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    assertEquals(response.statusCode(), 401, "Login fail");
  }
}

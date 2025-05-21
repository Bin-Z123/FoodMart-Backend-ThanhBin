package com.example.ASSIGNMENT_JAVA5;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.junit.jupiter.api.Test;

public class ProductApiTest {

  private static final HttpClient client = HttpClient.newHttpClient();

  @Test
  public void testGetAllProduct() throws IOException, InterruptedException {
    HttpRequest request =
        HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:9999/api/product"))
            .header("Content-Type", "application/json")
            .GET()
            .build();
    // Gửi request và nhận response
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    System.out.println("Status code: " + response.statusCode());
    System.out.println("Body: " + response.body());
    assertEquals(response.statusCode(), 200, "Get all product success");
  }

  @Test
  public void testGetProductById() throws IOException, InterruptedException {
    HttpRequest request =
        HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:9999/api/product/7"))
            .header("Content-Type", "application/json")
            .GET()
            .build();
    // Gửi request và nhận response
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    System.out.println("Status code: " + response.statusCode());
    System.out.println("Body: " + response.body());
    assertEquals(response.statusCode(), 200, "Get product by id success");
  }

  @Test
  public void testCreateProductSuccess() throws IOException, InterruptedException {
    String product =
        "{"
            + "\"nameProduct\":\"PC Gaming GTX 1650\","
            + "\"price\":35000000,"
            + "\"image\":\"gamming4070.png\","
            + "\"discount\":5000000,"
            + "\"stockQuantity\":10,"
            + "\"description\":\"Dàn PC gaming mạnh mẽ\","
            + "\"category\":{\"id\":3}"
            + "}";
    // Tạo request gửi đến API
    HttpRequest request =
        HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:9999/api/admin/product"))
            .header("Content-Type", "application/json")
            .header("Cookie", TestHelper.getSessionId())
            .POST(HttpRequest.BodyPublishers.ofString(product))
            .build();
    // Gửi request và nhận response
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    // Kiểm tra nếu response status code là 200 thì test case pass
    assertEquals(response.statusCode(), 200, "Create product success");
    System.out.println("Status code: " + response.statusCode());
    System.out.println("Create product success: " + response.body());
  }

  @Test
  public void testCreateProductNull() throws IOException, InterruptedException {
    String product =
        "{"
            + "\"nameProduct\":\"\","
            + "\"price\":35000000,"
            + "\"image\":\"gamming4070.png\","
            + "\"discount\":5000000,"
            + "\"stockQuantity\":10,"
            + "\"description\":\"Dàn PC gaming mạnh mẽ\","
            + "\"category\":{\"id\":3}"
            + "}";
    // Tạo request gửi đến API
    HttpRequest request =
        HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:9999/api/admin/product"))
            .header("Content-Type", "application/json")
            .header("Cookie", TestHelper.getSessionId())
            .POST(HttpRequest.BodyPublishers.ofString(product))
            .build();
    // Gửi request và nhận response
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    // Kiểm tra nếu response status code là 500 thì test case pass
    assertEquals(response.statusCode(), 500, "Create product success");
    System.out.println("Status code: " + response.statusCode());
    System.out.println("Create product fail: " + response.body());
  }

  @Test
  public void testUpdateProduct() throws IOException, InterruptedException {
    String product =
        "{"
            + "\"nameProduct\":\"PC Gaming RTX 4050\","
            + "\"price\":35000000,"
            + "\"image\":\"gamming4070.png\","
            + "\"discount\":5000000,"
            + "\"stockQuantity\":10,"
            + "\"description\":\"Dàn PC gaming mạnh mẽ\","
            //                + "\"category\":{\"id\":9},"
            + "\"category_id\":9"
            + "}";
    // Tạo request gửi đến API
    HttpRequest request =
        HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:9999/api/admin/product/93"))
            .header("Content-Type", "application/json")
            .header("Cookie", TestHelper.getSessionId())
            .PUT(HttpRequest.BodyPublishers.ofString(product))
            .build();
    // Gửi request và nhận response
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    System.out.println("Status code: " + response.statusCode());
    System.out.println("Update success: " + response.body());
    assertEquals(response.statusCode(), 200, "Update product success");
  }

  @Test
  public void testDeleteProduct() throws IOException, InterruptedException {
    HttpRequest request =
        HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:9999/api/admin/product/94"))
            .header("Content-Type", "application/json")
            .header("Cookie", TestHelper.getSessionId())
            .DELETE()
            .build();
    // Gửi request và nhận response
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    assertEquals(response.statusCode(), 200, "Delete product success");
    System.out.println("Status code: " + response.statusCode());
    System.out.println("Delete success: " + response.body());
  }
  //    @AfterClass
  //    public void testDeleteSessionId() throws IOException, InterruptedException {
  //        HttpRequest request = HttpRequest.newBuilder()
  //                .uri(URI.create("http://localhost:9999/logout"))
  //                .header("Cookie", "JSESSIONID=" + TestHelper.getSessionId())
  //                .GET()
  //                .build();
  //        HttpResponse<String> response = client.send(request,
  // HttpResponse.BodyHandlers.ofString());
  //        assertEquals(response.statusCode(),200, "Delete session success");
  //        System.out.println("Đã xóa session"+ response.body());
  //    }
}

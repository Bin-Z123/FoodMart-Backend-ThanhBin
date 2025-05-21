FROM openjdk:21-jdk-slim

# Cài đặt netcat-traditional để sử dụng lệnh nc trong script wait-for-mssql.sh/ # Dọn dẹp cache apt
RUN apt-get update && apt-get install -y netcat-traditional && apt-get clean && rm -rf /var/lib/apt/lists/*

WORKDIR /app
ARG JAR_FILE=target/*.jar
# Sao chép file jar vào container
COPY ${JAR_FILE} app.jar

# Copy script wait-for-mssql.sh vào container
COPY wait-for-mssql.sh /wait-for-mssql.sh

# Cấp quyền chạy cho script
RUN chmod +x /wait-for-mssql.sh

ENTRYPOINT ["/wait-for-mssql.sh", "sqlserver", "1433", "java", "-jar", "app.jar"]

# Stage 1: Proses Build / Kompilasi
FROM maven:3.9-eclipse-temurin-25 AS builder
WORKDIR /app
# Salin file konfigurasi pom.xml dan folder src
COPY pom.xml .
COPY src ./src
# Jalankan perintah build untuk menghasilkan file JAR
RUN mvn clean package -DskipTests

# Stage 2: Proses Menjalankan Aplikasi (Runtime)
FROM eclipse-temurin:25-jre
WORKDIR /app
# Ambil file JAR yang sukses di-build dari Stage 1 (builder)
COPY --from=builder /app/target/*.jar app.jar
USER ubuntu
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

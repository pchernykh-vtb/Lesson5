# Build
FROM maven:3.9.2-eclipse-temurin-17-alpine as build
# Переходим в директорию app
WORKDIR /app
# Копируем src в app/src в слое образа
COPY src /app/src
# Копируем pom туда же
COPY pom.xml /app
# Запускаем сборку, на выходе jar
RUN mvn -f /app/pom.xml clean package -DskipTests=true

# Run
# Запускаем на образе JRE близкой к сборке версии.
# Новый слой на другом образе.
FROM eclipse-temurin:17-jre
# Копируем получившийся jar из директории target слоя build в app текущего
# Имя файла JAR при сборке составляется из имени проекта и версии из POM.
COPY --from=build /app/target/Lesson5-0.0.1-SNAPSHOT.jar /app/Lesson5-0.0.1.jar
# Порт контейнера
EXPOSE 8080
# Запускаем приложение внутри контейнера
ENTRYPOINT ["java","-jar","/app/Lesson5-0.0.1.jar"]

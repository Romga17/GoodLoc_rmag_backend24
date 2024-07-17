FROM openjdk:21-jdk-slim

WORKDIR /app

# Ajouter un volume pour les logs
VOLUME /tmp

# Copier le fichier JAR généré
COPY goodloc24-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

# Exécuter le fichier JAR
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=prod"]

# Utiliser l'image JDK 21 officielle
FROM openjdk:11-jdk-slim

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier JAR construit dans l'image
COPY target/goodloc24-0.0.1-SNAPSHOT.jar goodloc24back.jar

# Exposer le port sur lequel l'application s'exécute
EXPOSE 8080

# Exécuter le fichier JAR
ENTRYPOINT ["java", "-jar", "goodloc24back.jar"]
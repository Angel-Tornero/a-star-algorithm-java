FROM openjdk:17-slim

WORKDIR /app
COPY src/a_star_algorithm /app/a_star_algorithm
RUN javac /app/a_star_algorithm/*.java

CMD ["java", "a_star_algorithm.Main"]

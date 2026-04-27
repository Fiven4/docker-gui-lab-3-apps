FROM eclipse-temurin:17-jdk-jammy AS builder
WORKDIR /src
COPY Calc.java .
RUN javac Calc.java && jar cfe calc.jar Calc Calc.class

FROM eclipse-temurin:17-jre-jammy
RUN apt-get update && apt-get install -y \
    libxext6 libxrender1 libxtst6 libxi6 libfreetype6 fontconfig \
    && rm -rf /var/lib/apt/lists/*
WORKDIR /app
COPY --from=builder /src/calc.jar .
CMD ["java", "-jar", "calc.jar"]
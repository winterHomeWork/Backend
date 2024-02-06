FROM openjdk:17-jdk

ARG PROFILE
ENV PROFILE ${PROFILE}

ARG DB_URL
ENV DB_URL ${DB_URL}

ARG DB_USERNAME
ENV DB_USERNAME ${DB_USERNAME}

ARG DB_PASSWORD
ENV DB_PASSWORD ${DB_PASSWORD}

ARG REDIS_HOST
ENV REDIS_HOST ${REDIS_HOST}

ARG REDIS_PORT
ENV REDIS_PORT ${REDIS_PORT}

ARG JWT_SECRET_KEY
ENV JWT_SECRET_KEY ${JWT_SECRET_KEY}

ARG JWT_ACCESS_EXP
ENV JWT_ACCESS_EXP ${JWT_ACCESS_EXP}

ARG JWT_REFRESH_EXP
ENV JWT_REFRESH_EXP ${JWT_REFRESH_EXP}

ARG JWT_HEADER
ENV JWT_HEADER ${JWT_HEADER}

ARG JWT_PREFIX
ENV JWT_PREFIX ${JWT_PREFIX}

ARG API_KEY
ENV API_KEY ${API_KEY}

ARG KAKAO_URL
ENV KAKAO_URL ${KAKAO_URL}

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=doker", "-jar", "app.jar"]

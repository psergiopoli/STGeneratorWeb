# Super trunfo generator


#Springboot

#SpringSecurity

#Maven

#PostgreSQL

mvn install dockerfile:build

docker create -v /var/lib/postgresql/data --name spring_app_data postgres:9.6

docker run -d \
    --name postgres-stg\
    -e POSTGRES_DB=stg \
    -e POSTGRES_USER=postgres \
    -e POSTGRES_PASSWORD=postgres \
   postgres

docker run -d \
--link postgres-stg \
-p 8082:8082 \
springio/stgenerator

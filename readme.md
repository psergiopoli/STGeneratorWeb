# Super trunfo generator


#Springboot

#SpringSecurity

#Maven

#PostgreSQL

mvn install dockerfile:build

docker create -v /var/lib/postgresql/data --name spring_app_data postgres:9.6

docker run -d --name postgres-stg -e POSTGRES_DB=stg -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres postgres

docker run -d --name boot-stg --link postgres-stg -p 8080:8080 boot/stgenerator

docker container start <name>
docker container stop <name>

**remover containers parados
docker ps -a -f status=exited
docker rm $(docker ps -a -f status=exited -q)

**descobrir ip do container
docker inspect <name>


**HOST PARA O BANCO
127.0.0.1 postgres-stg
## Docker config
Add mysql and phpmyadmin
Step
1. docker pull mysql
2. Run: docker run --name mysql-server -e MYSQL_ROOT_PASSWORD=1234 -d -p 3306:3306 mysql
1. docker pull phpmyadmin
2. Run: docker run --name phpmyadmin -d --link mysql-server:db -p 8081:80 phpmyadmin
Open localhost:8081 

## Package using
1. Mysql
[<dependency>
  <groupId>com.mysql</groupId>
  <artifactId>mysql-connector-j</artifactId>
  <scope>runtime</scope>
</dependency>]

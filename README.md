### Why did you do this project?
1. Learn about CRUD of Spring boot
2. Learn about Foreign Key, OneToOne, OneToMany
3. Learn about Deploy with Docker and Docker compose

### Examples of using MySQL and phpMyAdmin:
1. docker pull mysql
2. docker run --name mysql-server -e MYSQL_ROOT_PASSWORD=1234 -d -p 3306:3306 mysql
3. docker pull phpmyadmin
4. docker run --name phpmyadmin -d --link mysql-server:db -p 8081:80 phpmyadmin
5. Open localhost:8081 

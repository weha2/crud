## Docker config
MySQL with phpMyAdmin
MySQL
1. docker pull mysql
2. Run: MySQL
	docker run --name mysql-server -e MYSQL_ROOT_PASSWORD=1234 -d -p 3306:3306 mysql
PhpMyAdmin
1. docker pull phpmyadmin
2. Run: PhpMyAdmin
	docker run --name phpmyadmin -d --link mysql-server:db -p 8081:80 phpmyadmin
Open localhost:8081 

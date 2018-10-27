# MARIA DB 사용하기
1. Docker image pull 받기
      * docker pull mariadb
2. Mariadb 실행
      * docker run -p 3306:3306 --name mariadbtest -e MYSQL_ROOT_PASSWORD=mypass -e MYSQL_DATABASE=test -e MYSQL_USER=jimin -e MYSQL_PASSWORD=password -d mariadb
3. Mariadb 안으로 들어가기
      * docker exec -i -t mariadbtest bash
      * Mariadb에 들어간 후, Root로 접속 하기
          * mysql -u root -p

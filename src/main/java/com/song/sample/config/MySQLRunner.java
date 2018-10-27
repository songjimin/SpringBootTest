package com.song.sample.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Component
@Slf4j
public class MySQLRunner implements ApplicationRunner {

    @Autowired
    private DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        //close가 필요 없음
        try (Connection connection = dataSource.getConnection()){
            log.info("MySQL Connection Info URL : {}", connection.getMetaData().getURL());
            log.info("MySQL Connection Info UserName : {}", connection.getMetaData().getUserName());

            Statement statement = connection.createStatement();

            String createSql = "CREATE TABLE USER (ID INTEGER NOT NULL, name VARCHAR(255), PRIMARY KEY (id))";
            statement.executeUpdate(createSql);
        }

        //JDBCTemplate는 자동으로 Connection Close를 해줌, 에러 메시지도 가독성이 좋음
        jdbcTemplate.execute("INSERT INTO USER VALUES (1, 'jimin')");
    }
}

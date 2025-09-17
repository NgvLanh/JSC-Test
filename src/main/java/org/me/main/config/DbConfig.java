package org.me.main.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DbConfig {

    @Bean
    public DataSource dataSource() {
        Dotenv dotenv = null;
        try {
            dotenv = Dotenv.load();
        } catch (Exception ignored) {
        }

        String url = dotenv != null ? dotenv.get("DB_URL") : System.getenv("DB_URL");
        String username = dotenv != null ? dotenv.get("DB_USERNAME") : System.getenv("DB_USERNAME");
        String password = dotenv != null ? dotenv.get("DB_PASSWORD") : System.getenv("DB_PASSWORD");

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName("org.postgresql.Driver");
        return dataSource;
    }
}

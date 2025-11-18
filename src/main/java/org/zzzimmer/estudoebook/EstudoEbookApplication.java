package org.zzzimmer.estudoebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EstudoEbookApplication {

    public static void main(String[] args) {
        SpringApplication.run(EstudoEbookApplication.class, args);
    }


    @Bean
    public static FlywayMigrationStrategy cleanMigrateStrategy() {
        return flyway -> { flyway.repair(); flyway.migrate();
        }; }
}

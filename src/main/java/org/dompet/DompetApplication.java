package org.dompet;

import org.dompet.utils.database.DBProperties;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class DompetApplication {

    @Autowired
    private DBProperties dbProperties;

    public static void main(String[] args) {
        SpringApplication.run(DompetApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void migrateOnStartup() {
        Flyway flyway = Flyway.configure()
            .dataSource(
                dbProperties.getUrl(),
                dbProperties.getUser(),
                dbProperties.getPassword())
                .baselineOnMigrate(true)
            .load();
        flyway.migrate();
    }
}

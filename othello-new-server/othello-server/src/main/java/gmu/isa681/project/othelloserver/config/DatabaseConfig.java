package gmu.isa681.project.othelloserver.config;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories("gmu.isa681.project.othelloserver.repository")
@EnableTransactionManagement
public class DatabaseConfig {

}

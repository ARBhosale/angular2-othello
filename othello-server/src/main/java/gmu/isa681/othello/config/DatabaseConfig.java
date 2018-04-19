package gmu.isa681.othello.config;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories("gmu.isa681.othello.repository")
@EnableTransactionManagement
public class DatabaseConfig {

}

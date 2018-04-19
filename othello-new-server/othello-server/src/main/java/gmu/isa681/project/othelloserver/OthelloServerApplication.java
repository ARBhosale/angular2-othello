package gmu.isa681.project.othelloserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class OthelloServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OthelloServerApplication.class, args);
	}
}

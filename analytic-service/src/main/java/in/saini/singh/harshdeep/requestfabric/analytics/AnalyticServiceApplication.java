package in.saini.singh.harshdeep.requestfabric.analytics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.mongodb.autoconfigure.MongoAutoConfiguration;

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
public class AnalyticServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnalyticServiceApplication.class, args);
	}

}
